
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* SWEA_1976 시각 덧셈
* 시 분으로 이루어진 시각을 2개 입력 받아, 더한 값을 시 분으로 출력하는 프로그램으로 작성
*  -> 시각 12시간제 | 시가 가질 수 있는 값은 1시부터 12시
* */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // 전체적인 결과
    static int totalHour;
    static int totalMinuate;

    // Minuate & Hour
    public static void totalTime(int aHour, int aMinuate, int bHour, int bMinuate){
        // 전체 결과 초기화
        totalHour = 0;
        totalMinuate = 0;

        // Minuate 입력
        totalMinuate = (aMinuate + bMinuate) % 60;
        // Hour 넣기
        totalHour += (aMinuate + bMinuate) / 60; // 분에서 넘어온 시간
        totalHour += (aHour + bHour) % 12; // 추가하기
    }


    public static void main(String[] args) throws IOException {

        // 입출력 작성
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // test case 입력
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T ; test_case ++){
            st = new StringTokenizer(br.readLine().trim());

            // a의 시간
            int aHour = Integer.parseInt(st.nextToken());
            int aMinute = Integer.parseInt(st.nextToken());
            // b의 시간
            int bHour = Integer.parseInt(st.nextToken());
            int bMinute = Integer.parseInt(st.nextToken());

            // 결과 넣기
            totalTime(aHour, aMinute, bHour, bMinute);
            sb.append("#").append(test_case).append(" ").append(totalHour).append(" ").append(totalMinuate).append("\n");
        } // end for testcase

        System.out.println(sb);

    }
}
