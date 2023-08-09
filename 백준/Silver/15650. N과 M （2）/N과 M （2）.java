import java.util.Scanner;

public class Main {
	
	static int N;
	static int M;
	static int[] targetArray;
	static int[] outputArray;
	
	
	// 조합
	private static void combination(int cnt, int start) {
		
		// 종료 조건
		if (cnt == M) {
			// 아웃 풋 결과 출력
			for(int idx = 0; idx<M; idx ++) {
				System.out.print(outputArray[idx] + " ");
			}
			System.out.println();
			return; // 종료 조건!!
		}
	
		// 조합 조건
		for (int idx = start ; idx < N ; idx ++) {
			outputArray[cnt] = targetArray[idx];
			combination(cnt+1, idx + 1); // 한개씩 더 탐색
		}
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		// 배열 크기대로 초기화
		targetArray = new int[N];
		outputArray = new int[M];
		
		// N과 M이 저장될 일차원 배열 선언해주기
		for (int num = 1; num <= N; num++) {
			targetArray[num-1] = num;
		}
		
		combination(0, 0); // 시작하는 depth, start index
	}

}
