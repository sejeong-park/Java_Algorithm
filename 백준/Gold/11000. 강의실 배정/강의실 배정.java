
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int [][] lecture = new int[N][2];

        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lecture[idx] = new int[]{start, end};
        }

          // 정렬
        Arrays.sort(lecture, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lecture[0][1]);

        // 찾기
        for (int idx = 1; idx < N; idx ++) {
            // pq 제일 위에 있는거보다 시작시간이 작을때
            if (pq.peek() <= lecture[idx][0]) {
                pq.poll();
            }
            pq.offer(lecture[idx][1]);
        }

        System.out.println(pq.size());
    }

}
