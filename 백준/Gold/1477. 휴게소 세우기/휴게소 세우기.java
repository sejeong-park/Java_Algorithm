

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * 휴게소 세우기
     *
     * 1. 최대 거리 차가 최소가 되도록 휴게소 m 개를 설치해야 한다.
     * 2. 이분 탐색을 휴게소의 "거리차"를 기준으로 둔다.
     * sum = (array[idx] - array[idx - 1] - 1) / mid
     * : 현재 휴게소 사이에 mid 기준으로 총 몇개의 휴게소를 설치할 수 있나?
     * 만약 sum > m : 너무 많이 설치 할 수 있음 -> mid의 크기를 키워야 한다.
     * else : 설치를 더 해야한다. mid의 크기를 줄이기
     * */

    static BufferedReader br;
    static StringTokenizer st;

    static int totalCnt; // 현재 휴게소의 개수
    static int moreCnt; // 더 지으려고 하는 휴개소 개수
    static int roadLen; // 고속도로의 길이

    static int[] stopPoint; // 휴게소 위치

    public static int BSearch() {

        int start = 1;
        int end = roadLen - 1;
        int mid;

        while(start <= end) {

            // 조건
            mid = (start + end) / 2; // 중간 길이
            int sum = 0; // 총길이
            for (int idx = 1; idx < stopPoint.length; idx ++){
                // 휴게소 사이의 거리차를 mid 값으로 나누었을 때
                sum += (stopPoint[idx] - stopPoint[idx - 1] - 1) / mid;
            }

            // 지어야 하는 수량보다 더 크다면
            if (sum > moreCnt) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }

        }
        return start;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        totalCnt = Integer.parseInt(st.nextToken());
        moreCnt = Integer.parseInt(st.nextToken());
        roadLen = Integer.parseInt(st.nextToken());

        // 휴게소 위치 찾기
        stopPoint = new int[totalCnt + 2];
        st = new StringTokenizer(br.readLine().trim());
        stopPoint[0] = 0; // 도로의 시작 값 넣기
        for (int idx = 1; idx <= totalCnt; idx++) {
            stopPoint[idx] = Integer.parseInt(st.nextToken());
        }
        stopPoint[totalCnt + 1] = roadLen;
        // 정렬하기
        Arrays.sort(stopPoint);

        // 이분 탐색 결과
        System.out.println(BSearch());

    }

}
