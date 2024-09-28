
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 포도주 시식
 * - 포도주 일렬로 놓여있음
 * - 포도주 잔 선택하면 그 잔에 있는 포도주 모두 마시고, 마신 후 원래 위치에  되돌려놓는다.
 * - 연속으로 놓여 있는 3잔을 모두 마실순 없음
 *
 * // 3개일 때 경우의 수가 3개
 * oox : dp[idx - 1]
 * oxo : dp[idx - 2] + array[idx]
 * xoo : dp[idx - 3] + array[idx - 1] + array[idx]
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        int N = Integer.parseInt(br.readLine());
        int [] array = new int [N + 1];
        for (int idx = 1; idx < N + 1; idx ++) {
            array[idx] = Integer.parseInt(br.readLine());
        }

        // dp 테이블 만들기
        int [] dp = new int[N + 1];
        dp[0] = 0;
        for (int idx = 1; idx < N + 1; idx ++) {
            // 한개일 때
            if (idx == 1) {
                dp[idx] = array[idx];
                continue;
            }
            // 두개일 때
            if (idx == 2) {
                // oox
                dp[idx] = array[idx] + array[idx - 1];
                continue;
            }

            dp[idx] = Math.max(dp[idx - 1], Math.max(dp[idx - 2] + array[idx], dp[idx - 3] + array[idx] + array[idx - 1]));
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]); // 최댓값으로만 !!
    }
}
