

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @date : 23.08.23
 * @problem : BOJ_17471_게리맨더링
 *
 * [문제]
 * - 몇년간 게리맨더링을 통해 자신의 당에게 "유리"하게 선거구 획정
 * - 최대한 공평하게 선거구를 획정 하려함
 * - 백준시 N개 구역으로 나누어져있고, 1~N까지 번호가 매겨져 있다.
 * - 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어 있어야 한다.
 * - 선거구는 구역을 적어도 하나 포함해야하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어있어야 한다.
 * - 결론 : 공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 <최소>로 하려고 한다.
 *             백준시의 정보가 주어졌을 때 인구 차이의 최솟값 구하기
 *
 * [풀이]
 * 1. 구역의 개수 N 입력 받기
 * 2. N구역에 속한 인구 수 정보 입력 받기
 * 3. N개의 줄에 각 구역과 인접한 구역의 정보 주어진다. [구역과 인접한 구역의 수 / 인접한 구역의 번호]
 * 4. A와 B가 인접하면, B와 A가 인접 (행렬)
 *
 * @author sejeong-park
 * **/

public class Main {

    // 입출력
    static BufferedReader br;
    static StringTokenizer st;

    // 맵의 정보
    static int regionCnt;
    static int[] regionPeopleCnt; // 구역의 인구 수
    // power set을 구하는 데 필요한 정보
    static int[] regionNumber; // 인접 행렬의 조합
    static int [] selected; // 방문 여부 확인
    // 인접한 것인지 확인하기 위한 인접행렬
    static int[][] regionGraph; // 구역의 인접 행렬

    // 최종 결과 찾기
    static int minPopulationDifference;

    // 구역의 정보 초기화
    public static void makeRegionNumber() {
        regionNumber = new int[regionCnt + 1]; // 구역의 번호 정보 (탐색 여부)

        // regionNumber 그대로 넣기
        for (int number = 0; number <= regionCnt; number++) {
            regionNumber[number] = number; // 자기 자신 넣기
        }
    }

    // 현재 내가 가지고 있는 영역은 true / 다른 영역은 false로 표기한다.
    public static boolean[] checkRegion(int[] region) {
        // region 이미 탐색한 건지 확인하기
        boolean[] visited = new boolean [regionCnt+1];
        // 만약 조합에서 이미 탐색을 한 것 이라면,
        for (int idx = 0; idx < region.length ; idx++) {
            visited[region[idx]] = true; // region에는 number 정보가 들어 있으므로
        }
        return visited;
    }

    // 시작 index 찾기
    public static int findStartIdx(boolean[] region, boolean flag) {
        for (int number = 1; number < regionCnt + 1; number++) {
            if (region[number] == flag) {
                return number; // 영역을 비교 시작할 region
            }
        }
        return -1;
    }


    // 서로 인접한 영역인지 확인하는 메서드
    public static boolean findConnection(boolean[] region, boolean flag) {

        // bfs를 위한 초기화
        int startNumber = findStartIdx(region, flag); // flag인 영역의 임의의 점으로 시작한다.
        boolean [] visited = new boolean[regionCnt+1]; // 방문 구역 num 확인

        // BFS로 방문 탐색
        Deque<Integer> queue = new ArrayDeque<Integer>(); // 큐 생성
        queue.add(startNumber); // 첫번째 인덱스 넣기
        visited[startNumber] = true;

        // queue 탐색
        while (!queue.isEmpty()) {

            int currentNode = queue.poll();

            // 만약 현재 것이 인접행렬에서 체크되어있다면,
            for (int nextNode = 1; nextNode < regionCnt+1; nextNode++) {
                // 그래프가 연결되어 있어야 한다. && 해당 노드를 방문하지 않았어야 한다. && 팀의 색이 같아야 한다.
                if (regionGraph[currentNode][nextNode] == 1
                            && !visited[nextNode]
                            && region[nextNode] == flag){
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }
            }
        }  // end for bfs 탐색

        // 서로 연결되어 있는 지 확인한다.
        // 구역 묶음과 방문 노드가 일치하면 !
        for (int regionNumber = 1; regionNumber < regionCnt + 1; regionNumber++){
            // 만약 flag가 true인 경우면, region과 visited의 t/f가 일치해야 한다.
            if(flag == true){
                // 다르면 out
                if (region[regionNumber] != visited[regionNumber]) return false;
            }
            // 만약 flag가 false인 경우면, region과 visited의 t/f가 반대로 되어야 한다.
            else{
                if (region[regionNumber] == visited[regionNumber]) return false;
            }
        }

        return true;
    }

