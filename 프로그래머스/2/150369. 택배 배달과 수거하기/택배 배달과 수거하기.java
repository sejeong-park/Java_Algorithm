import java.util.*;
/**
배달할 물건 택배 상자에 담아 배달 & 배달을 다니면서 빈 재활용 택바 상자 수거
cap : 트럭에 실을 수 있는 택배 상자의 수
최소 이동 거리
각 집에 배달 및 수거 할 때, 원하는 개수 가능
어차피 왕복 *2 
*/
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0; // 이동 distance
        
        int delCnt = 0;
        int pickCnt = 0;
        
        for (int distance = 0; distance < n; distance ++) {
            // 뒤에서부터 카운트
            delCnt += deliveries[n - distance - 1];
            pickCnt += pickups[n - distance - 1];
            
            while (delCnt > 0 || pickCnt > 0) {
                delCnt -= cap;
                pickCnt -= cap;
                answer += (n - distance) * 2;
            }
        }
        
        return answer;
    }
}