
import java.util.Scanner;

class Solution {
    /*
    * 입력 : 8자리의 날짜 (날짜가 유효하지 않은 경우 -1 리턴)
    * 1. 4자리, 2자리 2자리
    * */

    public static boolean is_valid(int month, int day){
        // 1. month 계산 1월 부터 12월 까지
        if (1<=month && month <=12) {
            // 2. month 계산 - 문제에서 주어진 각 월에 해당하는 일 수인지 검사
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                if (1<=day && day <=31){
                    return true;
                }
            }else if (month == 4 || month == 6 || month == 9 || month == 11){
                if ( 1<= day && day <= 30){
                    return true;
                }
            }else if (month == 2){
                if (1<= day && day <= 28){
                    return true;
                }
            }
        }
        // true가 아닐 경우 false 반환
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int test_case = 1; test_case <= T ; test_case ++){
            String input = sc.next();
            // 자바에서 문자열 자르기 => str.substring(start, end);
            // 자바에서 문자열을 정수형으로 변환하기 => Integer.parseInt(str)
            int year = Integer.parseInt(input.substring(0, 4)); // 년도를 문자열에서 정수형으로 변환
            int month = Integer.parseInt(input.substring(4, 6)); // 달을 문자열에서 정수형으로 변환
            int day = Integer.parseInt(input.substring(6)); // 일자를 문자열에서 정수형으로 변환

            // 참일 경우
            if (is_valid(month, day)){
                System.out.printf("#%d %04d/%02d/%02d\n", test_case, year, month, day);
            }else {
                System.out.printf("#%d -1\n", test_case);
            }
        }
    }

}
