

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /***
     * BOJ_2580_스도쿠
     * - 9*9 = 81개 판
     * 1. 각각 가로줄과 세로줄에는 1 ~ 9 숫자 한번씩만
     * 2. 3*3 정사각형 안에도 1 ~ 0까지 숫자 한번씩
     */

    static BufferedReader br;
    static StringTokenizer st;
    static final int SIZE = 9;
    static final int EMPTY = 0;

    static int[][] map;

    public static void sudoku(int row, int col) {

        // 해당 행이 다 채워졌을 경우 다음 행부터 시작
        if (col == SIZE) {
            sudoku(row + 1, 0);
            return;
        }

        // 종료 조건
        if (row == SIZE) {
            // 행과 열 모두 채워졌을 경우
            // 종료
            // 결과
            for (int idx =0 ; idx < SIZE; idx ++) {
                for (int jdx = 0; jdx < SIZE; jdx++) {
                    System.out.print(map[idx][jdx] + " ");
                }
                System.out.println();
            }

            System.exit(0);
        }

        // 해당 위치의 값이 0이라면 1 ~ 9까지 중 가능한 수 탐색
        if (map[row][col] == EMPTY) {
            for (int number = 1; number <= SIZE; number ++) {
                // number의 중복 검사
                if (possibility(row, col, number)) {
                    map[row][col] = number; // 스도쿠가 가능하다면 number 넣기
                    sudoku(row, col + 1); // 다음 col로 넘기기
                }
            }

            // 불가능한 경우
            map[row][col] = EMPTY;
            return;
        }

        sudoku(row, col + 1);

    }

    public static boolean possibility(int row, int col, int number) {

        // 같은 행 중에서 겹치는 열이 있는 지 검사
        for (int idx = 0; idx < SIZE; idx ++) {
            if (map[row][idx] == number) {
                return false;
            }
        }

        // 같은 열 중에서 겹치는 행 원소가 있는 지 검사
        for (int idx = 0; idx < SIZE; idx++) {
            if (map[idx][col] == number) {
                return false;
            }
        }

        // 3 * 3 내에 중복되는 원소 있는 지 검사
        int minimapRow = (row / 3) * 3;
        int minimapCol = (col / 3) * 3;

        for (int idx = minimapRow; idx < minimapRow + 3; idx ++) {
            for (int jdx = minimapCol; jdx < minimapCol + 3; jdx++) {
                if (map[idx][jdx] == number) {
                    return false;
                }
            }
        }

        return true; // 중복되는 것이 없는 경우
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[SIZE][SIZE];

        // map에 Token 입력
        for (int idx = 0; idx < SIZE; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int jdx = 0; jdx < SIZE; jdx++) {
                map[idx][jdx] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0,0); // (0,0)

        // 결과
        for (int idx =0 ; idx < SIZE; idx ++) {
            for (int jdx = 0; jdx < SIZE; jdx++) {
                System.out.print(map[idx][jdx] + " ");
            }
            System.out.println();
        }

    }

}
