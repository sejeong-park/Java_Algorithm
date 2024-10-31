
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        char [] array1 = br.readLine().trim().toCharArray();
        int size1 = array1.length;
        char [] array2 = br.readLine().trim().toCharArray();
        int size2 = array2.length;

        int [][] dp = new int[size1 + 1][size2 + 1];

        for (int idx = 1; idx < size1 + 1; idx ++) {
            for (int jdx = 1; jdx < size2 + 1; jdx ++) {
                // 서로 같다면
                if (array1[idx - 1] == array2[jdx - 1]) {
                    dp[idx][jdx] = dp[idx - 1][jdx - 1] + 1;
                }
                else {
                    // 같지 않다면 자신의 양 옆에 있는 걸루
                    dp[idx][jdx] = Math.max(dp[idx - 1][jdx], dp[idx][jdx - 1]);
                }
            }
        }
        System.out.println(dp[size1][size2]);

    }

}
