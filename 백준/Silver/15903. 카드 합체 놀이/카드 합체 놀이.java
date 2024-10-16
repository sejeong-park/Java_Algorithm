import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_15903_카드합체놀이
 *
 * 아기 : 자연수 n장
 * x번 카드 y 번 카드 골라 두 더한값
 * x번 카드와 y번 카드에 모두 덮어씀
 * m번 하면 끝남
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> queue = new PriorityQueue();
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            queue.add(Long.parseLong(st.nextToken()));
        }

        for (int idx = 0; idx < M; idx ++) {
            long a = queue.poll();
            long b = queue.poll();
            long tmp = a + b;
            queue.add(tmp);
            queue.add(tmp);
        }

        long result = 0;
        while(!queue.isEmpty()) {
            result += queue.poll();
        }

        System.out.println(result);
    }
}
