
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static BufferedReader br;
    static StringBuilder sb;
    static final int[] moneys = {50000, 10000, 5000, 1000, 500, 100, 50, 10};

    public static void changeToMoney(int inMoney){
        // money 화폐단위 변경해주는 함수
        for (int idx = 0; idx < moneys.length; idx++){
            int result = inMoney/moneys[idx]; // 얼마의 돈으로 거스름 가능한지
            sb.append(result).append(" "); //결과 넣기
            inMoney %= moneys[idx]; // 거스르고 남음 금액
        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T ; test_case++){
            // 돈 입력

            int InMoney = Integer.parseInt(br.readLine().trim());
            sb.append("#").append(test_case).append("\n"); // 출력 값 넣어주기
            changeToMoney(InMoney); // 거스름 기계에 돈 넣기
            sb.append("\n"); // 테케 별 줄바꿈

        } // end for test_case
        System.out.println(sb);
    }

}
