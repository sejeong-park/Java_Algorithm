import java.util.*;

/**
시소 하나 설치 
중심부터 2,3,4 거리에 좌석 하나씩있음
두명이 시소 마주보고 타는데, 시소가 평형인 상태에서 각각에 의해 시소에 걸리는 톹크의 크기가 상쇄되어
완전한 균형 > 시소 짝궁
탑승한 사람의 무게 + 시소 축과 좌석간의 거리의 곱

1. 무게 오름차순
2. 2/3 2/4 3/4 -> 비율은 다른 케이스도 동일
-> weights 길이가 길어서 시간초과 -> for 문 하나로 끝내야 한다.

*/

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();
        
        for (int weight: weights) {
            int count = 0;
            double d1 = weight * 1.0;  // 1:1
            double d2 = (weight * 2.0) / 3.0; // 2:3
            double d3 = (weight * 1.0) / 2.0; // 1:2
            double d4 = (weight * 3.0) / 4.0; // 3:4
            
            if (map.containsKey(d1)) {
                count += map.get(d1);
            }
            if (map.containsKey(d2)) {
                count += map.get(d2);
            }
            if (map.containsKey(d3)) {
                count += map.get(d3);
            }
            if (map.containsKey(d4)) {
                count += map.get(d4);
            }
            // double로 바꾸고, 
            map.put(weight * 1.0, map.getOrDefault(weight * 1.0, 0) + 1);
            answer += count;
        }
        
        return answer;
    }
}