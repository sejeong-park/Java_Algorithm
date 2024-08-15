import java.util.*;
// 세로 3 가로 n
class Solution {
    public long solution(int n) {
        long answer = 0;
        // 홀수면 불가능
        if (n%2 == 1) return 0;
        long dp [] = new long [n + 1];
        dp[0] = 1;
        dp[2] = 3;
        // 2칸씩
        for (int idx = 4; idx <= n; idx += 2) {
            dp[idx] = dp[idx - 2] * 3;
            for (int jdx = idx - 4; jdx >= 0; jdx -= 2) {
                dp[idx] += dp[jdx] * 2;
            }
            dp[idx] %= 1000000007;
        }
        
        answer = dp[n];
        return answer;
    }
}