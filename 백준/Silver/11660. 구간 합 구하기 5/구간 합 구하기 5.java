
/*
* 구간합 구하기 5
* 가장 큰 범위 먼저 - 세로 - 가로 + 세로 가로에서 빼줄 때 중첩된 것
*
* */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] prefixSum;

    public static int prefixSumBlock(int row, int col){
        return prefixSum[row][col-1] + prefixSum[row-1][col] - prefixSum[row-1][col-1];
    }

    public static void main(String[] args) throws IOException {
        // 입력 조건들
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 출력 조건
        StringBuilder sb = new StringBuilder();

        // 처음 입력 구분해서 받기
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N * N배열 입력 받기
        int [][] array = new int[N][N];
        prefixSum = new int[N+1][N+1]; // 1depth 더 넣어준 입력

        // N * N 누적합 배열 입력 받기
        for (int row = 1; row <= N; row ++){
            // row 입력 받기
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col <= N; col++){
                // 현재 prefixSum 배열에는 현재 더한 값 + 현재 입력 받은 배열
                prefixSum[row][col] = prefixSumBlock(row, col) + Integer.parseInt(st.nextToken());
            }
        }

        // 출력 및 케이스 입력 받기
        for (int idx = 0; idx < M; idx ++ ){
            st = new StringTokenizer(br.readLine().trim()); // 입력 받기
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // prefix 빼주기
            int result = prefixSum[x2][y2] - prefixSum[x1 - 1][y2] - prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 - 1];
            sb.append(result).append("\n");
        }
        System.out.println(sb);



    }



}
