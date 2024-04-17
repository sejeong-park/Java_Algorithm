/*
네트워크 - 컴퓨터 상호 간의 정보 교환 연결된 형태
a - b 직접 연결
B - C 직접 연결
A - C 간접 연결
-> 같은 네트워크 안에 있음

컴퓨터 개수 N 개 & 연결정보 COMPUTER

네트워크 개수는 몇개 인가?

// 묶음의 개수를 구해야 한다.
**/

import java.util.*;

class Solution {
    
    static boolean[] visited;
    static int[][] computers;
    static int totalCnt = 0;
    static int n;
    static final int EXIST = 1;
    static final int NONE = 0;
    
    static void dfs(int nodeIdx) {
        // 자기자신 탐색
        visited[nodeIdx] = true;
        // 카운트 조건
        for (int idx = 0; idx < n; idx++) {
            // 연결 여부 
            if (computers[nodeIdx][idx] == EXIST && !visited[idx]) {
                // 다시 dfs로 탐색
                dfs(idx);
            }
        }
            
    }
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        visited = new boolean[n];
        
        for (int nodeIdx = 0; nodeIdx < n; nodeIdx++) {
            // 넘기는 노드가 방문하지 않았다면  
            
            if (!visited[nodeIdx]) {
                dfs(nodeIdx);
                totalCnt ++;
            }
        }

        
        
        return totalCnt;
    }
    
}