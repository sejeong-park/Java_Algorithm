import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int T; // 테스트 케이스의 개수
	static int map_size, max_depth; // 지도의 크기, 최대 공사 가능 깊이
	static int[][] map; // 지도에 나타나는 지형의 높이를 저장할 2차원 배열
	static final int[] dc = {-1, 1, 0, 0};
	static final int[] dr = {0, 0, -1, 1};
	static boolean[][] visited;
	static int peek_height; // 가장 높은 봉우리의 높이
	static int longest_trail; // 가장 긴 등산로의 길이
	
	public static void dfs(int col, int row, int distance, int depth) {
		int next_col = 0;
		int next_row = 0;
		
		if(distance > longest_trail) { // 등산로의 최대 길이 갱신
			longest_trail = distance;
		}
		visited[col][row] = true;
		
		for(int idx = 0; idx < 4; idx++) {
			next_col = col + dc[idx];
			next_row = row + dr[idx];
			// 지도의 범위를 벗어나거나 이미 방문한 봉우리인 경우...
			if(next_col < 0 || next_col >= map_size || next_row < 0 || next_row >= map_size || visited[next_col][next_row]) continue;
			
			// 다음 봉우리가 현재 봉우리보다 낮은 봉우리인 경우...
			if(map[next_col][next_row] < map[col][row]) {
				dfs(next_col, next_row, distance + 1,depth);
			} else if(depth > 0 && depth > map[next_col][next_row] - map[col][row]) {
				int tmp_height = map[next_col][next_row];
				map[next_col][next_row] = map[col][row] - 1;
				dfs(next_col, next_row, distance + 1, 0);
				map[next_col][next_row] = tmp_height; // 봉우리의 높이 복원
			}
		}
		visited[col][row] = false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			map_size = Integer.parseInt(st.nextToken()); // 지도의 크기 입력
			max_depth = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이 입력
			map = new int[map_size][map_size];
			visited = new boolean[map_size][map_size];
			peek_height = 0; // 가장 높은 봉우리의 높이 초기화
			longest_trail = 0; // 가장 긴 등산로의 길이 초기화
			
			// 지도의 정보 입력
			for(int colIdx = 0; colIdx < map_size; colIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int rowIdx = 0; rowIdx < map_size; rowIdx++) {
					map[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
					if(map[colIdx][rowIdx] > peek_height) { // 가장 높은 봉우리 찾기
						peek_height = map[colIdx][rowIdx];
					}
				}
			}
			
			
			for(int colIdx = 0; colIdx < map_size; colIdx++) {
				for(int rowIdx = 0; rowIdx < map_size; rowIdx++) {
					if(map[colIdx][rowIdx] == peek_height) { // 가장 높은 봉우리인 경우...
						dfs(colIdx, rowIdx, 1, max_depth); // 깊이 우선 탐색
					}
				}
			}
			sb = new StringBuilder();

			sb.append('#').append(test_case).append(' ').append(longest_trail);
			System.out.println(sb.toString());
		}
		
	}
	
}