import java.util.*;

class Solution {
    
    static HashSet<String> set;
    static String [] ban;
    static String [] user;
    static boolean [] visited;
    
    public static void dfs(int depth, String result) {
        if (depth == ban.length) {
 
            // String 재정렬
            String [] strList = result.split(" "); // String 목록
            Arrays.sort(strList);
            
            // 다시 String 만들어서 set에 넣기
            String str = "";
            for (int idx = 0; idx < strList.length; idx ++) {
                str += strList[idx] + " ";
            }
            set.add(str);
            return;
        }
        
        for (int idx = 0; idx < user.length; idx ++) {
            if (!visited[idx] && user[idx].matches(ban[depth])) {
                visited[idx] = true;
                dfs(depth + 1, result + " " + user[idx]);
                visited[idx] = false;
            }
        }
        
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        set = new HashSet<>();
        user = user_id; 
        ban = new String[banned_id.length];
        for (int idx = 0; idx < banned_id.length; idx ++) {
            ban[idx] = banned_id[idx].replace("*", ".");
        }
        visited = new boolean[user.length];
        dfs(0, "");
        
        return set.size();
    }
}