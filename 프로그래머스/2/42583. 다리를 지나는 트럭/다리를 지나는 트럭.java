/*
트럭 여러 대 강 가로질러 -> 몇초?

*/
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        // 2대 
        Queue<Integer> queue = new LinkedList<Integer>();
        int sum = 0;
        
        for (int truck : truck_weights) {
            
            // 큐 확인
            while(true) {
                // 큐가 비었다면 다음 트럭 넣기
                if (queue.isEmpty()) {
                    queue.add(truck);
                    sum += truck;
                    answer++;
                    break;
                }
                else if (queue.size() == bridge_length) {
                    sum -= queue.poll();
                }
                // 큐가 비어있지 않는 다면
                else {
                    if (sum + truck > weight) {
                        queue.offer(0);
                        answer++;
                    }
                    // 다음 트럭
                    else {
                        queue.add(truck);
                        sum += truck;
                        answer++;
                        break;
                    }
                }
                
            }
        }
        
        
        return answer + bridge_length;
    }
}