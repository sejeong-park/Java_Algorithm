import java.util.*;
/**
h-index : h 찾기
1. n 편중 h 번 이상 인용된 논문이 h편 이상
논문의 인용 횟수를 담은 배열 
*/
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations); // 논문의 인용 횟수 
        
        for (int idx = 0; idx < citations.length; idx ++) {
            int count = citations.length - idx; // h편 이상 인용된 논문 수
            int hIndex = Math.min(citations[idx], count); 
            answer = Math.max(answer, hIndex);
        }
        
        return answer;
    }
}