import java.util.*;

/**
유저 현재 남은 피로도는 80 이상 / 던전을 탐험한 후에는 피로도 20이 소모 됨
[최소 필요 피로도 / 소모 피로도] -> 
*/
class Solution {
    
    static int [][] dungeons;
    static boolean [] visited;
    static int answer;
    
    // dfs
    public static void dfs(int status, int count) {
        
        for (int idx = 0; idx < dungeons.length; idx ++) {
            // 방문 하지 않은 경우
            if (!visited[idx] && status >= dungeons[idx][0]) {
                visited[idx] = true;
                dfs(status - dungeons[idx][1], count + 1);
                visited[idx] = false;
            }
        }
        
        // 전체 탐색했을 경우
        answer = Math.max(answer, count);
    }
    
    
    public int solution(int k, int[][] dungeons) {
        this.answer = 0;
        this.dungeons = dungeons;
        
        visited = new boolean[dungeons.length];
        dfs(k, 0); // 첫번째부터 시작
        
        return answer;
    }
}