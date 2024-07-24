/**
야근 지수
dirms vlfheh
야근 시작한 시점에서 남은 일의 작업량을 제곱하여 더한 값
Demi N시간동안 야근 피로도 최소화 하도록 일할 것
Demi가 1시간동안 작업량 1만큼 처리할 수 있다고 할 때
퇴근까지 남은 N 시간과 각 일에 대한 작업량 works
*/
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        // 최대 힙
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        // 초기화
        for (int work : works) {
            queue.add(work);
        }
        
        // 작업시간 n이 양수일때까지만 유효
        while(n > 0) {
            int work = queue.poll();
            if (work == 0) break; // 더 줄일 수 있는 일이 없다.
            queue.add(work - 1);
            n -= 1; // 남은 추가작업량 감소
        }
        
        // queue가 비지 않을 때까지
        while(!queue.isEmpty()) {
            int work = queue.poll();
            answer += Math.pow(work, 2);
        }        
        return answer;
    }
}