import java.util.*;
class Solution {
    
    static char[][] map;
    static int count_O = 0;
    static int count_X = 0;
    
    public static void count() {
        for (int row = 0; row < 3; row ++) {
            for (int col = 0; col < 3; col ++) {
                if (map[row][col] == 'O') count_O ++;
                if (map[row][col] == 'X') count_X ++;
             }
        }
    }
    
    public static boolean hasWin(char ch) {
        for (int idx = 0; idx < 3; idx ++) {
            // row
            if (map[idx][0] == ch && map[idx][1] == ch && map[idx][2] == ch) return true;
            // col
            if (map[0][idx] == ch && map[1][idx] == ch && map[2][idx] == ch) return true;
        }
        // 대각선
        if (map[0][0] == ch && map[1][1] == ch && map[2][2] == ch) return true;
        if (map[0][2] == ch && map[1][1] == ch && map[2][0] == ch) return true;
        return false;
    }
    
    
    public int solution(String[] board) {
        int answer = -1;
        
        map = new char[3][3];
        for (int row = 0; row < 3; row ++) {
            map[row] = board[row].toCharArray();
        }
        
        count(); // 갯수 계산
        
        // X가 많거나, 2턴 이상 차이나면 오류
        if (count_O < count_X || count_O - count_X > 1) {
            return 0;
        }
        // O가 완성 되었을때, X가 O의 개수와 같으면 규칙 위반
        if (hasWin('O')) {
            if (count_O == count_X) return 0;
        }
        // X가 완성 되었을 때, O가 X보다 1개 많으면 규칙 위반
        if (hasWin('X')) {
            if (count_O == count_X + 1) return 0;
        }
        
        
        return 1;
    }
}