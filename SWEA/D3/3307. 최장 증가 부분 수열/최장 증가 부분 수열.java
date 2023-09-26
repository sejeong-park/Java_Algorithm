

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_3307_최장증가부분수열
 *
 *
 *
 * @author sj.park
 * */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;


    public static void main(String[] args) throws IOException {

        // 입출력 선언
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // Test case 입력
        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++){

            int lisLength = Integer.parseInt(br.readLine().trim());
            int[] array = new int[lisLength];
            // 입력 받기
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < lisLength; idx++){
                array[idx] = Integer.parseInt(st.nextToken()); // 입력
            }

            // LIS

            int[] dp = new int[lisLength]; // dp table 생성
            dp[0] = 1; // 0번째도 최장수열은 무조건 자기 자신도 포함이니까 초기화
            for (int idx = 1; idx < lisLength; idx++){
                dp[idx] = 1; // 자기자신이 있을 경우 최소 1 -> 초기화
                for (int jdx = 0; jdx < idx; jdx++){
                    // 자기자신보다 작은 원소들에서 "i"현재보다 작은 지 비교
                    if (array[idx] > array[jdx]){
                        // 현재 자기 자신보다 jdx가 작다면, 증가
                        dp[idx] = Math.max(dp[idx], dp[jdx] + 1);
                    }
                }
            }

            int lisResult = Integer.MIN_VALUE;
            for (int idx = 0; idx < lisLength; idx++){
                lisResult = Math.max(dp[idx], lisResult);
            }

            sb.append("#").append(tc).append(" ").append(lisResult).append("\n");
        } // testCase 종료

        System.out.println(sb);




    }


}
