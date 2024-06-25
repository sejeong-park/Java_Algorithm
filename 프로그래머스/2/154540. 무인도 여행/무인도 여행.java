import java.util.*;
import java.awt.*;

/**
상하좌우
칸의 숫자 = 식량
각 섬에서 최대 며칠씩 머무를 수 있는지
*/
class Solution {
    
    static int [][] map;
    static int rowSize, colSize;
    static boolean [][] visited;
    
    static ArrayList<Integer> list;
    
    static int [] deltaRow = {-1, 1, 0, 0};
    static int [] deltaCol = {0, 0, -1, 1};
    
    public static boolean inMap(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }
    
    public static int bfs(int row, int col) {
        
        int totalCnt = map[row][col];
        
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(row, col));
        visited[row][col] = true;
        
        while(!queue.isEmpty()) {
            
            Point now = queue.poll();
            
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.x + deltaRow[direction];
                int nextCol = now.y + deltaCol[direction];
                
                if (!inMap(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] == -1) continue;
                
                queue.add(new Point(nextRow, nextCol));
                visited[nextRow][nextCol] = true;
                totalCnt += map[nextRow][nextCol];
            }
        }
        
        return totalCnt;
    }
    
    public static void run() {
        // 찾기
        visited = new boolean[rowSize][colSize];
        
        for (int row = 0; row < rowSize; row ++) {
            for (int col = 0; col < colSize; col ++) {
                
                if (!visited[row][col] && map[row][col] != -1) {
                    int cnt = bfs(row, col);
                    if (cnt > 0) {
                        list.add(cnt);
                    }
                }
            }
        }    
    }
    
    
    public int[] solution(String[] maps) {
        
        list = new ArrayList<>();
        
        rowSize = maps.length;
        colSize = maps[0].length();
        map = new int[rowSize][colSize];
        
        for (int row = 0; row < maps.length; row ++) {
            String line = maps[row];
            for (int col = 0; col < line.length(); col ++) {
                char eat = line.charAt(col);
                if (eat == 'X') {
                    map[row][col] = -1;
                } else {
                    map[row][col] = eat - '0';
                }
            }
        }
    
        run();
        Collections.sort(list);
        
        if (list.size() == 0) {
            list.add(-1);
        }
        
        int [] answer = new int[list.size()];
        for (int idx = 0; idx < list.size(); idx ++) {
            answer[idx] = list.get(idx);
        }
        return answer;
    }
}