import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_7562_나이트의 이동
 *
 * 나이트 몇 번 움직이면 이동 가능?
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[] deltaRow = {-2, -1, -2, -1, 2, 1, 2, 1};
    static int[] deltaCol = {-1, -2, 1, 2, -1, -2, 1, 2};

    static int [][] map;
    static boolean [][] visited;
    static int mapSize;

    static class kNight implements Comparable<kNight>{
        int row;
        int col;
        int move;

        public kNight(int row, int col, int move) {
            this.row = row;
            this.col = col;
            this.move = move;
        }

        @Override
        public int compareTo(kNight o) {
            return this.move - o.move;
        }
    }

    private static boolean inMap(int row, int col) {
        return row >= 0 && row < mapSize && col >= 0 && col < mapSize;
    }

    private static void bfs(Point start, Point target) {
        PriorityQueue<kNight> queue = new PriorityQueue<>();
        queue.add(new kNight(start.x, start.y, 0));
        visited[start.x][start.y] = true;

        while(!queue.isEmpty()) {
            kNight now = queue.poll();

            // 확인
            if (now.row == target.x && now.col == target.y) {
                // 일치 여부 확인
                sb.append(now.move).append("\n");
                return;
            }

            for (int direction = 0; direction < 8; direction ++) {
                int nextRow = now.row + deltaRow[direction];
                int nextCol = now.col + deltaCol[direction];

                if (!inMap(nextRow, nextCol) || visited[nextRow][nextCol]) continue;
                queue.add(new kNight(nextRow, nextCol, now.move + 1));
                visited[nextRow][nextCol] = true;
            }
        }
    }


    private static void simulation(int size, Point now, Point target) {
        // 초기화
        mapSize = size;
        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize];

        bfs(now, target);
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int maxTestCase = Integer.parseInt(br.readLine().trim());

        // testCase 실행
        for (int testCase = 0; testCase < maxTestCase; testCase ++) {
            int mapSize = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine());
            Point now = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine().trim());
            Point target = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            simulation(mapSize, now, target);
        }
        System.out.println(sb.toString());
    }
}
