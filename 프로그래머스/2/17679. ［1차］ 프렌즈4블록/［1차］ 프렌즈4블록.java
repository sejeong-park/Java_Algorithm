/**
프렌즈 4블록
*/
import java.util.*;
import java.awt.*;

class Solution {
    
    static int count;
    
    static char [][] map;
    static int rowSize, colSize;
    
    static boolean [][] visited;
    
    static boolean game;
    static ArrayList<Point> deleteList;
    
    static final char EMPTY = '#'; // empty를 의미
    
    public boolean checkSame(int row, int col) {
        if ((map[row][col] == map[row][col + 1]) 
           && (map[row][col] == map[row + 1][col])
            && (map[row][col] == map[row + 1][col + 1])) {
            return true;
        }
        return false;
    }
    
    public void addDeleted(int row, int col) {
        if (!visited[row][col]) {
            deleteList.add(new Point(row, col));
        }
        visited[row][col] = true;
    }
    
    public void findTarget() {
        
        // 한 루프에서 찾을 객체 생성
        visited = new boolean[rowSize][colSize];
        deleteList = new ArrayList<>();
        
        // 찾기
        for (int row = 0; row < rowSize - 1; row ++) {
            for (int col = 0; col < colSize - 1; col ++) {
                if (map[row][col] == EMPTY) continue;
                if (!checkSame(row, col)) continue; // 2 * 2 확인
                // 같다면
                addDeleted(row, col);
                addDeleted(row, col + 1);
                addDeleted(row + 1, col);
                addDeleted(row + 1, col + 1);
            }
        }
        
        if (deleteList.size() == 0){
            game = false;
        }
        
        // 지울 것 개수 세기
        count += deleteList.size(); 
    }
    
    // 2 * 2 배열을 지운다. -> EMPTY
    public void delete() {
        for (int idx = 0; idx < deleteList.size(); idx ++) {
            Point point = deleteList.get(idx);
            map[point.x][point.y] = EMPTY;
        }
    } 
    
    // 중력에 의해 떨어짐 
    public void getGravity() {
        // 열 기준으로 재배열
        for (int col = 0; col < colSize; col ++) {
            int emptyCnt = 0;
            for (int row = rowSize - 1; row >= 0; row --) {
                if (map[row][col] != EMPTY) {
                    if (emptyCnt > 0) {
                        map[row + emptyCnt][col] = map[row][col];
                        map[row][col] = EMPTY;
                    }
                } else {
                    emptyCnt ++;
                }
            }
        }         
    }
    
    // public void print() {
    //     for (int row = 0; row < rowSize; row ++) {
    //         System.out.println(Arrays.toString(map[row]));
    //     }
    // }
    
    public void run() {
        
        game = true; // 시작
        while(game){
            findTarget();
            delete();
            getGravity();
        }
    }
    
    public int solution(int m, int n, String[] board) {
        count = 0;
        
        // 맵 정보 할당
        rowSize = m;
        colSize = n;
        
        map = new char[rowSize][colSize];
        for (int idx = 0; idx < rowSize; idx ++) {
            map[idx] = board[idx].toCharArray();
        }
        
        run();
        
        return count;
    }
}