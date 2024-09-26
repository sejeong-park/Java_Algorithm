import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int[][] timeTable = new int[N][2];
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            timeTable[idx][0] = Integer.parseInt(st.nextToken());
            timeTable[idx][1] = Integer.parseInt(st.nextToken());
        }

        // 만약 시작 시간이 동일하면 종료값으로 비교
        Arrays.sort(timeTable, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

        // 종료 시간을 넣는다.
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(timeTable[0][1]); // 첫번째 종료 시간
        for (int idx = 1; idx < N; idx++) {
            // 만약 앞에 거보다 시작 시간 작거나 같을경우 -> 여러개 생김
            if (queue.peek() <= timeTable[idx][0]) {
                queue.poll(); //
            }
            queue.add(timeTable[idx][1]);
        }
        System.out.println(queue.size());
    }

}
