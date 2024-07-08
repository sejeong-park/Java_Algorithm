/** 디펜스 게임
준호는 디펜스 게임 -> n명으로 연속되는 적의 공격을 순서대로 막는다.
매 라운드마다 enumy[i]마리의 적
남은 병사 중 enemy[i]명 만큼 소모하여 enemy[i]마리의 적을 막을 수 잇다.
*/
import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int idx = 0; idx < enemy.length; idx ++) {
            queue.add(enemy[idx]);
            if (queue.size() > k) {
                n -= queue.poll();
            }
            
            if (n < 0) {
                answer = idx;
                break;
            }
        }
        return answer;
    }
}