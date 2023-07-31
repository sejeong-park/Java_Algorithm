import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        // 알파벳 소문자 -> 대문자 변환 프로그램

        Scanner sc = new Scanner(System.in);

        String str = sc.next(); // 소문자 헤드라인 입력
        System.out.println(str.toUpperCase()); // toUpperCase() -> 파이썬에서 upper()
    }
}
