import java.util.*;

class Solution {
    
    static ArrayList<ArrayList<Integer>> graph;
    static boolean [] visited;
    
    // 1번 노드로부터 가장 멀리 떨어진 노드가 몇개?
    private static int bfs() {
        
        int maxDistance = 0;
        int count = 0;
        
        Deque<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0}); // index & distance
        visited[1] = true;
        
        while(!queue.isEmpty()) {
            
            int [] now = queue.poll();
            
            // 만약 최대 길이의 노드라면
            if (maxDistance == now[1]) {
                count ++;
            }
            else if (maxDistance < now[1]) {
                maxDistance = now[1]; // 갱신
                count = 1; // 갱신 값으로 초기화
            }
            
            // 그래프 탐색 
            for (int idx = 0; idx < graph.get(now[0]).size(); idx ++) {
                int target = graph.get(now[0]).get(idx);
                if (!visited[target]) {
                    queue.add(new int[]{target, now[1] + 1}); // 길이 추가
                    visited[target] = true;
                }
            }
        }
    
        return count;
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        // 그래프 초기화
        graph = new ArrayList<>(); // 이차원 리스트 초기화
        for (int idx = 0; idx < n + 1; idx ++) {
            graph.add(new ArrayList<>());
        }
        
        // 양방향 그래프 연결
        for (int idx = 0; idx < edge.length; idx ++) {
            int a = edge[idx][0];
            int b = edge[idx][1];
            // 양방향 그래프 연결
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        // 방문 리스트 초기화
        visited = new boolean [n + 1];
        
        // BFS 탐색
        answer = bfs();
        
        return answer;
    }
}