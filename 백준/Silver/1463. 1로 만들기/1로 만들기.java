

import java.util.Arrays;
import java.util.Scanner;

/**
 * @date : 23.08.29
 * @problem : BOJ_1463_1로만들기
 * 
 * 1. X가 3으로 나누어 떨어지면 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * 결론 : 정수 N이 주어졌을 때 세 개를 적절히 사용하여 1을 만든다 . -> 연산을 사용하는 횟수의 최솟값
 * 
 * @author sejeong-park
 * */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int [] dp = new int[n+1]; // dp 테이블 생성
		
		for (int idx = 2; idx < n+1; idx++) {
			dp[idx] = dp[idx-1] +1;
			if (idx%2==0) dp[idx] = Math.min(dp[idx], dp[idx/2]+1);
			if (idx%3==0) dp[idx] = Math.min(dp[idx], dp[idx/3]+1);
		}
		
		
		System.out.println(dp[n]);
		
		
	}

}
