
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 *
 * 1. 폭탄의 폭발 -> 상하좌우 인접한 네칸 폭발
 * 2. 폭탄이 폭발할 때, 인접한 칸에 폭탄이 있는 경우, 인접한 폭탄은 그냥 파괴 (연쇄 반응 없다.)
 * 3. 봄버맨은 자유로운 이동 가능
 * ---
 * 1초 동안 봄버맨 모든 칸에 폭탄 설치
 * 1초 지나면 터짐
 * 두 행동을 반복
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize, colSize, endTime;
    static int t; // time

    static char [][] map;
    static int [][] time;

    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, -1, 0, 1};

    private static void setBomb(){
        for (int row = 0; row < rowSize; row ++) {
            for (int col = 0; col < colSize; col ++) {
                if (map[row][col] == '.') {
                    map[row][col] = 'O';
                    time[row][col] = t + 3;
                }
            }
        }
    }

    private static boolean inMap(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }

    private static void doBomb() {
        for (int row = 0; row < rowSize; row ++) {
            for (int col = 0; col < colSize; col ++) {
                if (time[row][col] == t) { // 3초 전에 놓아 현재 시간에 터져야하는 폭탄

                    // Bomb
                    map[row][col] = '.'; // 빈칸 변경
                    for (int direction = 0; direction < 4; direction ++) {
                        int nextRow = row + deltaRow[direction];
                        int nextCol = col + deltaCol[direction];

                        if (!inMap(nextRow, nextCol)) continue;
                        if (time[nextRow][nextCol] == t) continue; // 같은 시간에 놓은 폭탄들은 터뜨리지 않는다.
                        if (map[nextRow][nextCol] == '.') continue;

                        time[nextRow][nextCol] = 0; // 폭탄
                        map[nextRow][nextCol] = '.';

                    }

                }
            }
        }
    }

    private static void print() {
        for (int row = 0; row < rowSize; row ++) {
            String line = "";
            for (int col = 0; col < colSize; col ++) {
                line += map[row][col];
            }
            System.out.println(line);
        }
    }

    private static void simulation() {

        t = 1;
        while(t++ < endTime) {
            if (t % 2 == 0) {
                setBomb();
            } else {
                doBomb();
            }
        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        endTime = Integer.parseInt(st.nextToken());

        map = new char[rowSize][colSize];
        time = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row ++) {
            String line = br.readLine();
            for (int col = 0; col  <colSize; col ++) {
                map[row][col] = line.charAt(col);
                if (map[row][col] == 'O') {
                    time[row][col] = 3;
                }
            }
        }

        simulation();
        print();
    }


}
