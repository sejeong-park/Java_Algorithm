
import java.util.Scanner;

/*
 * BOJ_2839 설탕배달
 * 
 * 상근이 섩탕 배달 -> 정확히 N 배달
 * 봉지 3, 5 상근이는 최대한 적은 봉지
 * */
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		// N키로그램 배달할 때 배달하는 최소 개수 
		
		int N = sc.nextInt();
		// 5, 3
		int [] cnt = new int[2]; // 5봉지와 3봉지를 따로 카운트 할 것
		int remain = 0;
		int flag = 0;
		cnt[0] = N/5; // N을 몇개 나누냐.
		remain = N%5; // 5로 나누고 나머지
		
		// 이제 탐색
		while (true){
			if (cnt[0] < 0) {
				flag = 1;
			}
			// 만약 3으로 나누어 떨어지면?
			if (remain%3 == 0) {
				cnt[1] = remain / 3;
				break;
			}
			// 만약 3으로 나누어떨어지지 않았다면?
			remain += 5; // 5개에서 꺼내서 넣는다.
			cnt[0] -= 1;
		}
		if (flag == 1) {
			System.out.println(-1);
		}else {
		// 초기값과 결과값이 일치하다면 리턴	
			System.out.println(cnt[0] + cnt[1]);
		}
	}
	
}
