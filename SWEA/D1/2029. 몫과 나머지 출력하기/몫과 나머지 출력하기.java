import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        // 2개의 수 입력 받아, a, b로 나눈 몫과 나머지 출력
        // 이것도 단순 문제니까 Buffered 입출력 연습 ~!

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1 ; test_case <= T ; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.printf("#%d %d %d\n", test_case, (a/b), (a%b));
        }



    }


}
