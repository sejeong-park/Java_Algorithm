import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 회의실 배정 3
 * K번째 회의는 K-1, k+1번째 회의랑만 시간이 겹친다.
 * k번째 회의를 택하면 k-1째 회의를 택할 수 없다.
 * 1. K-2번째 회의를 택하고, k번째 회의를 택하는 경우
 * 2. k-1번째 회의를 택하고, k번째 회의를 택하지 않는 경우
 * 위 두 케이스 중 더 좋은 경우를 갱신해나가기
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int [][] timeTables = new int[N][3];
        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            timeTables[idx][0] = start;
            timeTables[idx][1] = end;
            timeTables[idx][2] = count;
        }

        int [] dp = new int[N + 1];
        dp[0] = timeTables[0][2];
        if (N == 1) {
            System.out.println(dp[0]);
            return;
        }
        dp[1] = Math.max(dp[0], timeTables[1][2]);
        for (int idx = 2; idx < N; idx ++) {
            // 내가 들어가면 -2 /
            dp[idx] = Math.max(dp[idx - 2] + timeTables[idx][2], dp[idx - 1]);
        }

        System.out.println(dp[N-1]);
    }
}
