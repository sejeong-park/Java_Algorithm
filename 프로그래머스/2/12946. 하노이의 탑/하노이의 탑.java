import java.util.*;

/**
하노이탑
세개의 기둥과 이 기둥에 꽂을 수 있는 다ㅏ양한 원판
한 기둥에 원판들이 작은것이 위에 있도록 쌓임
1. 한번에 하나의 원판만 이동 가능
2. 큰 원판이 작은 원판 위에 있으면 안됨
최소 횟수로 이동
n 개의 원판을 3번 원판으로 옮기는 최소방법
움직인 순서 출력
*/


class Solution {
    
    private static List<int []> array = new ArrayList<>();
    
    /**
    n : 이동할 원판 개수
    start : 출발지
    mid : 경유지
    end : 도착지
    */
    void hanoi(int n, int start, int mid, int end) {
        if (n == 0) {
            return;
        }
        // n-1 개를 1번에서 2번으로 이동
        hanoi(n - 1, start, end, mid);
        // 1개를 1번에서 3번으로 이동 (가장 큰 원판)
        array.add(new int []{start, end});
        // n-1개를 2번에서 C번으로 이동
        hanoi(n - 1, mid, start, end);
        
    }
    
    public int[][] solution(int n) {
        hanoi(n, 1, 2, 3);
        
        int [][] answer = array.stream().toArray(int[][]::new);
        
        return answer;
    }
}