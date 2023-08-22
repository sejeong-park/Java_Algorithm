

/*
 * BOJ_15683_감시
 *
 * [CCTV 정보]
 * 1번 : 한방향
 * 2번 : 반대방향 (2)
 * 3번 : 직각방향 (2)
 * 4번 : 세방향 (3)
 * 5번 : 상하좌우(4)
 * CCTV 회전 가능
 * [벽 정보]
 * 6
 * [출력 값]
 * 사각지대의 최소 크기 = 최대한 탐색하는 영역이 많아야 한다.
 *
 * 풀이 시작 시간 : 2시 8분
 *
 * */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // 맵 정보 입력
    static int rowSize, colSize;
    static int[][] map;

    // delta direction = 상하좌우
    static final int[] deltaRow = {-1, 1, 0, 0};
    static final int[] deltaCol = {0, 0, -1, 1};

    // CCTV의 개수를 저장하는 리스트
    static ArrayList<int[]> totalCCTV; // CCTV 전체 위치가 담겨 있는 리스트

    // direction 정보 저장하는 맵
    static ArrayList<ArrayList<Integer>> directionsOfCCTV;

    // result
    static int minNonCCTV;


    // copy 하여 복사 하는 메서드 ..
    public static int[][] copyArray(int[][] inputArray){
        int [][] copyArray = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                copyArray[row][col] = inputArray[row][col];
            }
        }
        return copyArray;
    }


    // CCTV의 방향 정보를 알려주는 메서드
    public static void infoCCTV(int num) {
        // 자바..로 딕셔너리(hashMap)...안에 value 2차원으로 어떻게 하나여 ,..   :<

        directionsOfCCTV = new ArrayList<>(); // CCTV 정보를 입력 해줄 배열객체 생성

        if (num == 1) {
            // 일차원 리스트에 객체 선언하면서 리스트 넣는 방법
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(0)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(1)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(2)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(3)));
        }
        else if (num == 2) {
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(0,1)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(2,3)));
        }
        else if (num == 3) {
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(0,3)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(2,0)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(1,2)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(1,3)));
        }
        else if (num == 4) {
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(0,1,2)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(0,1,3)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(2,3,0)));
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(2,3,1)));
        }
        else if (num == 5) {
            directionsOfCCTV.add(new ArrayList<>(Arrays.asList(0,1,2,3)));
        }

    }

    // 현재 방향으로 확인한다.
    public static int[][] watchToDirection(int row, int col, ArrayList<Integer> directions, int[][] resultMap) {

        Deque<int[]> queue = new ArrayDeque<>(); // 감시하고 있는 방향을 넣기 위해

        // CCTV가 바라보고 있는 방향이므로 계속!
        for (int direction : directions) {
            int nextRow = row + deltaRow[direction];
            int nextCol = col + deltaCol[direction];
            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize || resultMap[nextRow][nextCol] == 6) continue;
            queue.add(new int[] {nextRow, nextCol, direction});
        }


        // # = -1로 대체
        while (!queue.isEmpty()) {
            int [] nowPoint = queue.pop();
            if (resultMap[nowPoint[0]][nowPoint[1]] == 0)
                resultMap[nowPoint[0]][nowPoint[1]] = -1;
//            if(nowPoint[0]==1)
//                System.out.println(nowPoint[0] + " " + nowPoint[1]);
            // 현재 방향을 유지한 채 뒤로 간다.
            int nextRow = nowPoint[0] + deltaRow[nowPoint[2]];
            int nextCol = nowPoint[1] + deltaCol[nowPoint[2]];

            // 밖으로 나가지 않은 경우
            if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize || resultMap[nextRow][nextCol] == 6 ) continue;
            // 다른 CCTV가 없는 경우 = 0일 경우 탐색 && CCTV 인 경우 탐색
            if (resultMap[nextRow][nextCol] <6) {
                queue.add(new int[] {nextRow, nextCol, nowPoint[2]}); // 현재 방향은 유지

            }
        }


        return resultMap;
    }

    // CCTV의 사각지대를 구하는 함수
    public static int NonCCTV(int [][] resultMap) {
        int count = 0;
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (resultMap[row][col] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static void print(int[][] OriginalMap) {
        for (int row = 0; row < rowSize; row++) {
            System.out.println(Arrays.toString(OriginalMap[row]));
        }
        System.out.println();
    }

    public static void dfs(int cctvIdx, int[][] OriginalMap) {

        // 기저조건 -> depht와 totalCCTV Size 가 일치하면,
        // 사각지대에 있는 수를 구한다.
        if (cctvIdx == totalCCTV.size()) {
            int result = NonCCTV(OriginalMap);
            if (result <= minNonCCTV) {
                // print(OriginalMap);
                minNonCCTV = Math.min(NonCCTV(OriginalMap), minNonCCTV);
            }
            return;
        }

        // 진행조건
        // 그렇지 않다면, 다음 CCTV의 케이스를 비교한다.

        int[] currentCCTV = totalCCTV.get(cctvIdx);
        infoCCTV(map[currentCCTV[0]][currentCCTV[1]]);  // CCTV 번호를 입력하여, 현재 탐색해야할 방향을 확인한다.

        int[][] currentMap = copyArray(OriginalMap); // copy하여 탐색
        // 방향 정보의 케이스 확인
        for (ArrayList<Integer> directions : directionsOfCCTV) {
            // 현재의 CCTV가 지나갈 수 있는 길을 dfs로 간다.
            currentMap = watchToDirection(currentCCTV[0], currentCCTV[1], directions, currentMap); // fill/            dfs(cctvIdx+1, currentD); // 한칸 이동
            dfs(cctvIdx+1, currentMap);
            currentMap = copyArray(OriginalMap); // 다시 풀어주기
        }


    }


    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub

        // 입출력
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();


        // 맵 정보 입력 받기
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        map = new int[rowSize][colSize];
        totalCCTV = new ArrayList<>(); // total CCTV 초기화

        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (1 <= map[row][col] && map[row][col] <= 5) {
                    totalCCTV.add(new int[] {row, col}); // 위치값 넣기
                }
            }
        }

        minNonCCTV = Integer.MAX_VALUE;
        // 각 CCTV의 케이스 찾기
        dfs(0, map); // 처음 시작 : 0 depth부터 원본 MAP 넣기

        System.out.println(minNonCCTV);
    }

}