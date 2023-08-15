
import java.util.Scanner;

public class Solution {

    static boolean[] countNum;
    static int count;
    static int result;
    static int N;
    static StringBuilder sb;

    public static void CountSheep(int target){
        String tmp = Integer.toString(target); // int -> string
        // 한글자씩 끊어 읽기
        for (int idx = 0 ; idx < tmp.length() ; idx++){
            int intNum = Integer.parseInt(tmp.substring(idx, idx+1)); // 한글자씩 끊기
            if (!countNum[intNum]){
                countNum[intNum] = true; // true
                count++;
            }
        }
        // 기저 조건 -> 10개 다 탐색했다면
        if (count == 10){
            // tmp를 출력
            sb.append(tmp).append("\n");
            return;
        }
        // 진행 조건
        // 만약 그렇지 않다면
        result ++; // 한번 더 세기
        CountSheep(N * result);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            N = sc.nextInt();
            countNum = new boolean[10]; // 양을 세자.
            count = 0;
            result = 1;
            // String N을 String으로 변환 ->
            sb.append("#").append(test_case).append(" ");
            CountSheep(N) ;
        } // end for test_case
        System.out.println(sb);

    }

}
