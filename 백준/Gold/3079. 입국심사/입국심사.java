
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * BOJ_3079_입국 심사
 *
 * - K번 심사대에 앉아있는 심사관이 한명을 심사하는데, 드는 시간 T
 * - 처음 모든 심사대 비어있고, 심사를 할 준비 끝
 * - 한 심사대에서는 한번에 한 사람만 심사 가능
 * - 가장 앞에 서있는 사람 -> 비어있는 심사대 심사
 * - 더 빠른 심사대의 심사가 끝나면 거기로 가도 됨
 * - 어떻게 심사를 받으면 모든 사람이 심사를 받는데 걸리는 시간이 최소가 될까?
 *
 * [생각]
 * 1. 정렬 -> 어차피 빠른 초들을 여러번 들어갈 수록 이득
 * 2. 최대 인원을 넘어야 하는데, pointer를 기준으로 뒤에서 왔다갔다 해야한다. (mid가 포인터)
 * -> 더 적은 초로 M을 넘어야해.
 * -> M을 end가 큰데서 넘겼다면, 더 짧은 초에서 M을 넘길 수 있는 지 검사 (end가 포인터보다 작아져야 함)
 * -> start는 M을 넘기지 못할 때 큰쪽으로 mid값을 찾아준다.
 *
 * 10억은 int 초과
 * -> T가 10의 9승 * M이 10의 9승인데, 두 값을 곱한게 최대니까 Long 타입 지정
 * 이 코드에서 m도 int형이고 min도 int형이므로,
 * min * m은 int로 계산됩니다.
 * 이 즉시 오버플로가 발생하게 되고,
 * 이미 오버플로가 발생한 값을 long인 변수에 넣는 것은 이미 늦은 뒤입니다.
 * 한 번 오버플로된 값은 복구가 되지 않습니다.
 *
 * -> people도 overflow 여지 있다.
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int M;
    static int [] timeTable;
    static long max = 0; // 받은 검사 시간 * 인원수
    static long min = Long.MAX_VALUE;

    public static void minAccess() {
        long start = 0;
        long end =  max * M; // 최대 오래 걸리는 초

        // start end 전까지
        while(start <= end) {
            long mid = (start + end) / 2; // 이분 탐색 -> mid는 초

            // 시간 계산 -> 최소값부터 카운티
            long totalCnt = 0; // 전체 심사대에서 검사할 수 있는 사람의 수
            for (int time = 0; time < timeTable.length; time ++) {
                // 낱개의 심사대에서 mid 기준으로 받을 수 있는 사람의 수
                long eachCnt = mid / timeTable[time];
                if (totalCnt >= M) break; // 종료할 수 있으면 루프 종료
                totalCnt += eachCnt;
            }
            // 전체 합이 M보다 클때
            if (totalCnt >= M) {
                end = mid - 1; // 더 작은 범위 찾기
                min = Math.min(min, mid); // 최소 시간
            } else {
                start = mid + 1;
            }
        }


    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 심사를 하는데 걸리는 시간
        timeTable = new int[N];
        for(int idx = 0; idx < N; idx++) {
            timeTable[idx] = Integer.parseInt(br.readLine().trim());
            max = Math.max(max, timeTable[idx]);
        }
        // 정렬
        Arrays.sort(timeTable);
        minAccess();
        System.out.println(min);
    }
}
