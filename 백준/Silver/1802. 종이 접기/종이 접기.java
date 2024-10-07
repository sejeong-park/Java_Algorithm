import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1802_종이접기
 **/
public class Main {

    static BufferedReader br;
    static StringBuilder sb;

    private static boolean foldable(String input) {

        if (input.length() > 1) {
            int half = input.length() / 2;

            //왼쪽 종이와 오른쪽 종이가 가능한지 확인
            if (!foldable(input.substring(0, half))) return false;
            if (!foldable(input.substring(half+1))) return false;

            // 접현던 작은 부분들이 가능한 경우라면 현재 페이지에서 서로 반대되는 지 확인
            for (int idx = 1; idx < half + 1; idx ++) {
                // 반대여부 확인
                if (input.charAt(half - idx) == input.charAt(half + idx)) {
                    return false;
                }
            }
        }
        // 반으로 접은 경우 1페이지인 경우 확인하지 않는다.
        return true;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < testCase; idx ++) {
            String input = br.readLine().trim();
            if (foldable(input)) {
                sb.append("YES").append("\n");
            }
            else {
                sb.append("NO").append("\n");
            }
        }

        // 결과 출력
        System.out.println(sb.toString());

    }
}
