/**
길이가 같은 두개의 큐
// long 타입 
*/

import java.util.*;

class Solution {
    
    static Deque<Integer> q1;
    static Deque<Integer> q2;
    
    // queue의 합을 산술하는 메서드
    public long getQueueSum(int [] queue) {
        long sum = 0; //queue의 전체 합
        for (int idx = 0; idx < queue.length; idx++) {
            sum += (long) queue[idx];            
        }
        return sum;
    }
    
    
    public int solution(int[] queue1, int[] queue2) {
        
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        // queue 초기화
        for (int idx = 0; idx < queue1.length; idx ++) {
            q1.add(queue1[idx]);
            q2.add(queue2[idx]);
        }
        
        // Queue의 합 구하기
        long queueSum1 = getQueueSum(queue1);
        long queueSum2 = getQueueSum(queue2);
        
        // 두 큐의 합이 같을 때까지 반복
        int cnt = 0; // 반복을 도는 횟수 카운트
        while(queueSum1 != queueSum2) {
            
            if (cnt > (queue1.length + queue2.length) * 2) { // 더 순회해도 만들지 못함
                return -1;    
            }
            
            
            int tmp = 0;
            // queue2의 합이 더 클 때
            if (queueSum1 < queueSum2) {
                tmp = q2.poll(); // 뽑는 것
                q1.add(tmp);
                queueSum1 += (long) tmp;
                queueSum2 -= (long) tmp;
            } else if (queueSum1 > queueSum2) {
                tmp = q1.poll();
                q2.add(tmp);
                queueSum1 -= (long) tmp;
                queueSum2 += (long) tmp;
            } else {
              return cnt; // 두 큐의 합이 같다면 리턴  
            }
            cnt ++;
            
            
        }
    
        
        return cnt;
    }
}