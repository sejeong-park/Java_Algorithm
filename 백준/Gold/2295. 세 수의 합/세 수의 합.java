import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main {

    static BufferedReader br;
    static int N;
    static int [] numbers;
    static ArrayList<Integer> list; // 두 수 더하기

    // 존재하는지 확인한다.
    public static boolean search(int n) {
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (list.get(mid) < n) {
                start = mid + 1;
            }
            else if (list.get(mid) > n) {
                end = mid - 1;
            }
            else if (list.get(mid) == n) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());

        numbers = new int [N];
        for (int idx = 0; idx < N; idx ++) {
            numbers[idx] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(numbers); // 정렬
        int answer = 0;
        // x + y + z = k
        // x + y = k - z
        list = new ArrayList<>();
        for (int x = 0; x < N; x ++) {
            for (int y = 0; y < N; y ++) {
                list.add(numbers[x] + numbers[y]);
            }
        }
        Collections.sort(list);

        // 이분 탐색
        // 뒤에서부터 탐색
        for (int k = N - 1; k >= 0; k --) {
            for (int z = 0; z < k; z ++) {
                int diff = numbers[k] - numbers[z];

                // k - z가 list 내에 존재하는 지 찾기
                // k의 최대값 업데이트
                if (search(diff)){
                    answer = Math.max(answer, numbers[k]);
                }
            }
        }

        System.out.println(answer);

    }
}
