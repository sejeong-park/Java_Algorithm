

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @date 23.08.29
 * @problem SWEA_1767_프로세서 연결하기
 *
 * [문제]
 * 1. 멕시노스의 가장자리에 위치한 Core는 이미 전원이 연결된 것으로 간주
 * 2. 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합
 * 3. 여러 방법이 있을 경우, 전선길이의 합이 최소가 되는 값
 * [조건]
 * 1. 전선은 절대로 교차해서는 안된다.
 * 2. 전선 길의의 합의 최소
 *
 * @time : 8:00
 * @author sejeong-park
 * */

public class Solution {

    // 입출력
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // 맵 정보 입력
    static int mapSize;
    static int[][] map;

    // Core List
    static ArrayList<Core> coreList;

    // 최종 결과 값
    static int resultWireLineCnt;
    static int resultCoreCnt;

    // map 관련 고정 상수
    static final int CORE = 1;
    static final int WIRE = 2;
    static final int[] deltaRow = {-1, 1, 0, 0};
    static final int[] deltaCol = {0, 0, -1, 1};

    // Core 클래스
    public static class Core{
        int row;
        int col;

        Core(int row, int col){
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Core{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    // 디버깅용 print
    public static void print(){
        for (int row = 0; row < mapSize; row++){
            System.out.println(Arrays.toString(map[row]));
        }
        System.out.println();
    }

    // 가장자리인지 확인
    public static boolean isEdge(int row, int col){
        // 가장자리인 경우 확인
        return row == 0 || row == mapSize-1 || col == 0 || col == mapSize-1;
    }

    // 좌표가 맵 안에 있는 지 확인하기
    public static boolean isInMap(int row, int col){
        if (row < 0 || row >= mapSize || col < 0 || col >= mapSize)
            return false;
        return true;
    }

    // 전원에 연결할 수 있는 지 확인 = 가장자리와 만나는지
    public static boolean connectPower(int row, int col, int direction){

        while (true){
            // 다음 블록 찾기
            int nextRow = row + deltaRow[direction];
            int nextCol = col + deltaCol[direction];

            // map 밖으로 나갔다 == power에 연결이 가능하다.
            if (!isInMap(nextRow, nextCol)){
               return true;
            }
            // 만약 중간에 코어가 있거나 Wire가 있으면 -> 연결 불가능
            if (map[nextRow][nextCol] == CORE || map[nextRow][nextCol] == WIRE){
                return false;
            }

            // 다음으로 넘어가기
            row = nextRow;
            col = nextCol;
        }

    }


    public static void setWireLine(int row, int col, int direction, int cellKey){

        while (true){
            // 다음 블록 찾기
            int nextRow = row + deltaRow[direction];
            int nextCol = col + deltaCol[direction];

            // map 밖으로 나가면 종료 -> 더 세팅하지 않는다.
            if (!isInMap(nextRow, nextCol))
                break;

            // 맵에 깔아준다.
            map[nextRow][nextCol] = cellKey;

            // 업데이트
            row = nextRow;
            col = nextCol;
        }

    }

    // 전선이 깔린 수 세기
    public static int countWireLine(){
        int wireLineCnt = 0;
        // 전선 개수 세기
        for (int row = 0; row < mapSize; row++){
            for (int col = 0; col < mapSize; col++){
                // System.out.print(map[row][col] + " ");
                if (map[row][col] == WIRE) wireLineCnt++;
            }
            // System.out.println();
        }
        // System.out.println(wireLineCnt);
        return wireLineCnt;
    }

    // 결론
    // 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구한다.
    // 여러 방법이 있을 경우, 전선의 길이의 합이 최소가 되는 값을 구하라
    public static void findResult(int coreCnt){
        // 만약 코어 개수가 최종 코어 수보다 크다면?
        if (coreCnt > resultCoreCnt){
            resultCoreCnt = coreCnt;
            resultWireLineCnt = countWireLine();
            return;
        }
        // 여러 방법이 있을 경우
        else if (coreCnt == resultCoreCnt){
            // 전선 길이의 합이 최소가 되는 값을 구한다.
            int currentCountWireLine = countWireLine();
            resultCoreCnt = coreCnt; // 동일
            if (resultWireLineCnt > currentCountWireLine){
                resultWireLineCnt = currentCountWireLine;
            }
        }
    }


    // coreList를 탐색하여, 전체 돌기
    public static void connectCore(int coreIdx, int coreCnt){

        // 1. 기저조건
        if (coreIdx == coreList.size()){
            findResult(coreCnt);
            return;
        }

        // 2. 진행 조건
        // 현재 Core
        Core currentCore = coreList.get(coreIdx);
        // 방향 탐색
        for (int direction = 0; direction < 4; direction++){
            // step 1. 전선을 연결할 수 있는 지 확인하기
            if (!connectPower(currentCore.row, currentCore.col, direction)) continue; // 전선을 연결할 수 없으면 무시

            // step 2. 전선 깔기
            setWireLine(currentCore.row, currentCore.col, direction, WIRE);
            // step 3. 다음 코어 탐색하기
             connectCore(coreIdx+1, coreCnt+1); // 만약 Core를 깔 수 있으면 Core+1
            // step 4. 전선 다시 풀기 -> 현재 배열에서 탐색하기용
            setWireLine(currentCore.row, currentCore.col, direction, 0); // WIRE-> 0으로 세팅 해주기
        }
        // 만약 첫번째 코어에서 전선을 깔지 않을 경우도 고려할 수 있다.
        connectCore(coreIdx+1, coreCnt);
    }


    public static void main(String[] args) throws IOException {

        // 입출력 객체
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase <= TC; testCase ++){

            coreList = new ArrayList<>();
            mapSize = Integer.parseInt(br.readLine().trim());
            // 맵 입력
            map = new int[mapSize][mapSize];
            for (int row = 0; row < mapSize; row++){
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < mapSize; col++){
                    map[row][col] = Integer.parseInt(st.nextToken());

                    // Core인데, 가장자리에 있는 경우를 제외하고 Core탐색
                    if (map[row][col] == CORE && !isEdge(row, col)){
                        coreList.add(new Core(row, col));
                    }
                }
            } // end for input

            // 결론
            // 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구한다.
            // 여러 방법이 있을 경우, 전선의 길이의 합이 최소가 되는 값을 구하라
            resultCoreCnt = Integer.MIN_VALUE;
            resultWireLineCnt = Integer.MAX_VALUE;

            // connection DFS 탐색
            connectCore(0,0);

            // 최종 결과
            sb.append("#").append(testCase).append(" ").append(resultWireLineCnt).append("\n");

        } // end for testCase

        System.out.println(sb);

    }


}
