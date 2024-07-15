import java.util.*;

/*
고속도로 이동 -> 모든 차량이 이용하면서 단속용 카메라를 한번 만나도록 카메라 설치
routes 매개변수로 주어질 때, 모든 차량이 한번은 단속용 카메라를 만나도록 하려면 최소 몇 대?

차량 진입 / 진출 시점
-20 -15 / - 18 -13/ -14 -5 / -5 -3 
*/
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int min = Integer.MIN_VALUE;
        // 0은 진입 1은 진출
        for (int idx = 0; idx < routes.length; idx ++ ) {
            if (min < routes[idx][0]) {
                min = routes[idx][1];
                answer ++;
            }
        }
        
        return answer;
    }
}