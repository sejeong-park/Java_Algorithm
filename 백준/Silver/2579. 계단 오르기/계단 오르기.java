
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* BOJ_2579_계단오르기
* 1. 계단은 한번에 한계단 & 두계단씩 오를 수 있다.
* 2. 연속된 세개의 계단을 모두 밟아서 안된다.
* 3. 마지막 도착 계단은 반드시 밟아야한다.
* */
public class Main {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int [] array = new int[N + 2];

        for (int idx = 1; idx <= N; idx++){
            array[idx] = Integer.parseInt(br.readLine().trim());
        }
        int [] dp = new int[N+2];
        dp[1] = array[1];
        dp[2] = array[1] + array[2];
        for (int idx =  3; idx <= N; idx++){
            dp[idx] = Math.max(dp[idx-3] + array[idx-1], dp[idx-2]) + array[idx];
        }
        System.out.println(dp[N]);
    }
}
