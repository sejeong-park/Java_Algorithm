import java.util.*;

/**
가로 길이 2 
세로 길이 1
직사각형 모양 타일
직사각형을 채우는 방법의 수 리턴
n = 1 -> 1
n = 2 -> 2
n = 3 -> 3
*/

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int [] dp = new int [n];
        dp[0] = 1;
        dp[1] = 2;
        
        for (int idx = 2; idx < n; idx ++) {
            int num = dp[idx - 1] + dp[idx - 2];
            dp[idx] = num % 1000000007;
        }
        return dp[n - 1];
    }
}