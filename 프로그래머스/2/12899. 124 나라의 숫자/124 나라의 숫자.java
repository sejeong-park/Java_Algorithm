import java.util.*;
class Solution {
    public String solution(int n) {
        String answer = "";
        int [] word = {4, 1, 2};
        
        while (n > 0) {
            int tmp = n % 3;
            answer = Integer.toString(word[tmp]) + answer;
            
            // 3으로 나누어떨어지면
            if (tmp == 0) {
                n--;
            }
            
            n /= 3;
        }
        
        return answer;
    }
}