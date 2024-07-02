import java.util.*;

/**
n개의 송전탑 -> 전선을 통해 하나의 트리 형태
하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하고자 함
*/

class Solution {
    static int answer;
    static int [][] wires;
    static int [][] map;
    static int mapSize;
    
    public static int bfs(int start) {
        
        // 방문
        boolean [] visited = new boolean[mapSize + 1];
        int count = 1;
        
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int now = queue.poll();
            
            for (int idx = 1; idx <= mapSize; idx ++) {
                if (map[now][idx] == 0 || visited[idx]) continue;
                queue.add(idx);
                visited[idx] = true;
                count += 1; // 개수 세기
            }
            
        }
        
        // 결과
        return (int) Math.abs(count - (mapSize - count)); // 두 사이의 차
    }
    
    public static void run() {
        answer = Integer.MAX_VALUE;
        for (int idx = 0; idx < wires.length; idx ++) {
            int from = wires[idx][0];
            int to = wires[idx][1];
            
            // 1. 선 끊기
            map[from][to] = 0;
            map[to][from] = 0;
            
            // 2. BFS 탐색하여 최소값 구하기
            int diff = bfs(from);
            answer = Math.min(answer, diff);
            
            // 3. 선 재연결
            map[from][to] = 1;
            map[to][from] = 1;
        }
        
    }
    

    public int solution(int n, int[][] wires) {

        mapSize = n;
        map = new int [mapSize + 1][mapSize + 1];
        this.wires = wires;
        
        // wire 연결 관계 확인
        for (int idx = 0; idx < wires.length; idx ++) {
            // 연결
            int from = wires[idx][0];
            int to = wires[idx][1];
            
            // 연결 성립
            map[from][to] = 1; 
            map[to][from] = 1;
        }
        
        run();
        
        return answer;
    }
}