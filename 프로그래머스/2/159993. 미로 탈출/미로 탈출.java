import java.util.*;
import java.awt.*;
/**
1 * 1 -> 직사각형 -> 미로 탈출
칸은 통로 또는 벽 -> 벽은 지나갈 수 없고, 통로로 된 칸으로만 이동
통로들 중 한칸에는 미로를 빠져나가는 문
미로를 빠르게 빠져나가는 데 걸리는 시간
*/

class Solution {
    
    static char [][] map;
    static int [][] visited;
    
    static int [] deltaRow = {-1, 1, 0, 0};
    static int [] deltaCol = {0, 0, -1, 1};
    
    static Point start;
    static Point end;
    static Point middle;
    
    
    public static void print(int [][] map) {
        for (int row = 0; row < map.length; row ++) {
            System.out.println(Arrays.toString(map[row]));
        }
        System.out.println();
    }
    
    
    public static boolean inMap(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length;
    }
    
    public int bfs(int row, int col, char target) {
        
        visited = new int[map.length][map[0].length];
        
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(row, col));
        
        while(!queue.isEmpty()) {
            
            Point now = queue.poll();
            
            // 확인
            if (map[now.x][now.y] == target) {
                
                return visited[now.x][now.y];
            }
            
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.x + deltaRow[direction];
                int nextCol = now.y + deltaCol[direction];
                
                if (!inMap(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol] != 0 || map[nextRow][nextCol] == 'X') continue;

                queue.add(new Point(nextRow, nextCol));
                visited[nextRow][nextCol] = visited[now.x][now.y] + 1;
            }
            
        }
        
        return -1;
    }
    
    public int solution(String[] maps) {
        int answer = 0;
        
        // map
        map = new char [maps.length][maps[0].length()];
        for (int row = 0; row < maps.length; row ++) {
            String line = maps[row];
            for (int col = 0; col < maps[0].length(); col ++) {
                map[row][col] = line.charAt(col);
                
                // 값 찾기
                if (map[row][col] == 'S') {
                    start = new Point(row, col);
                }
                if (map[row][col] == 'L') {
                    middle = new Point(row, col);
                }
                if (map[row][col] == 'E') {
                    end = new Point(row, col);
                }
            }
        }
        
        int firstCnt = bfs(start.x, start.y, 'L');
        if (firstCnt == -1) {
            return -1;
        }
        
        int secondCnt = bfs(middle.x, middle.y, 'E');
        if (secondCnt == -1) {
            return -1;
        }
      
        return firstCnt + secondCnt;
    }
}