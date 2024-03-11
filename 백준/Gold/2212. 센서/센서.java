
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * BOJ_2212_센서
     *
     * - N개의 센서 설치
     * - 수집한 자료들 모으고 분석할 몇 개의 집중국을 세운다.
     * - k개의 집중국을 세운다. -> 센서의 수신 가능 영역 조절 가능
     *
     * 센서 6개인데, 집중국 2개인 경우 -> [1,3] [6, 9] 경로에 1개 설치하면 2 + 3 = 5
     * 집중국은 각 센서마다 1개씩 담당하면 되므로 최소 길이 0개
     * 센서 N개 & 집중국이 N-1개처럼 개수가 차이날 경우 어떻게 해야하는가?
     *
     * 수신 가능 영역을 최소로 만들기 위해 -> 두 점 사이의 거리가 가장 먼 곳을 제외한다.
     * 집합을 만든다는 개념
     * */

    static BufferedReader br;
    static StringTokenizer st;

    static int sensorCnt;
    static int maxPoint;
    static int [] sensorList;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        sensorCnt = Integer.parseInt(br.readLine().trim());
        maxPoint = Integer.parseInt(br.readLine().trim());

        // 각 좌표 입력
        st = new StringTokenizer(br.readLine().trim());
        sensorList = new int[sensorCnt];
        for (int idx = 0; idx < sensorCnt; idx++) {
            sensorList[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensorList);

        // 서로 간 간격
        int [] diff = new int[sensorCnt - 1]; // 간격이므로 -1
        for (int idx = 0; idx < sensorCnt - 1; idx ++) {
            diff[idx] = sensorList[idx + 1] - sensorList[idx];
        }
        Arrays.sort(diff);

        int answer = 0;
        for (int idx = 0; idx < sensorCnt - maxPoint; idx++) {
            answer += diff[idx];
        }

        System.out.println(answer);

    }

}
