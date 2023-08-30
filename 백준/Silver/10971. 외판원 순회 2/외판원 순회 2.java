
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @date 23.08.30
 * @problem BOJ_10971 외판원순회2
 * 
 * [문제]
 * - 1번부터 N번까지 번호가 매겨져 있는 도시 & 도시 사이에 길 존재
 * - 한번 갔던 도시로는 다시 돌아갈 수 없다.
 * - 가장 적은 비용을 들이는 여행 계획
 * 
 * [출력]
 * - 외판원의 순회에 필요한 최소 비용 출력
 * 
 * @author sejeong-park
 * */
public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;

	static int cityCount;
	static int[][] map;
	
	// 비용 경우의 수
	static boolean [] visited;
	static int result;
	
	public static void dfs(int startIdx, int cityIdx, int depth, int totalCnt) {
		if (totalCnt > result) return ; // 조건 미달
		
		// 1. 기저조건
		if (depth == cityCount-1) { // 깊이가 cityCount와 같이 탐색했을 경우
			
			// 다시 시작지점으로 갈 수 없으면 리턴
			if (map[cityIdx][startIdx] == 0) return;
			
			// 그렇지 않으면 순환하면서 가는 거리 한번 더 더해주기
			totalCnt += map[cityIdx][startIdx];
			result = Math.min(result, totalCnt);
			

			return; // 종료
		}
		
		
		// 2. 진행조건

		// 선택을 했을 경우
		for (int nextIdx = 0; nextIdx < cityCount; nextIdx++) {
			// 이미 방문한 idx이거나, 해당없는 것일 때
			if (visited[nextIdx] || map[cityIdx][nextIdx] == 0)
				continue;
	
			visited[nextIdx] = true;
			dfs(startIdx, nextIdx, depth+1, totalCnt+map[cityIdx][nextIdx]); 
			visited[nextIdx] = false; // 풀어주기
		}
		// 현재 인덱스에서 선택을 하지 않은경우
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		br = new BufferedReader(new InputStreamReader(System.in));
		
		cityCount = Integer.parseInt(br.readLine().trim());
		map = new int [cityCount][cityCount]; 
		
		for (int row = 0; row < cityCount; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < cityCount; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		} // end for input
		
		result = Integer.MAX_VALUE;

		
		// start 시작하는 도시 지정해주기 -> 돌아올때 일치해야함 (순환)
		for (int startIdx = 0; startIdx < cityCount; startIdx++) {
			visited = new boolean[cityCount]; // visited 객체 생성
			visited[startIdx] = true;  // 처음 시작 index 방문
			
			dfs(startIdx, startIdx, 0, 0); // 재귀 탐색 
		}
		
		System.out.println(result);
		
	}

}
