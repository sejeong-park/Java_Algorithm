import java.util.*;
import java.awt.*;
/**
1. 대기실은 5개 -> 5*5
2. 거리두기를 위해 응시자들 끼리 맨해튼 거리가 2이하로 앉으면 안된다.
3. 단, 응시자가 앉아 있는 자리 사이 파티션으로 막혀있는 경우는 허용
*/
class Solution {
    
    static char [][] map;
    static boolean [][] visited;
    
    static final char STUDENT = 'P', WALL = 'X', EMPTY = 'O';
    
    static int [] deltaRow = {-1, 1, 0, 0};
    static int [] deltaCol = {0, 0, -1, 1};
    
    public static boolean inMap(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }
    
    public static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
    
    public static boolean bfs(Point current) {
        boolean [][] visited = new boolean[5][5];
        
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(current);
        visited[current.x][current.y] = true;
        
        while(!queue.isEmpty()) {
            
            Point now = queue.poll();
            
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.x + deltaRow[direction];
                int nextCol = now.y + deltaCol[direction];
                
                if (!inMap(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] == WALL) continue;
            
                int distance = getDistance(current.x, current.y, nextRow, nextCol);
                
                if (map[nextRow][nextCol] == STUDENT && distance <= 2) {
                    return false;
                } 
                else if (map[nextRow][nextCol] == EMPTY && distance < 2) {
                    queue.add(new Point(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        
        return true;
    }
    
    
    public static int checkWaitingRoom(String [] place) {
        // 맵 만들기
        ArrayList<Point> student = new ArrayList<>(); // 응시자 들어있는 맵
        map = new char[5][5]; // 맵 초기화
        for (int row = 0; row < 5; row ++) {
            for (int col = 0; col < 5; col ++) {
                map[row][col] = place[row].charAt(col);
                if (map[row][col] == STUDENT) {
                    student.add(new Point(row, col));
                }
            }
        }

        for (Point person : student) {
            boolean flag = bfs(person);
            if (!flag) {
                return 0;
            }
        }
        return 1;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5]; // 대기실 5개
        for (int idx = 0; idx < 5; idx ++) {
            answer[idx] = checkWaitingRoom(places[idx]);
        }
        
        return answer;
    }
}