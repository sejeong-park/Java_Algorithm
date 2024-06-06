

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_7490_0만들기
 * 1부터 N깢 오름차순
 * + , - , 공백 숫자 사이에 삽입
 * N이 주어졌을 때 수식의 결과가 0이되는 모든 수식을 찾는 프로그램
 * */
public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static int targetNumber;

    public static Boolean parseCheck(String string) {
        // 공백 제거
        string = string.replaceAll(" ", ""); // 기억하기!
        // 공백을 제거하고 연산자를 포함하여 숫자로 변환
        StringTokenizer numberAndOperation = new StringTokenizer(string, "+|-", true); //
        int sum = Integer.parseInt(numberAndOperation.nextToken()); // 처음 숫자 더하고 시작
        // 토큰이 존재할 때만 반복
        while(numberAndOperation.hasMoreTokens()) {
            String next = numberAndOperation.nextToken();
            // 더하기일 경우
            if (next.equals("+")) {
                int number = Integer.parseInt(numberAndOperation.nextToken());
                sum += number; // 더하기
            } else if (next.equals("-")) {
                int number = Integer.parseInt(numberAndOperation.nextToken());
                sum -= number; // 빼기
            }
        }
        // sum이 0일 경우만 return
        if (sum == 0) {
            return true;
        }
        return false;
    }
    public static void calculation(int number, int size, String string) {

        // 종료 조건
        if (size == targetNumber) {
            // 결과 확인
            if (parseCheck(string)) {
                sb.append(string + "\n");
            }
            return;
        }
        int nextNumber = number + 1;
        calculation(nextNumber, size + 1, string + ' ' +  nextNumber);
        calculation(nextNumber, size + 1, string + '+' + nextNumber);
        calculation(nextNumber, size + 1, string + '-' + nextNumber);
    }

    // 자연수 케이스 구하기
    public static void run(int N) {
        targetNumber = N;
        calculation(1, 1, "1");

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < N; idx ++) {
            int testCase = Integer.parseInt(br.readLine().trim());
            run(testCase);
            sb.append("\n"); // 즐바꿈
        }
        System.out.println(sb);
    }



}
