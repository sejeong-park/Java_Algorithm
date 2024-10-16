import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_17396_백도어
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N, M;
    static boolean[] sight;
    static long [] distance;
    static boolean [] visited;

    static ArrayList<ArrayList<Node>> graph;

    static class Node implements Comparable<Node>{
        int index;
        long cost;

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    private static void Dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        distance[0] = 0;

        while (!pq.isEmpty()) {

            Node now = pq.poll();

            // 방문 했다면 pass
            if (visited[now.index]) continue;
            visited[now.index] = true;

            for (Node next : graph.get(now.index)) {
                // 상대편의 점의 시야에 걸리는 경우 & 상대편 N-1번째에 분기점에 걸리는 경우
                if (next.index != N-1 && !sight[next.index]) {
                    continue;
                }

                // 더 작을 때 현재 갱신
                if (distance[next.index] > distance[now.index] + next.cost) {
                    distance[next.index] = distance[now.index] + next.cost;
                    pq.add(new Node(next.index, distance[next.index]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 시야에 걸리는 여부
        sight = new boolean[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            int tmp = Integer.parseInt(st.nextToken());
            sight[idx] = (tmp == 1) ? false : true;
        }

        graph = new ArrayList<>();
        for (int idx = 0; idx < N; idx ++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 입력
        visited = new boolean[N];
        for (int idx = 0; idx < M; idx ++) {
            st = new StringTokenizer(br.readLine().trim());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        distance = new long[N];
        Arrays.fill(distance, Long.MAX_VALUE);
        
        Dijkstra();

        // 상대 넥서스 까지 못가면 -1
        System.out.println(distance[N-1] != Long.MAX_VALUE ? distance[N-1] : -1);
    }
}
