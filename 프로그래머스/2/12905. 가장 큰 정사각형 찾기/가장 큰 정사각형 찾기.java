import java.util.*;
/**
가장 큰 정사각형 찾기
1, 0 -> 1로 이루어진 가장 큰 정사각형
*/

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        
        int rowSize = board.length;
        int colSize = board[0].length;
        
        // board의 크기에 따른 예외
        if (rowSize < 2 || colSize < 2) {
            return 1;
        }
        
        for (int row = 1; row < rowSize; row ++) {
            for (int col = 1; col < colSize; col ++) {
                // 0은 취급 하지 않는다.
                if (board[row][col] == 0) continue;
                // 1일 경우
                // 왼쪽 상단, 왼쪽, 위쪽 중 최소값 구해 + 1 후 할당
                board[row][col] = Math.min(board[row - 1][col - 1], Math.min(board[row][col - 1], board[row - 1][col])) + 1;
                // 갱신
                if (answer < board[row][col]) answer = board[row][col];
            }
            
        }
        

    
        return answer * answer;
    }
}