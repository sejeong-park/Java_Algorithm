import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int [] answer = new int[2];
        
        Set<String> set = new HashSet<>(Arrays.asList(gems)); // 중복 없는 보석 종류
        Map<String, Integer> map = new HashMap<>(); // 보석의 종류와 개수
        
        int start = 0;
        int size = Integer.MAX_VALUE;
        for (int end = 0; end < gems.length; end ++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
            
            // start번째 보석이 중복이라면? -> 보석 개수 줄이고, startIndex 조절
            while(map.get(gems[start]) > 1) {
                map.put(gems[start], map.getOrDefault(gems[start], 0) - 1);
                start ++;
            }
            
            // 1. map의 개수와 보석의 개수 비교
            // 2. 가장 짧은 구간 (size가 작아야함)
            if (map.size() == set.size() && size > end - start){
                size = end - start;
                answer[0] = start + 1; //자연수
                answer[1] = end + 1;
            }
        }
        
        return answer;
    }
}