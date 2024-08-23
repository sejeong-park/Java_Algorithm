import java.util.*;


class Solution {
    

    public int solution(int[][] jobs) {
        int answer = 0;
        
        int jobCnt = jobs.length;
        // 요청 시작 시간 기준 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); 
        // 소요 시간 기준 오름차순 정렬
        PriorityQueue<int []> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]); // 시간 기준

        int index = 0;
        int end = 0;
        int total = 0;
        int count = 0;
        
        while (count < jobCnt) {
            
            // index 기준 pq에 넣기
            while (index < jobCnt && jobs[index][0] <= end) {
                queue.add(jobs[index++]);
            }
            
            // 큐에 작업이 없다면, 작업 요청시점이 가장 빠른 작업 추가
            if (queue.isEmpty()) {
                end = jobs[index][0];
            } 
            // 큐에 작업이 있다면, 작업 소요시간이 가장 빠른 작업 수행
            else {
                int [] now = queue.poll();
                total += now[1] + end - now[0];
                end += now[1]; // 시간 
                count ++;
            }
            
        }
        
        return total / jobCnt;
    }
}