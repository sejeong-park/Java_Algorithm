import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br;
    static int [] alpha = new int[26];
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        alpha = new int [26];

        for (int idx = 0; idx < N; idx ++) {
            String str = br.readLine().trim();
            int size = str.length();
            int base = (int) Math.pow(10, size - 1); // 가중치
            // ABCD = A * 1000 + B * 100 + C * 10 + D
            for (int jdx = 0; jdx < size; jdx ++) {
                alpha[str.charAt(jdx) - 'A'] += base; // str의 가중치
                base /= 10; // 가중치 나누기
            }
        }
        Arrays.sort(alpha);

        int answer = 0;
        int num = 9;
        for (int idx = 25; idx >= 0; idx --) {
            if (alpha[idx] == 0) continue;
            answer += (alpha[idx] * num);
            num --;
        }
        System.out.println(answer);
    }
}
