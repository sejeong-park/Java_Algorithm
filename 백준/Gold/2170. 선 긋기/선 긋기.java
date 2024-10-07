

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static class Line implements Comparable<Line>{
        int start;
        int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // o1. o2 -> 오름차순
        @Override
        public int compareTo(Line o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        PriorityQueue<Line> queue = new PriorityQueue<>();

        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            queue.add(new Line(start, end));
        }

        // 작은거 찾기
        Line prev = queue.poll(); // 첫번째
        int total = prev.end - prev.start;
        while(!queue.isEmpty()) {
            Line now = queue.poll(); // 현재
            // 빠지는 것 조건
            if (prev.start <= now.start && now.end <= prev.end) {
                continue;
            }
            else if (now.start < prev.end) {
                total += now.end - prev.end;
            }
            else {
                total += now.end - now.start;
            }
            prev = now;
        }

        System.out.println(total);
    }
}
