

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N, M;
    static int[] distance;
    static ArrayList<Node>[] list;
    static boolean[] visited;

    public static class Node implements Comparable<Node> {
        int distance;
        int cost;

        public Node(int distance, int cost) {
            this.distance = distance;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        distance[1] = 0; // 시작 지점은 1
        queue.offer(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (!visited[now.distance]) {
                visited[now.distance] = true;
            } else {
                continue;
            }

            for (int idx = 0; idx < list[now.distance].size(); idx++) {
                Node next = list[now.distance].get(idx);
                if (distance[next.distance] > distance[now.distance] + next.cost) {
                    distance[next.distance] = distance[now.distance] + next.cost;
                    queue.offer(new Node(next.distance, distance[next.distance]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        //
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 돌리기
        list = new ArrayList[N + 1];
        for (int idx = 1; idx < N+1; idx ++) {
            list[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < M; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, cost)); // 끝
            list[end].add(new Node(start, cost)); // 시작
        }

        visited = new boolean[N + 1];
        distance = new int[N + 1];
        Arrays.fill(distance, 50000001); // 50000*1000 + 1
        dijkstra();
        System.out.println(distance[N]); // 목적지는 N

    }

}
