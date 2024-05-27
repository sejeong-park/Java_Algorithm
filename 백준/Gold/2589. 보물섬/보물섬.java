import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BOJ_2589_보물섬
 *
 * */
public class Main {

    static StringTokenizer st;
    static BufferedReader br;

    // 맵
    static int rowSize;
    static int colSize;
    static char[][] map;
    static boolean[][] visited;

    static int[] deltaRow = {0, 1, 0, -1};
    static int[] deltaCol = {-1, 0, 1, 0};

    // Point
    public static class Point {
        int row;
        int col;
        int cost;

        public Point(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }
    public static boolean isMap(int rowIdx, int colIdx) {
        return rowIdx >= 0 && rowIdx < rowSize && colIdx >= 0 && colIdx < colSize;
    }
    public static int bfs(int rowIdx, int colIdx) {
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(rowIdx, colIdx, 0));
        visited[rowIdx][colIdx] = true;

        int length = 0;
        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.row + deltaRow[direction];
                int nextCol = now.col + deltaCol[direction];

                // 제외할 것
                if(!isMap(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol] || map[nextRow][nextCol] == 'W') continue;

                // 추가할 것
                queue.add(new Point(nextRow, nextCol, now.cost + 1));
                length = Math.max(length, now.cost + 1);
                visited[nextRow][nextCol] = true;
            }
        }

        return length;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 개수 세기
        map = new char[rowSize][colSize];
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String line = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = line.charAt(colIdx);
            }
        }

        int max = 0;
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                if (map[rowIdx][colIdx] == 'L') {
                    visited = new boolean[rowSize][colSize];
                    int length = bfs(rowIdx, colIdx);
                    max = Math.max(max, length);
                }
            }
        }

        System.out.println(max);
    }

}
