
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * BOJ_2170_선긋기
 *
 * 출력 : 그려진 선의 총 길이
 * 1. 줄이 완전히 겹칠 때 -> 이전 선에 포함될 때 (계산하지 않는다.)
 * 2. 줄의 선이 일부만 겹칠 때 -> 추가된 부분만 계산한다.
 * 3. 선이 겹치지 않을 때 -> 따로 계산한다.
 * 1. 이미 선이 겹칠 때 -> 새로 그려진 부분만 계산해야한다.
 * 2. 선이 겹치지 않을 때 -> 따로 계산
 * 1 -> 3 = 2
 * 2 -> 5 = 5 - 3 = 2
 * 3 -> 5 = 5 - 5 = 0
 * 6 -> 7 = 7 - 6 = 1
 * 2 + 2 + 1 = 5
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[][] lines = new int[N][2];
        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            lines[idx][0] = Integer.parseInt(st.nextToken());
            lines[idx][1] = Integer.parseInt(st.nextToken());
        }
        // 조건 x좌표가 오름차순 정렬 -> x가 같다면, y좌표 오름차순정렬
        Arrays.sort(lines, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        int prevStart = lines[0][0];
        int prevEnd = lines[0][1];
        int total = prevEnd - prevStart; // 전체 줄 긋기 수
        for (int idx = 1; idx < N; idx ++) {
            // 이미 포함된 줄이라면
            if (prevStart <= lines[idx][0] && lines[idx][1] <= prevEnd) {
                continue;
            }
            // 만약 줄이 살짝 걸쳐있다면
            else if (lines[idx][0] < prevEnd) {
                total += lines[idx][1] - prevEnd; // 추가된 부분
            }
            // 만약 줄이 끝난 이후일 때
            else {
                total += lines[idx][1] - lines[idx][0]; // 새로 그린 것 추가
            }
            prevStart = lines[idx][0];
            prevEnd = lines[idx][1];
        }

        System.out.println(total);
    }

}
