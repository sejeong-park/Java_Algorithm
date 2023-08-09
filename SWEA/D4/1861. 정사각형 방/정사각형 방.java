
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA_1861_정사각형 방
 * [문제 이해]
 * N*N 개의 
 * 당신이 어떤 방에 잇다면, 상하좌우에 있는 다른 방으로 이동할 수 있다.
 * 이동하려는 방에 적힌 숫자가 현재 적힌 방의 숫자보다 정확히 1이 커야 한다.
 * 처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동?
 * 
 * 
 * */

public class Solution {

	static StringTokenizer st;
	
	static int N;
	// delta direction
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	//dfs에 필요한 값
	static int [][] array;
	static boolean [][] visited;
	static int depth;
	

	// 처음부터 depth 깊이로 찾기
	public static void dfs(int x, int y) {
		// 방문 표시 
		visited[x][y] = true;
		depth ++;
		// 4방향 탐색
		for (int direction = 0; direction < 4 ; direction++) {
			
			int nx = x + dx[direction];
			int ny = y + dy[direction];
			
			if (0<=nx && nx<N && 0<=ny && ny<N && !visited[nx][ny]) {
				if (array[x][y] + 1 == array[nx][ny]) {
					dfs(nx, ny); // 재귀적으로 탐색
					
				}
			}
			
		}
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 자바 입출력 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		// test case
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			N = Integer.parseInt(br.readLine().trim());
			array = new int[N][N]; // N*N의 이차원 배열\
			// 입력 
			for (int row = 0; row<N;row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					// 입력 받기
					array[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// N*N 배열에 모두 넣기 
			
			int maxDistance = Integer.MIN_VALUE; // 이동할 수 있는 방의 개수
			int roomNumber = Integer.MAX_VALUE; // 이동할 수 있는 방번호
			
			for (int x = 0; x < N; x++) {
				for (int y =0; y < N ; y++) {
					visited = new boolean[N][N]; // visited 초기화
					depth = 0; // 결과 값 초기화 
					dfs(x,y);
					// System.out.println(array[x][y] + " - " +depth);
					// depth의 최대 길이 비교
					if (maxDistance <= depth) {
						// 만약 depth가 더 깊다면, 출력 값 갱신
						if (maxDistance == depth && roomNumber < array[x][y]) continue; // 만약 내가 depth가 동일하거나 같아서 갱신하기 전에, 만약 현재의 룸 번호가 최저보다 크다면, 패스
						maxDistance = depth;
						roomNumber = array[x][y];
					}
				}
			}

			sb.append("#").append(test_case).append(" ").append(roomNumber).append(" ").append(maxDistance).append("\n");			
		}	
		
		System.out.println(sb);
	} // end main

}
