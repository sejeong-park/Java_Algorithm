import java.util.*;

/**
x축과 y축
원점이 서로 다른 크기의 원
1. 1사분면만 계산하여 * 4
2. x, y축 위에 있는 좌표는 중복 계산이 된다.
3. 마지막 축 위에 있는 개수를 뺀다.

원의 방정식
x^2 + y^2 = r ^ 2

참고 블로그 : https://tu-tr.tistory.com/172
*/

class Solution {
    
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long r1Pow = (long) Math.pow(r1, 2);
        long r2Pow = (long) Math.pow(r2, 2);
        
        int count = 0;
        for (long x = 0; x <= r2; x ++) {
            long y2 = (long) Math.sqrt(r2Pow - x * x);
            long y1 = (long) Math.sqrt(r1Pow - x * x);
            
            if (Math.sqrt(r1Pow - x * x) % 1 == 0) {
                count ++;
            }
            
            // 테두리 
            answer += (y2 - y1) * 4; // 4사분면
        }
        answer += count * 4;
        answer -= 4; // 중복 제거 (x == r2 y == 0 && x == 0 y == r2)
        
        return answer;
    }
}