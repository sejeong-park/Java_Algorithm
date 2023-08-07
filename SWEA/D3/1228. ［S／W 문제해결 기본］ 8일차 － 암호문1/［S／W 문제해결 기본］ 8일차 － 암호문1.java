

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/*
* 문제 해석
* 0 ~ 9999999 사이의 수 나열
* (삽입 x, y, s) : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입 - s는 덧붙일 숫자들
* 수정된 결과의 처음 10개 숫자를 출력
* */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringTokenizer stLine;
    static StringBuilder sb;

    static List<String> secretKeyList;

    static int N, M;
    static int x, y;

    public static void main(String [] args) throws IOException {

        // 입출력 받기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 테스트 케이스
        for (int test_case = 1 ; test_case <= 10; test_case ++ ){

            // 원본 암호 입력 받기 -> List로 입력
            secretKeyList = new LinkedList<>();

            // 입력 받기
            N = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());

            // 암호 리스트에 담기
            for(int count = 0; count<N; count++){
                secretKeyList.add(st.nextToken());
            }
            // 명령의 개수 입력
            M = Integer.parseInt(br.readLine().trim());
            // 변경할 명령할 입력 -> I 로 구분
            st = new StringTokenizer(br.readLine().trim());
            // 네번째 줄 명령어의 개수 시작
            for (int count = 0; count<M; count++){
                st.nextToken(); // 명령 I 버리기

                x = Integer.parseInt(st.nextToken()); // 앞에서부터 x의 위치 바로 다음에
                y = Integer.parseInt(st.nextToken()); // y개의 숫자를 삽입

                // List의 특정 위치로 삽입한다.
                for (int index = 0; index < y ; index ++){
                    // 다음으로 받는 입력 토큰
                    secretKeyList.add(x + index, st.nextToken());
                }

            } // end for 입력 & I로 구분된 추가 리스트

            // 마지막 조건
            // 수정된 항의 10개를 출력하라.
            sb.append("#").append(test_case).append(" ");
            for (int count = 0; count < 10; count++){
                sb.append(secretKeyList.get(count)).append(" "); // 다시 앞에서부터 빼기 ㅎㅎ
            }//
            sb.append("\n");

        } // end for test_case
        System.out.println(sb);

    } // main
}
