import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1011_FlyMeToTheAlphaCentauri
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    private static void simulation(int x, int y) {

        int distance = y - x;
        int max = (int) Math.sqrt(distance);

        if (max == Math.sqrt(distance)) {
            sb.append(max * 2 - 1).append("\n");
        } else if (distance <= max * max + max) {
            sb.append(max * 2).append("\n");
        } else {
            sb.append(max * 2 + 1).append("\n");
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < N; testCase ++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            simulation(x, y);
        }

        System.out.println(sb.toString());
    }
}
