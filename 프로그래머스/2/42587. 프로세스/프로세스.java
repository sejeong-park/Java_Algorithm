import java.util.*;

/**
실행 대기 큐 - 대기중 프로세스 하나 꺼낸다.
큐에 대기중인 프로세스 우선순위가 더 높은 프로세스 있으면, -> 방금 꺼낸 프로세스를 다시 큐에 넣는다.
만약 그런 프로세스 없다면, 방금 꺼낸 프로세스를 실행함
- 한번 실행 프로세스 -> 다시 큐에 안넣고 종료
*/

class Solution {
    
    static Deque<Process> queue;
    static int answer;

    static class Process {
        int number;
        int priority;
        
        public Process(int number, int priority) {
            this.number = number;
            this.priority = priority;
        }
    }
    
    public static void init(int [] priorityies) {
        
        queue = new ArrayDeque<>();
        for (int idx = 0; idx < priorityies.length; idx ++) {
            queue.add(new Process(idx, priorityies[idx]));
        }
    }
    
    public static void run(int location) {
        
        while(!queue.isEmpty()) {
            Process current = queue.poll(); // 현재 프로세스
            
            // 큐에 대기중인 프로세스 중 우선순위 더 높은 게 있다면, 다시 큐에 넣음
            for (Process next : queue) {
                // 찾기 우선순위가 더 높은 프로세스가 있다면,
                if (current.priority < next.priority){
                    queue.add(current);
                    current = null; // 없어짐
                    break;
                }
            }
            
            // 그런 프로세스가 없다면,
            if (current != null) {
                // 실행하는 것과 현재가 동일하다면,
                if (current.number == location) {
                    return;
                } else {
                    answer += 1;
                }
            }
        }
        return;
    }
    
    
    public int solution(int[] priorities, int location) {
        answer = 0;
        
        init(priorities);
        run(location);
        
        return answer + 1;
    }
}