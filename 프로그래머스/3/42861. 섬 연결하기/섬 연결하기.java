import java.util.*;

class Solution {
    
    static int size;
    static int [][] costs;
    static int[] parents; // 부모노드
    
    public static void init(int n) {
        size = n;
        parents = new int[n + 1];
        // 자연수 index 그대로 찾기 위해
        for (int idx = 1; idx < n + 1; idx ++) {
            parents[idx] = idx;
        }
        return;
    }
    
    public static int find(int x) {
        if (x == parents[x]) {
            return x;
        }    
        return parents[x] = find(parents[x]);
    }    
    
    public static boolean union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        
        if (root1 == root2) {
            return false;
        }
        
        if (root1 > root2) {
            parents[root2] = root1;
        } else {
            parents[root1] = root2;
        }
        
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        init(n);
        
        // 가중치 기준 오름차순
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);
        
        // union
        for (int idx = 0; idx < costs.length; idx ++) {
            // a,b
            if (union(costs[idx][0], costs[idx][1])) {
                answer += costs[idx][2];
            }
        }
        
        return answer;
    }
}