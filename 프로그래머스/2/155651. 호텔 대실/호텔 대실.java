import java.util.*;

/**
최소 객실만 사용 -> 예약 손님
한번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소 
*/

class Solution {
    
    static int parseTime(String time) {
        String [] hhmm = time.split(":");
        int hour = Integer.parseInt(hhmm[0]);
        int minuate = Integer.parseInt(hhmm[1]);
        return hour * 60 + minuate;
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        int size = book_time.length;
        int [][] bookTime = new int[size][2];
        
        for (int idx = 0; idx < size; idx ++){
            bookTime[idx][0] = parseTime(book_time[idx][0]); // start time
            bookTime[idx][1] = parseTime(book_time[idx][1]) + 10; // end time + cleanning
        }
        
        // startTime 기준
        Arrays.sort(bookTime, (a,b) -> a[0] - b[0]); 
        
        // 시간표가 몇개 테이블 있는지 -> 우선순위는 endTime
        PriorityQueue<int []> queue = new PriorityQueue<>((a,b) -> a[1] - b[1]); 
        
        for (int idx = 0; idx < size; idx ++) {
            // queue에서 첫번째 endTime이 idx의 startTime보다 작은 경우
            while(!queue.isEmpty() && queue.peek()[1] <= bookTime[idx][0]) {
               queue.poll(); // 제일 위에 제거
            }
            // queue에 추가
            queue.add(bookTime[idx]);
            answer = Math.max(answer, queue.size());
        }
        
        
        return answer;
    }
}