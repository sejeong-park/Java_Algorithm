import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_2230_수고르기
 *
 * N개의 정수로 이루어졌다.
 * 수열에서 두 수를 골랐을 때 그 차이가 M이상이면서 제일 작은 경우
 * {1, 2, 3, 4, 5}
 *
 * 1, 3, 5
 * 5 - 1 = 4
 * 3 - 1 = 2
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int M;
    static int [] list;
    static int min;

    private static void simulation() {
        int start = 0;
        int end = 0;

        min = Integer.MAX_VALUE; // 가장 작은 값

        while (start < N  && end < N) {
            int distance = list[end] - list[start];

            if (distance >= M) {
                min = Math.min(distance, min);
                start += 1;
            } else {
                end += 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new int [N];
        for (int idx = 0; idx < N; idx ++) {
            list[idx] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(list);
        simulation();
        System.out.println(min);
    }
}
