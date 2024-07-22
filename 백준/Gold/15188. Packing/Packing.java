
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, W1, W2;
    static int[] weights;
    static int[] values;

    public static void init() throws IOException{

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        W1 = Integer.parseInt(st.nextToken());
        W2 = Integer.parseInt(st.nextToken());

        weights = new int[N];
        values = new int[N];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            weights[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            values[idx] = Integer.parseInt(st.nextToken());
        }
    }

    public static int knapsack() {
        int W = W1 + W2;
        int[][] dp = new int[N + 1][W + 1]; // 무게 확인

        for (int idx = 1; idx <= N; idx ++) {
            for (int w = 0; w <= W; w ++) {
                if (weights[idx - 1] <= w) {
                    dp[idx][w] = Math.max(dp[idx - 1][w], dp[idx - 1][w - weights[idx - 1]] + values[idx - 1]);
                } else {
                    dp[idx][w] = dp[idx - 1][w];
                }
            }
        }
        // System.out.println(dp[N][W]);
        return dp[N][W];
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int packingCount = Integer.parseInt(br.readLine().trim());

        for (int pIdx = 1; pIdx <= packingCount; pIdx ++) {
            init();
            int maxValue = knapsack();
            sb.append("Problem ").append(pIdx).append(": ").append(maxValue).append("\n");
        }
        System.out.println(sb);

    }

}
