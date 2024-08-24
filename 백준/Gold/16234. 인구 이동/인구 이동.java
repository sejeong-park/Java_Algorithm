import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * N * N
 * 1. 인구 이동 ->
 * 2. 두 나라의 인구 차이가 L명 이상 R명 이하라면, 두 나라가 공유하는 국경선 하루동안 연다.
 * 3. 열려있어 인접한 칸 이용해 이동 -> 연합
 * 4. 연합의 인구수 / 연합을 이루고 있는 칸
 * 5. 연합 해체 후 모든 국경선 닫는다.
 * 6. 인구이동 몇일간 이루어지는가?
 * */
public class Main {

    static StringTokenizer st;
    static BufferedReader br;
    static int N, L, R;
    static int [][] map;

    // 상하 좌우
    static boolean [][] visited;
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};

    // check
    static boolean open;

    public static boolean inMap(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }

    public static int countPeople(ArrayList<Point> list) {
        int sum = 0;
        for (Point now : list) {
            sum += map[now.x][now.y];
        }
        return sum / list.size();
    }

    public static int bfs(int row, int col) {
        // 연합 리스트
        ArrayList<Point> unionList = new ArrayList<>();
        unionList.add(new Point(row, col));

        // bfs
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(row, col));
        visited[row][col] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.x + deltaRow[direction];
                int nextCol = now.y + deltaCol[direction];

                // 조건 찾기
                if (!inMap(nextRow, nextCol) || visited[nextRow][nextCol]) continue;
                int diff = Math.abs(map[now.x][now.y] - map[nextRow][nextCol]);
                // 국경이 열려 인구 이동을 한다면
                if (L <= diff && diff <= R) {
                    queue.add(new Point(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                    unionList.add(new Point(nextRow, nextCol));
                }
            }
        }

        // 연합 넣기
        int peopleCnt = countPeople(unionList);
        for (Point point : unionList) {
            map[point.x][point.y] = peopleCnt;
        }

        // 그룹별 수 리턴
        return unionList.size();
    }


    public static void move() {

        visited = new boolean[N][N];
        int count = 0; // 독립적인 연합이 생기는 수를 카운트

        for (int row = 0; row < N; row ++) {
            for (int col = 0; col < N; col ++) {
                if (!visited[row][col]) {
                    int unionSize = bfs(row, col);
                    if (unionSize == 1) {
                        count++;
                    }
                }
            }
        }

        // 만약 count = N * N
        if (count == N * N) {
            open = false;
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // N, L, R
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[N][N];
        for (int row = 0; row < N; row ++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col ++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 국경 열리는 기간
        open = true;
        int day = 0; // 몇일간?
        while(true) {
            move();
            if (!open) break;
            day ++;
        }

        System.out.println(day);
    }
}
