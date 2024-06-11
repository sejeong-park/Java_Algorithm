import java.util.*;

/**
마법의 세계 사는 민수 높은 탑
마법의 엘베에서는  -1 +1 -10 +10 -100 +100 등과 같이 절대값

버튼을 누르면 현재 층수에 버튼에 적혀 잇는 값 + 더한 층
엘베 현재 층과 버튼 값을 더한 결과 <0 -> 엘베 움직이지 않음
0이 가장 아래 층 / 엘베 = 민수가 있는 층


*/

class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int num = storey % 10; // 일의 자리
            storey /= 10; // 일의자리를 제외함
            
            if (num > 5) {
                // 빼기
                answer += 10 - num;
                storey ++; // 앞자리 증가
            } else if (num < 5) {
                answer += num;
            
                // 5라면
            } else if (storey % 10 >= 5) {
                answer += 5;
                storey ++;
            } else {
                answer += 5;
            }
            
        }
        
        
        
        return answer;
    }
}