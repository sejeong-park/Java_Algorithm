import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* SWEA_1859_백만장자 프로젝트
*
* 문제 이해
* - 연속된 N일 동안 물건의 매매가 예측
* - 하루에 최대 1만큼 구입 & 판매는 얼마든
*
* 문제 풀이
* 1. N 과 매매가 리스트 입력 받기
* 2. 구매 조건 : max 값을 입력 받기 전까지는 구매
* 3. 팔기 조건 : max 에 팔기
*
* 구현 방법
* 1. max 값이 뒤에 모아서 한번에 구매하는 것이 유리하므로 뒤에서부터 탐색
* 2. max값 갱신할 때는 판매하는 날이므로, 차액을 계산할 필요 없음
* 
* 후기 
* - 구현 방법을 역순으로 한다는 것을 떠올리기 어려웠다.
* */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static int[] PriceList;

    public static void main(String[] args) throws IOException {

        // 입출력 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 입력 받기
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1 ; test_case <= T; test_case ++){
            // N 입력 받기 (몇일 동안 장사할 것인지)
            int N = Integer.parseInt(br.readLine().trim());
            // max 값 초기화
            int now = 0; // 첫날 입력 받은 것 부터 시작 할 것

            st = new StringTokenizer(br.readLine().trim()); // line 입력 받기
            PriceList = new int[N]; // 가격 정보를 입력받을 리스트 생성 & 초기화
            for (int day = 0; day < N; day++) {
                // 하나씩 입력 받으면서 판단 (매매가)
                PriceList[day] = Integer.parseInt(st.nextToken()); // 일일 가격 입력 받기
            }

            // 최댓값으로 한번에 사야 하므로 역순으로 탐색한다. (마지막 날 -> 첫날)
            Long result = 0L; // 결과값 초기화
            int maxPrice = Integer.MIN_VALUE; // 가장 비싼 가격 정보
            for (int day = N-1 ; day >= 0; day --){
                // 만약 매매가가 최대값이 갱신되었다면
                if (PriceList[day] > maxPrice) {
                    maxPrice = PriceList[day]; // maxPrice 갱신
                }else{
                    // 만약 갱신되지 못하면 ?
                    // 일일 차액만큼 더해준다.
                    result += maxPrice - PriceList[day]; //
                }
            }
            // 출력 값 생성하기 (Line 기준)
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        // 최종 출력하기
        System.out.println(sb);
    }

}
