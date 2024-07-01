

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 미친 로봇
 *
 * 통제할 수 없는 로봇이 평면 위 -> N번 행동
 * 4개 방향 중 임의로 선택 -> 그 방향으로 한칸 이동
 * 로ㅗ봇이 같은 곳을 한번보다 많이 이동하지 않을 때,
 * 로봇의 이동 경로가 단순하다
 *
 * N은 14보다 작거나 같은 자연수
 * -> 
 * */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static double [] percent;

    // 동서 남북
    static int [] deltaRow = {0, 0, 1, -1};
    static int[] deltaCol = {1, -1, 0, 0};

    static boolean [][] visited;
    static double answer = 0;

    public static boolean inMap(int row, int col) {
        return row >= 0 && row < 30 && col >= 0 && col < 30;
    }

    public static void dfs(int row, int col, int cnt, double result) {
        // 로봇의 최대 이동 횟수 도달
        if (cnt == N) {
            answer += result;
            return;
        }

        for (int direction = 0; direction < 4; direction ++) {
            int nextRow = row + deltaRow[direction];
            int nextCol = col + deltaCol[direction];

            if (!inMap(nextRow, nextCol)) continue;

            // 특정 지점을 방문하지 않았을 경우
            if (!visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, cnt + 1, result * percent[direction]);
                visited[nextRow][nextCol] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        // 동서남북
        percent = new double[4];
        for (int idx = 0; idx < 4; idx ++) {
            percent[idx] = Double.parseDouble(st.nextToken()) * 0.01;
        }

        visited = new boolean[30][30];
        visited[15][15] = true;
        dfs(15, 15, 0, 1);

        System.out.println(answer);
    }
}
