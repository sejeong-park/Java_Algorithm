import java.util.*;
class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        
        int [] dart = new int[3];
        String numStr = "";
        int n = 0;
        int checked = 0;
        
        for (int idx = 0; idx < dartResult.length(); idx++) {
            char c = dartResult.charAt(idx);
            
            if ('0' <= c && c <= '9') {
                numStr += String.valueOf(c);
            }
            
            else if (c == 'S' || c == 'D' || c == 'T') {
                n = Integer.parseInt(numStr);
                if (c == 'S') {
                    dart[checked ++] = (int) Math.pow(n, 1);
                } 
                else if (c == 'D') {
                    dart[checked ++ ] = (int) Math.pow(n, 2);
                } else {
                    dart[checked ++] = (int) Math.pow(n, 3); 
                }
                numStr = "";
            }
            else {
                if (c == '*') {
                    dart[checked-1] *=2;
                    if (checked - 2 >= 0){
                        dart[checked - 2] *= 2;
                    }
                } else {
                    dart[checked - 1] *= (-1);
                }
            }
            
        }
        
        answer = dart[0] + dart[1] + dart[2];
        return answer;
    }
}