import java.util.*;

/**
조건 조합
*/
class Solution {
    
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static String [] condition; // data
    static boolean [] visited;
    static int count;
    static char [] output;
    
    private static boolean check(String order) {
        for (String cond : condition) {
            char a = cond.charAt(0);
            char b = cond.charAt(2);
            char op = cond.charAt(3);
            int distance = cond.charAt(4) - '0';
            
            // 두 문자의 위치 사이 값
            int length = Math.abs(order.indexOf(a) - order.indexOf(b)) - 1;
            
            if (op == '=') {
                if (length != distance) return false;
            }
            else if (op == '<') {
                if (length >= distance) return false;
            }
            else if (op == '>') {
                if (length <= distance) return false;
            }
        }
        return true;
    }
    
    private static void permutation(int depth, int selectIdx) {
        // 종료 조건
        if (depth == friends.length) {
            if (check(new String(output))) {
                count ++;
            }
            return;
        }
        
        // 진행 조건
        for (int idx = 0; idx < friends.length; idx ++) {
            if (!visited[idx]) {
                visited[idx] = true;
                output[selectIdx] = friends[idx];
                permutation(depth + 1, selectIdx + 1);
                visited[idx] = false;
            }
        }
        return;
    }
    
    public int solution(int n, String[] data) {
        
        count = 0;
        condition = data;
        visited = new boolean[friends.length];
        output = new char[friends.length];
        
        permutation(0, 0);
        return count;
    }
}