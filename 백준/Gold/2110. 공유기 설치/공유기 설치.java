import java.io.*;
import java.util.*;

public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        // n: 집 개수 / c: 공유기 개수
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 집 좌표 배열
        int[] house = new int[n];

        // 입력값 읽어오기
        for (int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        // 정렬 (Python의 sort()에 해당)
        Arrays.sort(house);

        int start = 1; // 최소 거리
        int end = house[n - 1] - house[0]; // 최대 거리
        int result = 0; // 최적의 GAP 값 저장

        while (start <= end) {
            int mid = (start + end) / 2; // GAP 값 설정
            int prev = house[0]; // 첫 번째 집에 공유기 설치
            int count = 1; // 설치한 공유기 개수

            // 공유기 설치 개수 확인
            for (int i = 1; i < n; i++) {
                if (house[i] >= prev + mid) { // mid 거리 이상이면 설치 가능
                    count++;
                    prev = house[i]; // 이전 공유기 위치 갱신
                }
            }

            // 공유기 개수 비교
            if (count >= c) { // 더 많은 공유기 설치 가능 -> 간격 늘리기
                result = mid; // 가능한 최대 거리 기록
                start = mid + 1; // 더 넓은 간격 탐색
            } else { // 공유기 부족 -> 간격 줄이기
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}
