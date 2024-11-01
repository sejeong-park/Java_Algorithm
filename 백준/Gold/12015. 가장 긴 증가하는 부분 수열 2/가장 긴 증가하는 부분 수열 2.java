import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int [] array;

    static ArrayList<Integer> list;

    public static int binarySearch(int target) {
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        array = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            array[idx] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();

        for (int idx = 0; idx < N; idx ++) {
            int target = binarySearch(array[idx]); // 탐색할 대상 찾기
            if (target < list.size()) {
                list.set(target, array[idx]);
            }else {
                list.add(array[idx]);
            }
        }

        System.out.println(list.size());
    }
}
