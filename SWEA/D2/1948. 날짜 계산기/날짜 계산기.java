
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* 가능 조건: 두번째 날짜가 첫번째 날짜보다 항상 크게 주어진다.
* */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int[] monthList = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static int findDateRange(int [] target){
        int result = 0;

        // 만약 시작 달이랑 끝나는 달이 같다면?
        if (target[0] == target[2]){
            result = target[3] - target[1] + 1;
        }else{
            // 같지 않다면!

            // 1. 시작 달이 한달 중에 몇일이나 남았는지 계산
            result += monthList[target[0]] - target[1] + 1;
            // 2. 시작한 달의 다음달 부터과 끝나는 달 사이에 몇 달간의 달 차이 구하기
            for(int month = target[0]+1 ; month < target[2] ; month++){
                result += monthList[month];
            }
            // 3. 끝나는 달의 날짜 길이만큼 더하기
            result += target[3];

        }
        return result;

    }

    public static void main(String[] args) throws IOException {

        // 입출력
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // test case
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++){
            // 입력
            st = new StringTokenizer(br.readLine().trim());
            int [] target = new int[4];
            for (int idx =0 ; idx<target.length; idx++){
                target[idx] = Integer.parseInt(st.nextToken());
            }
            // 차이 계산하기
            sb.append("#").append(test_case).append(" ").append(findDateRange(target)).append("\n");
        }// end for testCase
        System.out.println(sb);
    } // end Main

}
