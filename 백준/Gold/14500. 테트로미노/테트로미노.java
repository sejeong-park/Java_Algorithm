import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정사각형 서로 겹치면 안돼
 * 도형 모두 연결되어야해
 * 정사각형 변끼리 연결
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize, colSize;
    static int [][] map;
    static boolean [][] visited;
    static int max;

    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    static boolean inMap(int row, int col) {
        return row >= 0 && row < rowSize && col >= 0 && col < colSize;
    }

    static void dfs(int row, int col, int sum, int count) {
        // 테크노미노 수의 합
        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int direction = 0; direction < 4; direction ++) {
            int nextRow = row + deltaRow[direction];
            int nextCol = col + deltaCol[direction];

            // 업무 벗어나면
            if (!inMap(nextRow, nextCol) || visited[nextRow][nextCol]) continue;

           // depth 2에서 ㅗ 만들어준다
            if (count == 2) {
                visited[nextRow][nextCol] = true;
                dfs(row, col, sum + map[nextRow][nextCol], count + 1);
                visited[nextRow][nextCol] = false;
            }

            // 일반 방문
            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol, sum + map[nextRow][nextCol], count + 1);
            visited[nextRow][nextCol] = false;
        }

    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;

        map = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col ++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 전체 탐색 -> backtracking
        for (int row = 0; row < rowSize; row ++) {
            for (int col = 0; col < colSize; col ++) {
                visited[row][col] = true;
                dfs(row, col, map[row][col], 1); // dfs
                visited[row][col] = false;
            }
        }

        System.out.println(max);
    }
}
