import java.util.*;

/*
입국심사 - java

한 심사대 -> 동시에 한명만 심사
가장앞에 서있는 사람 -> 비어있는 심사대로 가서 심사 & 더 빨리 끝난다면 그 곳으로 ㄱㄱ
모든 사람이 심사 받는데 걸리는 시간 최소

1. 걸리는 시간의 최소와 최대 -> start / end
2. 이분탐색 시간 (mid) 를 사용하여, 심사위원들이 몇명 처리 가능?
3. 걸리는 시간 계속 이분탐색으로 최소시간 찾는다.

*/

class Solution {

    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times); // 시간 기준 정렬
        long start = 0;
        long end = (long) n * times[times.length - 1]; // 가장 오래 걸리는 시간
        while(start <= end){
            long mid = (start + end) / 2;
            long sum = 0; // 총 심사한 인원
            for (int idx = 0; idx < times.length; idx++){
                // 빨리 심사하는 심사관 순서대로 심사처리
                sum += mid / times[idx];
            }
            // 해야할 인원보다 심사처리 못함 -> 시간 더필요
            if (sum < n) {
                start = mid + 1;
            }else{
                // 해야할 인원보다 심사 처리 많이 함 -> 시간 줄여서 더 최고의 시간 만들기
                end = mid - 1;
                answer = mid;
            }
        }
        

        
        return answer;
    }
}