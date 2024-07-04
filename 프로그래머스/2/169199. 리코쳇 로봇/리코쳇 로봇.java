import java.util.*;
import java.awt.*;
/**
최소 몇번만에 도달?
. : 빈 공간
R : 로봇의 첫 위치
D : 장애물의 위치
G : 목표 지점
*/

class Solution {
    // 상 하 좌 우
    static int [] deltaRow = {-1, 1, 0, 0};
    static int [] deltaCol = {0, 0, -1, 1};
    
    static final char START = 'R';
    static final char WALL = 'D';
    static final char END = 'G';
    
    static char [][] map;
    static int [][] visited;
    
    static int rowSize;
    static int colSize;
    
    static Point start;
    static Point end;
    
    static int max = Integer.MIN_VALUE;
    
    public static boolean inMap(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }
    
    public static int bfs() {
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.x][start.y] = 1;
        
        while(!queue.isEmpty()) {
            
            Point now = queue.poll();
            
            // 확인
            if (now.x == end.x && now.y == end.y) {
                return visited[now.x][now.y] - 1;
            }
            
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.x;
                int nextCol = now.y;
                // 한 챕터가 움직이는 칸까지
                while(true) {
                    if (inMap(nextRow, nextCol) && map[nextRow][nextCol] != WALL) {
                        nextRow += deltaRow[direction];
                        nextCol += deltaCol[direction];
                    }
                    else {
                        // 방문초과시 
                        nextRow -= deltaRow[direction];
                        nextCol -= deltaCol[direction];
                        break; // 종료
                    }
                }

                // 방문한 적이 없다면
                if (visited[nextRow][nextCol] != 0) continue;
                
                queue.add(new Point(nextRow, nextCol));
                visited[nextRow][nextCol] = visited[now.x][now.y] + 1;
            }
        }
        
        return -1;
    }
    
    
    public int solution(String[] board) {
        int answer = 0;
        
        rowSize = board.length;
        colSize = board[0].length();
        
        map = new char[rowSize][colSize];
        visited = new int[rowSize][colSize];
        
        for (int row = 0; row < rowSize; row ++) {
            for (int col = 0; col < colSize; col ++) {
                map[row][col] = board[row].charAt(col);
                if (map[row][col] == START) {
                    start = new Point(row, col);
                }
                if (map[row][col] == END) {
                    end = new Point(row, col);
                }
            }
        }
        
        answer = bfs();
        
        return answer;
    }
}