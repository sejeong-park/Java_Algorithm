import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 알파벳 입력 문자열
        String str = sc.next();
        // 알파벳 숫자로 변환 (A = 65)
        for(int idx = 0; idx < str.length() ; idx++){
            // String.charAt(index) : String의 index번째 char형으로 변환
            // A는 65이므로, 64만큼 빼주기
            System.out.print(str.charAt(idx) - 64 + " ");
        }
    }
}
