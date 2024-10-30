import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static class Meeting implements Comparable<Meeting> {

        int start;
        int end;
        int count;

        public Meeting(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        @Override
        public int compareTo(Meeting o) {
            return this.end - o.end;
        }
    }

    // 현재 회의와 겹치지 않는 가장 가까운 이전 회의를 찾는다.
    public static int binarySearch(Meeting [] meeting, int index) {
        int start = 0;
        int end = index - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            // 조건
            if (meeting[mid].end <= meeting[index].start) {
                if (meeting[mid + 1].end <= meeting[index].start) {
                    start = mid + 1;
                } else {
                    return mid;
                }
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        Meeting[] table = new Meeting[N];

        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            table[idx] = new Meeting(start, end, count);
        }

        // 배열 정렬
        Arrays.sort(table);
        int [] dp = new int [N + 1];
        dp[0] = table[0].count; // 처음 초기화

        for (int idx = 1; idx < N; idx ++) {
            int now = table[idx].count;
            int last = binarySearch(table, idx);

            // -1이 아니라면
            if (last != -1) {
                now += dp[last]; // 마지막까지
            }
            dp[idx] = Math.max(dp[idx - 1], now);
        }

        System.out.println(dp[N - 1]);

//        for (int idx = 0; idx < N; idx ++) {
//            System.out.println(table[idx].start + " " + table[idx].end + " " + table[idx].count);
//        }

    }

}
