
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    // 입출력 선언
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    // 공용
    static int N, K;
    static int[][] map;
    public static void main(String[] args) throws IOException {

        // 입출력
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // test case 입력 받기
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T ; test_case++){
            st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken()); // 가로 세로 길이
            K = Integer.parseInt(st.nextToken()); // 단어의 길이

            map = new int[N][N];

            int totalResult = 0;
            for (int row = 0; row < N; row ++){
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < N; col ++){
                    map[row][col] = Integer.parseInt(st.nextToken()); // 입력 받기
                }
            }

            // 행 검사 -> cnt
            for (int row = 0; row < N; row++){
                int rowCnt = 0;
                for (int col = 0; col < N ; col++){
                    // 만약 낱말 단어가 빈칸이라면?
                    if (map[row][col] == 0) {
                        // 만약 단어 낱말이 겹쳐지면?
                        if (rowCnt == K){
                            totalResult++; // 단어 추가 가능
                        }
                        rowCnt = 0;  //
                    }else{
                        rowCnt++;
                    }
                }
                if (rowCnt == K){
                    totalResult++;
                }

            } // end for row checking

            // 열 검사
            for (int row = 0; row < N; row++){
                int colCnt = 0;
                for (int col = 0; col < N; col++){
                    // 만약 낱말 단어가 세로로 빈칸이라면?
                    if (map[col][row] == 0){
                        if (colCnt == K){
                            totalResult++;
                        }
                        colCnt = 0;
                    }else{
                        colCnt++;
                    }
                }
                if (colCnt == K){
                    totalResult++;
                }
            } // end for col checking

            sb.append("#").append(test_case).append(" ").append(totalResult).append("\n");
        } // end for test_case
        System.out.println(sb);
    }
}
