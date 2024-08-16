import java.util.*;
import java.awt.*;

/**
그림에 몇개의 영역이 있는지 / 가장 큰 영역의 넓이는 얼마인지
*/
class Solution {
    
    // map 탐색 변수
    static int rowSize, colSize;
    static int [][] map;
    static boolean [][] visited;
    static int max;
    
    // 기본 값
    static int [] deltaRow = {-1, 1, 0, 0};
    static int [] deltaCol = {0, 0, -1, 1};
    static final int EMPTY = 0;
    
    public static boolean inMap(int row, int col) {
        return 0 <= row && row < rowSize && 0<= col && col < colSize;
    }
    
    public static void bfs(int row, int col) {
        Deque<Point> queue = new ArrayDeque<>(); // 큐
        queue.add(new Point(row, col));
        int color = map[row][col];
        int size = 0; 
        
        while(!queue.isEmpty()) {
            Point now = queue.poll(); 
            
            // 이동
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.x + deltaRow[direction];
                int nextCol = now.y + deltaCol[direction];
                
                // 확인
                if (!inMap(nextRow, nextCol) || visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] != color) continue;
                
                visited[nextRow][nextCol] = true;
                queue.add(new Point(nextRow, nextCol));
                size ++;
            }
        }
        
        max = Math.max(max, size); // 최대 색칠 칸 비교
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        // 내가 사용하기 쉬운 변수명으로 변경 & 초기화
        rowSize = m;
        colSize = n;
        map = picture;
        visited = new boolean[rowSize][colSize];
        max = Integer.MIN_VALUE;
        
        // BFS 시작 
        for (int row = 0; row < rowSize; row ++) {
            for (int col = 0; col < colSize; col ++) {
                if (map[row][col] == EMPTY || visited[row][col]) continue;
                bfs(row, col); // BFS 탐색
                numberOfArea ++;   
            } 
        }
        maxSizeOfOneArea = max; // 최댓값 갱신
        
        // default
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}