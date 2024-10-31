
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize, colSize;
    static int [][] map;
    static int [][] dp;

    static int max;

    // 최대 배열 크기를 구하는 것
    public static void findMaxSum() {
        max = Integer.MIN_VALUE;

        for (int row1 = 1; row1 < rowSize + 1; row1 ++) {
            for (int col1 = 1; col1 < colSize + 1; col1 ++) {

                // 밖에 있는 수열
                for (int row2 = row1; row2 < rowSize + 1; row2 ++) {
                    for (int col2 = col1; col2 < colSize + 1; col2++) {
                        int tmp = dp[row2][col2] - dp[row1 - 1][col2] - dp[row2][col1 - 1] + dp[row1 - 1][col1 - 1];
                        max = Math.max(max, tmp);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col ++){
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[rowSize + 1][colSize + 1];
        for (int row = 1; row < rowSize + 1; row ++) {
            for (int col = 1; col < colSize + 1; col ++ ){
                dp[row][col] = map[row - 1][col - 1] + dp[row - 1][col] + dp[row][col - 1] - dp[row-1][col-1];
            }
        }

        findMaxSum();

        System.out.println(max);

//        for (int row = 0; row < rowSize + 1; row++) {
//            System.out.println(Arrays.toString(dp[row]));
//        }

    }

}
