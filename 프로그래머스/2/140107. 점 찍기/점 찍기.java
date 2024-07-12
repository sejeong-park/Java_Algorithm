import java.util.*;
/**
좌표 평면 좋아하는 진수는 x축과 y축이 직교하는 2차원 평면
k, d가 주어질 때 점을 찍으려한다.

원점 (0,0)으로부터 x축 방향으로 a * k, y축 방향으로 b * k
원점과 거리가 d를 넘는 위치에는 점을 찍지 않는다.
*/

class Solution {
    public long solution(long k, long d) {
        long answer = 0;

        for(long x = 0; x <= d; x += k){
            long maxY = (long) Math.sqrt(d*d - x*x); 
            answer += maxY / k + 1;
        }
        
        return answer;
    }
}