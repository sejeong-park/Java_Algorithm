import java.util.*;

/**
N-Queen (유명한 백트래킹 문제 )
*/
class Solution {
    
    static int [][] map;
    static int size;
    static int answer;
    
    // 해당 자리에 Queen이 위치할 수 있는 지 조건 확인
    private static boolean isPosition(int row, int col) {
        // 세로로 위에 & 좌우 대각선으로 1이 있으면 위치하지 못함
        
        for (int idx = 0; idx < row; idx ++) { // row 기준 탐색
            // 세로 확인 
            if (map[idx][col] == 1) 
                return false;
            // 좌측 대각선 확인
            int rowIdx = row - idx; // 유효 범위인지 확인
            if (col - rowIdx >= 0 && map[idx][col - rowIdx] == 1) 
                return false;
            // 우측 대각선 확인
            if (col + rowIdx < size && map[idx][col + rowIdx] == 1) 
                return false;
        }
        return true;
    }
    
    private static void backTracking(int depth) {
        // 만약 size 줄만큼 돌았다면,
        if (depth == size) {
            answer ++; 
            return;
        }
        
        // nQueen 배치 여부 (행 기준)
        for (int col = 0; col < size; col ++) {
            // 만약 가능하다면
            if (isPosition(depth, col)) {
                map[depth][col] = 1;
                backTracking(depth + 1); // 한줄씩 깊이 탐색
                map[depth][col] = 0; // 초기화
            }
        }
    }
    
    
    
    public int solution(int n) {
        answer = 0;
        
        // 초기화 
        map = new int[n][n];
        size = n;
        
        backTracking(0); 
        
        return answer;
    }
}