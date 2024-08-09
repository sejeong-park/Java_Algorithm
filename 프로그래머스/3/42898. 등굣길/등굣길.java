
import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
            
        final int MOD = 1000000007; // 최단 경로
            
        int [][] dp = new int[n + 1][m + 1];
        for (int idx = 0; idx < puddles.length; idx++){
            dp[puddles[idx][1]][puddles[idx][0]] = -1;
        }
        
        // dp 초기화
        dp[1][1] = 1;
        for (int rowIdx = 1; rowIdx < n + 1; rowIdx++){
            for (int colIdx = 1; colIdx < m + 1; colIdx++){
                if (dp[rowIdx][colIdx] == -1) 
                    continue;
                if (dp[rowIdx-1][colIdx] > 0){
                    dp[rowIdx][colIdx] += dp[rowIdx - 1][colIdx] % MOD;
                }
                if(dp[rowIdx][colIdx - 1] > 0){
                    dp[rowIdx][colIdx] += dp[rowIdx][colIdx - 1] % MOD;
                }
                    
            }
        }
        
        answer = dp[n][m] % MOD;
        return answer; 
    }
}