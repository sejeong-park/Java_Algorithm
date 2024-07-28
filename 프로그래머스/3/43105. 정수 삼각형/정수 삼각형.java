import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int size = triangle.length;
        
        int [][] dp = new int [size][size];
        dp[0][0] = triangle[0][0]; // 초기화
        
        // STEP 1 : col의 index 0 레이어 먼저 만들기
        for (int row = 1; row < size; row ++) {
            dp[row][0] = triangle[row][0] + dp[row - 1][0];
        }
        // STEP 2 : 삼각형 DP 채우기 -> 좌 & 우를 비교한다.
        for (int row = 1; row < size; row ++) {
            for (int col = 1; col < triangle[row].length; col ++) {
                int a = triangle[row][col] + dp[row - 1][col - 1]; // 좌
                int b = triangle[row][col] + dp[row - 1][col]; // 우
                dp[row][col] = Math.max(a,b);
            }
        }        
        // STEP 3 : MAX 값 찾기 => 마지막 줄만 비교
        for (int col = 0; col < size; col ++) {
            answer = Math.max(answer, dp[size - 1][col]);
        }

        return answer;
    }
}