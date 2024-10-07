import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ_녹색 옷 입은 애가 젤다지?
 *
 * 젤다 화폐 -> 루피
 * 도둑루피 : 검정색 루피 -> 이거 갖게되면 오히려 소지한 루피가 감소
 * 링크는 주인공(0,0에 있음)
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int mapSize;
    static int [][] map;
    static int [][] countMap;

    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, -1, 0, 1};

    static class Rupee implements Comparable<Rupee>{
        int row;
        int col;
        int count;
        public Rupee(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        @Override
        public int compareTo(Rupee o) { // 오름차순 정렬
            return count - o.count;
        }

    }

    private static void init(int N) throws IOException {
        mapSize = N;
        map = new int[mapSize][mapSize];
        countMap = new int[mapSize][mapSize];

        // 입력 받기
        for (int row = 0; row < mapSize; row ++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col ++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // count 초기화하기
        for (int row = 0; row < mapSize; row ++) {
            Arrays.fill(countMap[row], Integer.MAX_VALUE);
        }
    }

    private static boolean inMap(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static int bfs() {
        PriorityQueue<Rupee> queue = new PriorityQueue<>();
        queue.add(new Rupee(0, 0, map[0][0])); // 처음 자기
        countMap[0][0] = map[0][0]; // 초기화

        while (!queue.isEmpty()) {
            Rupee now = queue.poll();

            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = now.row + deltaRow[direction];
                int nextCol = now.col + deltaCol[direction];

                // 맵 밖 여부
                if (!inMap(nextRow, nextCol)) continue;

                if (now.count + map[nextRow][nextCol] < countMap[nextRow][nextCol]) {
                    countMap[nextRow][nextCol] = now.count + map[nextRow][nextCol];
                    queue.add(new Rupee(nextRow, nextCol, now.count + map[nextRow][nextCol]));
                }
            }
        }

        // 결론
        return countMap[mapSize-1][mapSize-1];
    }



    public static void simulation(int time, int N) throws IOException {
        init(N); // 초기화
        int result = bfs(); // bfs
        sb.append("Problem " + time).append(": ").append(result).append("\n");
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int time = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine().trim());
            // 종료 조건
            if (N == 0) {
                break;
            }
            simulation(time, N);
            time ++;
        }
        System.out.println(sb);
    }
}
