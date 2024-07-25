import java.util.*;

class Solution {
    
    static PriorityQueue<Task> queue;
    static Stack<Task> stack;
    static ArrayList<String> doneList;
    
    // task class
    class Task implements Comparable<Task> {
        String name;
        int start;
        int playtime;
        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
        
        @Override
        public int compareTo(Task o) {
            return this.start - o.start;
        }
    }
    
    // change time from String to int
    public int changeToTime(String time) {
        String [] timeList = time.split(":");
        int hh = Integer.parseInt(timeList[0]);
        int mm = Integer.parseInt(timeList[1]);
        return hh * 60 + mm;
    }
    
    public void run() {
        
        // 초기화
        stack = new Stack<>();
        doneList = new ArrayList<>();
        
        // 진행 중 탐색
        while(!queue.isEmpty()) {
            
            Task now = queue.poll(); // 현재 과제
            
            // 새로운 과제를 할 것이 없을 때
            if (queue.isEmpty()) {
                if (stack.isEmpty()) { // 멈춘 과제가 없다면
                    doneList.add(now.name);
                }
                else { // 멈춘 과제가 있다면
                    doneList.add(now.name); 
                    while(!stack.isEmpty()) { // 멈춘 과제 최신 순서대로 소진
                        doneList.add(stack.pop().name);
                    }
                }
                continue; // 종료
            }
            
            // 진행 조건
            
            Task next = queue.peek(); // 새로운 과제
            int countTime = now.start; // 과제 시간 카운트
            
            // STEP 1 : 새로운 과제 시작 시간 전에 진행 중인 과제를 끝낸다.
            if (now.start + now.playtime <= next.start) {
                doneList.add(now.name); 
                countTime += now.playtime;
                if (countTime == next.start) continue;
                
                // STEP 2 : 시간이 남을 경우 -> 멈춰둔 과제를 이어서 진행한다.
                while(!stack.isEmpty()) {
                    
                    Task recent = stack.pop(); // 가장 최근에 멈춘 과제
                    
                    // STEP 3 : 멈춰둔 과제를 진행한다.
                    if (recent.playtime + countTime <= next.start) {
                        doneList.add(recent.name);
                        countTime += recent.playtime;
                    } else {
                        // STEP 4 : 멈춰둔 과제를 다 끝내지 못했다면, 남은 시간만큼 과제 스택에 넣고 반복을 종료한다.
                        int remain = recent.playtime + countTime - next.start;
                        recent.playtime = remain;
                        stack.push(recent);
                        break;
                    }
                }
                
            } else {
                // STEP 5 : 새로운 과제를 시작할 시각이 되었을 때, 기존 진행 중인 과제를 멈춘다.
                int remain = now.start + now.playtime - next.start; // 남은 과제의 양
                now.playtime = remain;
                stack.push(now); 
            }
        }
    }
    

    public String[] solution(String[][] plans) {
        String[] answer = {};
        
        // init
        queue = new PriorityQueue<>();
        for (int idx = 0; idx < plans.length; idx ++) {
            String name = plans[idx][0];
            int start = changeToTime(plans[idx][1]);
            int playtime = Integer.parseInt(plans[idx][2]);
            queue.add(new Task(name, start, playtime));
        }
        
        // main logic
        run();
        
        // list to array
        answer = new String[doneList.size()];
        for (int idx = 0; idx < doneList.size(); idx ++) {
            answer[idx] = doneList.get(idx);
        }        
        
        return answer;
    }
}