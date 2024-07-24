/**
자연수 n개로 이루어진 중복 집합
1. 각 원소의 합이 S가 되는 수의 집합
2. 위 조건을 만족하며 각 원소의 곱이 최대가 되는 집합
*/
import java.util.*;
class Solution {
    
 
    public int[] solution(int n, int s) {
        // 최고의 집합이 존재하지 않는 경우
        if (n > s) {
            return new int[]{-1};
        }
        
        int init = s / n;
        int mod = s % n;
        
        // answer을 init값으로 초기화
        int [] answer = new int[n];
        for (int idx = 0; idx < n; idx ++) {
            answer[idx] = init;
        }
        
        for (int idx = 0; idx < mod; idx ++) {
            answer[idx] ++;
        }
        
        Arrays.sort(answer);   
        
        return answer;
    }
}