

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @date 23.08.30
 * @problem BOJ_1010_다리놓기
 * 
 * [문제]
 * - 도시 동쪽 & 서쪽 일직선 모양의 강
 * - 강의 서에 N개의 사이트, 동쪽에는 M개의 사이트
 * - 서쪽과 동쪽을 다리로 연결하려는데, 이 다릴르 최대한 많이 지으려한다. 
 * - 최대 N개 (서쪽) 다리를 지으려는데
 * - 다리끼리 서로 겹쳐지지 않을 때 다리를 지을 수 잇는 <경우의 수>
 * 
 * -> 문제의 의도 조합 구하기 -> 재귀를 사용하지 않고 !
 * 
 * @author sejeong-park
 * */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] dp; // dp table
	
	// 조합을 DP 메모이제이션으로
	public static int combination(int n, int r) {
		
		if (dp[n][r] > 0) return dp[n][r];
		if (r == 0 || n == r) return dp[n][r] = 1;
	
		return dp[n][r] = combination(n-1, r-1) + combination(n-1, r);
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			
			dp = new int[30][30]; // 문제에 주어진 조건 0<=N < M < 30이라고 문제에 정의되어 있다.
			
			// west, east
			st = new StringTokenizer(br.readLine().trim());
			int west = Integer.parseInt(st.nextToken());
			int east = Integer.parseInt(st.nextToken());
			
			// 두 수의 조합 구하기
			sb.append(combination(east, west)).append("\n");
		}
		
		System.out.println(sb);

	}

}
