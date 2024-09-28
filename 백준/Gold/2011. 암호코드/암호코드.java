import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MOD = 1000000;
    static BufferedReader br;

    public static void main(String[] args) throws IOException{

        br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine(); // 입력 코드

        int[] dp = new int[code.length() + 1];
        dp[0] = 1;

        for (int idx = 1; idx <= code.length(); idx ++){
            int now = code.charAt(idx - 1) - '0'; // 글자
            if (now >= 1 && now <= 9) {
                dp[idx] += dp[idx - 1];
                dp[idx] %= MOD;
            }


            // 첫번째 글자인 경우
            if (idx == 1) continue;

            int prev = code.charAt(idx - 2) - '0';
            if (prev == 0) continue;

            int value = prev * 10 + now;

            if (value >= 10 && value <= 26) {
                dp[idx] += dp[idx - 2];
                dp[idx] %= MOD;
            }
        }
        System.out.println(dp[code.length()]);
    }
}
