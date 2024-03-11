
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * 15486 퇴사 2
     *
     * 오늘부터 N + 1 일째 되는 날 퇴사 -> 남은 N일동안 최대한 많은 상담
     *
     *
     *
     * */

    static BufferedReader br;
    static StringTokenizer st;

    static int totalDay;
    static int[] periodList;
    static int[] priceList;
    static int[] dp;

    public static void main(String [] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        totalDay = Integer.parseInt(br.readLine().trim());

        periodList = new int[totalDay + 2];
        priceList = new int[totalDay + 2];
        // 날짜에 해당하는 값 넣기
        for (int day = 1; day <= totalDay; day++) {
            st = new StringTokenizer(br.readLine().trim());
            periodList[day] = Integer.parseInt(st.nextToken());
            priceList[day] = Integer.parseInt(st.nextToken());
        }

        dp = new int[totalDay + 2];
        for (int day = 1; day <= totalDay; day ++) {
            // 종료일
            int endDate = day + periodList[day];
            // 종료일이 전체 날짜를 지날 경우
            if (endDate <= totalDay + 1) {
                dp[endDate] = Math.max(dp[endDate], dp[day] + priceList[day]);
            }
            dp[day + 1] = Math.max(dp[day + 1], dp[day]);
        }

        Arrays.sort(dp);
        System.out.println(dp[dp.length - 1]);

   }


}
