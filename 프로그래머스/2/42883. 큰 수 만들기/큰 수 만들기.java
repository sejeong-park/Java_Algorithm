import java.util.*;
class Solution {
    
    public String solution(String number, int k) {

        Deque<Character> queue = new ArrayDeque<>();
        
        int removeCnt = 0;
        
        for (char digit: number.toCharArray()) {
            while (!queue.isEmpty() && removeCnt < k && queue.peekLast() < digit) {
                queue.pollLast();
                removeCnt ++;
            }
            queue.addLast(digit);
        }
        
        while (removeCnt < k) {
            queue.pollLast();
            removeCnt ++;
        }
        
        StringBuilder answer = new StringBuilder();
        for (char digit : queue){
            answer.append(digit);
        }
        
        return answer.toString();
    }
}