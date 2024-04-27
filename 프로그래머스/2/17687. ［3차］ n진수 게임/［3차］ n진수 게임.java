import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder convert = new StringBuilder();
        
        // 
        for (int idx = 0; convert.length() <= t * m ; idx ++) {
            convert.append(Integer.toString(idx, n));
        }
        
        for (int idx = p - 1; answer.length() < t; idx += m) {
            answer.append(convert.charAt(idx));
        }
        return answer.toString().toUpperCase();
    }
}