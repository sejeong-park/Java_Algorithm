

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    // 입출력 넣기
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int [][] puzzleMap;
    static final int puzzleSize = 9;

    // 가로 기준으로 퍼즐 검사
    public static boolean rowCheck(){
        boolean [] rowCheck; // 행 기준 검증
        for (int row = 0; row < puzzleSize; row ++){
            rowCheck = new boolean[puzzleSize];
            for (int col = 0; col < puzzleSize ; col++){
                // row 기준으로 확인하기
                // puzzleMap은 자연수여서 1부터 시작하므로 -1 을 해줌
                rowCheck[puzzleMap[row][col] - 1] = true;
            }
            // row 탐색이 끝났다면, 9개 중에서 가능 여부 검사
            for (int idx = 0; idx < puzzleSize; idx++){
                // 만약 false 가 있으면, 함수 종료
                if (!rowCheck[idx]) return false; // 불가능한 row가 포함되어 있다.
            }
        }
        return true;
    }

    // 세로 기준으로 검사
    public static boolean colCheck(){
        boolean [] colCheck; // 열 기준 검사
        for (int col = 0; col < puzzleSize; col ++){
            colCheck = new boolean[puzzleSize];
            for (int row = 0; row < puzzleSize; row++){
                // col 기준으로 확인하기
                colCheck[puzzleMap[row][col] -1]  = true;
            }
            // col 탐색이 끝났다면, 9개 중에서 가능 여부 검사
            for (int idx = 0; idx < puzzleSize; idx++){
                // 만약 false 가 있으면 함수 종료
                if (!colCheck[idx]) return false;
            }
        }
        return true;
    }

    // 3*3 map 기준으로 검사 -> 한 맵 당 가능 여부 확인하기
    public static boolean miniMapCase(int startRow, int startCol){
        boolean[] miniCheck = new boolean[puzzleSize];
        for (int row = startRow; row<startRow+3; row++){
            for (int col = startCol; col < startCol+3; col++){
                miniCheck[puzzleMap[row][col]-1] = true;
            }
        }
        // 9개 중에 없는 숫자가 있다면 -> 거짓
        for (int idx = 0; idx < puzzleSize; idx++){
            if (!miniCheck[idx]) return false;
        }
        return true;
    }

    // 3*3 기준으로 검사
    public static boolean miniMapCheck(){
        for (int row = 0; row < puzzleSize; row+=3){
            for (int col = 0; col < puzzleSize; col+=3){
                if (!miniMapCase(row, col)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        // 입출력 생성
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // test case 입력 받기
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {

            // 9*9의 퍼즐 구현하기
            puzzleMap = new int[puzzleSize][puzzleSize];
            // 퍼즐 입력 받기
            for (int row = 0; row < puzzleSize; row++){
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < puzzleSize; col++){
                    puzzleMap[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // 3가지 조건 모두 true일 경우 1 (세로 검사 + 가로 검사 + 3*3검사)
            // 아니라면 false = 0
            sb.append("#").append(test_case).append(" ");
            if (rowCheck() && colCheck() && miniMapCheck()){
                sb.append("1").append("\n");
            }else{
                sb.append("0").append("\n");
            }

        }// end for testcase
        System.out.println(sb);

    }


}
