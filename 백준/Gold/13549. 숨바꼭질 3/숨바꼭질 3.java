import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 수빈이는 현재 점 N / 동생은 점 K
 * 수빈이는 걷거나 순간이동 가능 -> 수빈이의 위치가 X일 때 걷는 다면, 1초 후에 X - 1 또는 X + 1로 걷는다.
 * 순간이동을 하는 경우에는 0초 후에 2 * X의 위치로 이동
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N, K;
    static final int MAX = 100000;
    static boolean [] visited;
    static int [] deltaRow = {-1 , 1};

    private static boolean checkRange(int point) {
        return 0 <= point && point <= MAX;
    }

    public static void bfs(int start, int target) {
        visited = new boolean[MAX + 1];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while(!queue.isEmpty()) {
            int [] now = queue.poll();
            int point = now[0];
            int time = now[1];

            // 만약 목표물을 찾으면?
            if (point == target) {
                System.out.println(time);
                return;
            }

            // 순간이동
            if (checkRange(point * 2) && !visited[point * 2]) {
                queue.add(new int [] {point * 2, time});
                visited[point * 2] = true;
            }
            // 일반 이동
            for (int idx = 0; idx < 2; idx ++) {
                int next = point + deltaRow[idx];
                if (checkRange(next) && !visited[next]) {
                    queue.add(new int[]{next, time + 1}); // 초 증가
                    visited[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K);

    }

}
