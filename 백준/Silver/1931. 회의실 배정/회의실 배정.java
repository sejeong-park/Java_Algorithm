import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine().trim());
        int [][] meetingRoom = new int[cnt][2];
        for (int idx = 0; idx < cnt; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetingRoom[idx][0] = start;
            meetingRoom[idx][1] = end;
        }
        Arrays.sort(meetingRoom, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        int count = 0;
        int endTime = 0;
        for (int idx = 0; idx < cnt; idx ++) {
            if (endTime <= meetingRoom[idx][0]) {
                count += 1;
                endTime = meetingRoom[idx][1];
            }
        }
        System.out.println(count);
    }
}