    // 인구 차이를 구한다.
    public static void populationDirfference(boolean[] regionSpace){
        // 초기화
        int trueRegionPopulation = 0;
        int falseRegionPopulation = 0;
        // 인구 수 세기
        for (int regionNumber = 1; regionNumber < regionCnt + 1; regionNumber++){
            if (regionSpace[regionNumber]) trueRegionPopulation += regionPeopleCnt[regionNumber];
            if (!regionSpace[regionNumber]) falseRegionPopulation += regionPeopleCnt[regionNumber];
        }

        minPopulationDifference = Math.min(minPopulationDifference, Math.abs(trueRegionPopulation - falseRegionPopulation));
    }


    // 두영역으로 나눌 때, 두 영역이 서로 연결 되어있는 것인지 확인하기
    public static void divideRegion(int[] region) {
        /**
         * @Parem : region
         * checkRegion 메서드 : 현재 방문한 칸은 true / 아닌 칸은 false로 영역을 구분해준다.
         * - region1을 이용하여, region2를 찾는다.
         * - 두 영역은 나눈 한 영역끼리 "연결" 되어 있어야 한다.
         * **/
        // 1. (1 ~ regionCnt+1)까지 false와 true로 영역의 칸을 구분함
        boolean [] regionSpace = checkRegion(region);

        // 2. bfs를 이용하여, 탐색한다.
        // 2-1. 현재 위치를 기준으로 하는 것은 true
        boolean regionValidation1 = findConnection(regionSpace, true);
        if (!regionValidation1) return; // 종료
        // 2-2. 나와 다른 영역에서 연결 여부를 확인한다.
        boolean regionValidation2 = findConnection(regionSpace, false);
        if (!regionValidation2) return; // 종료

        // 3. 둘 다 true 인 경우 ->  두 영역이 모두 유효한 경우
        populationDirfference(regionSpace);
    }


    // 만들 수 있는 조합의 수
    public static void combination(int selectIdx, int elementIdx) {

        // 기저 조건 : select 크기가 다 찼다면
        if (selectIdx == selected.length) {
            divideRegion(selected); // 두 개의 영역 찾기
            return;
        }

        // 기저 조건 : 최종 종료
        if (elementIdx == regionNumber.length) {
            return;
        }

        // 진행 조건
        // 선택한 후 조합하는 경우
        selected[selectIdx] = regionNumber[elementIdx];
        combination(selectIdx+1, elementIdx+1);
        // 선택하지 않고 조합하는 경우
        combination(selectIdx, elementIdx+1); // 탐색하지 않을 경우 다음으로 넘어가기
    }


    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub

        // 입출력
        br = new BufferedReader(new InputStreamReader(System.in));

        // 몇개의 구역?
        regionCnt = Integer.parseInt(br.readLine().trim());

        // 구역의 인구 정보
        regionPeopleCnt = new int[regionCnt+1]; // index가 구역의 번호
        regionGraph = new int[regionCnt + 1][regionCnt + 1]; //  구역의 인접 행렬 정보

        // 인구 정보
        st = new StringTokenizer(br.readLine().trim());
        for (int currentRegion = 1; currentRegion < regionCnt+1 ; currentRegion++) {
            // 각 번호 별 인구 정보 넣기
            regionPeopleCnt[currentRegion] = Integer.parseInt(st.nextToken());
        }
        // 각 구역 별 정보 입력
        for (int currentRegion = 1; currentRegion < regionCnt+1 ; currentRegion ++) {
            // 구역에 해당하는 정보 입력 받기
            st = new StringTokenizer(br.readLine().trim());

            // 첫번째 수는 주변에 인접한 구역이 "몇개 있는지 확인"
            int nearCnt = Integer.parseInt(st.nextToken());
            // idx를 넣기
            for (int idx = 0; idx < nearCnt ; idx++) {
                int nearRegion = Integer.parseInt(st.nextToken());
                // A구역과 B가 인접하면, B도 A와 인접하다. (인접 행렬만들기)
                regionGraph[currentRegion][nearRegion] = 1;
                regionGraph[nearRegion][currentRegion] = 1;
            }
        }

        // 영역 정보가 들어있는 map
        makeRegionNumber(); // 영역 번호가 들어있는 배열 만듬

        minPopulationDifference = Integer.MAX_VALUE; // 최종 최소 값 구하는 함수

        // 조합을 도는 경우 (예 : 5C1 , 4개의 나머지 / 5C2, 3개의 나머지)
        for (int idx = 1; idx <= regionCnt / 2; idx++) {
            // 무조건 2영역으로 나눠져야 한다.
            selected = new int[idx]; // 몇개를 선택할 것인지 객체 생성
            combination(0,1); // elementIdx 는 1부터 선택
        }

        // 결과 출력
        if (minPopulationDifference == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(minPopulationDifference);
        }
    }
}