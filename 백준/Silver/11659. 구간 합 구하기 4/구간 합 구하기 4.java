import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 일반적으로 하면 시간초과 난다.
 * 구간 합 알고리즘 필요
 * 
 * */

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // token 기준 입력 받을 것 
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim(), " "); // split
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// N 배열 입력
		int [] targetArray = new int[N];
		int [] prefixSum = new int[N+1]; // 구간합 배열
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		for (int idx = 0; idx < N; idx ++) {
			targetArray[idx] = Integer.parseInt(st.nextToken());
			prefixSum[idx+1] = prefixSum[idx] + targetArray[idx]; // 구간합 구하기
		}
		 
		
		// M 만큼 인덱스 기준 합 구하기
		
		
		for (int mcnt = 0; mcnt < M ; mcnt++) {
			// 입력
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			int result = prefixSum[end] - prefixSum[start-1];
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
		

	}

}
