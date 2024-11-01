import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static BufferedReader br;

    static int N;
    static int [] array;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        array = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            array[idx] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println(1);
            return;
        }
        int [] dp = new int[N];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        for (int idx = 1; idx < N; idx ++) {

            for (int jdx = 0; jdx < idx; jdx ++) {
                if (array[idx] > array[jdx]) {
                    dp[idx] = Math.max(dp[idx], dp[jdx] + 1);
                }
            }
            max = Math.max(max, dp[idx]);
        }
        System.out.println(max);
    }
}
