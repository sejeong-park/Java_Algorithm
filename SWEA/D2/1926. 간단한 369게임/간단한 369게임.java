
/*
* @author 박세정
* SWEA_1926_간단한 369 게임
* 문제 해석
* - 3, 6, 9 가 들어있는 수는 말하지 않는다.
* - 35 -> 박수 한번 36 - 박수 두번
*
* */

import java.util.Scanner;

public class Solution {

    static StringBuilder sb;

    public static String clapSign(int cnt){
        String result = "";

        for (int idx = 0; idx < cnt; idx++){
            result += "-";
        }
        return result;
    }

    public static String find369(String number){
        // 각 자리수로 찾기
        int cnt = 0; // 3,6,9
        for (int idx = 0; idx < number.length() ; idx++){
            int num = number.charAt(idx) - '0';
            if (num == 3 || num == 6 || num == 9){
                cnt++;
            }
        }

        if (cnt == 0){
            return number;
        } else{
            return clapSign(cnt);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        int N = sc.nextInt();

        String numString = "";

        for (int number = 1; number <= N; number++){
            // 자리수 탐색
            // Int형 String으로 변환
            sb.append(find369(Integer.toString(number))).append(" ");
        }
        System.out.println(sb);
    }
}
