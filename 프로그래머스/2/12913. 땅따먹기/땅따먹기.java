
import java.util.*;
class Solution {
    
    public int[][] copyLand(int[][] land) {
        int [][] copy= new int [land.length][land[0].length];
        
        for (int row = 0; row < land.length; row ++) {
            for (int col = 0; col < land[0].length; col ++) {
                copy[row][col] = land[row][col];
            }
        }
        return copy;
    }
     
    public int solution(int[][] land) {
        int answer = 0;
        
        // land를 dp 테이블로 만들기
        int [][] dp = copyLand(land);

        for (int row = 1; row < land.length; row ++) { // 한행씩 내려옴
            for (int col = 0; col < land[0].length; col ++) {
                // 한줄 전꺼 현재 위치 제외
                int maxTarget = 0;
                for (int idxCheck = 0; idxCheck < land[0].length; idxCheck++){
                    if (col == idxCheck) continue;
                    maxTarget = Math.max(dp[row - 1][idxCheck], maxTarget);
                }
                dp[row][col] += maxTarget;
            }
            // System.out.println(Arrays.toString(dp[row]));
        }
        
        // // 마지막 줄 중 최댓값
        for (int score : dp[land.length - 1]) {
            answer = Math.max(score, answer);
        }
        return answer;
    }
}