import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int [][] timeTable = new int[N][3];
        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            timeTable[idx][0] = start;
            timeTable[idx][1] = end;
            timeTable[idx][2] = count;
        }

        // end 타임 기준으로 정렬
        Arrays.sort(timeTable, (o1, o2) -> o1[1] - o2[1]);

        // 시작
        int [] dp = new int[N];
        dp[0] = timeTable[0][2]; // 시작
        if (N <= 1) {
            System.out.println(dp[0]);
            return;
        }
        
        dp[1] = Math.max(timeTable[0][2], timeTable[1][2]);
        for (int idx = 2; idx < N; idx ++) {
            dp[idx] = Math.max(dp[idx - 1], dp[idx - 2] + timeTable[idx][2]);
        }

        System.out.println(dp[N-1]); // 결과
    }
}
