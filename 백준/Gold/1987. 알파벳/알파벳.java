

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * BOJ_1987_알파벳_박세정
 * [문제 이해]
 * - R*C 
 * - 모든 칸에 적혀있는 알파벳과 달라야한다. -> 같은 알파벳이 적힌 칸을 두번 지날 수 없다.
 * - BFS 로 탐색을 하면서 지나간 알파벳에 대해 visited 처리를 하자.
 * - DFS 로 풀어보자.
 * */

public class Main {

	// 입출력
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	// R * C size
	static int R, C;
	static char[][] charMap;
	
	// delta 상하 좌우
	static int[] deltaRow = {-1,1,0,0};
	static int[] deltaCol = {0,0,-1,1};
	
	static boolean [] alphabet;
	// 결과
	static int result;
	
	// DFS
	public static void dfs(int row, int col, int count) {
		
		// 기저 조건 
		// 기존에 탐색했던 알파벳이 있다면 종료
		if (alphabet[alphabetIdx(charMap[row][col])]){
			result = Math.max(count, result);
			return; // 종료
		}
		
		alphabet[alphabetIdx(charMap[row][col])] = true;
		// 유도 조건 
		for (int direction = 0; direction < 4; direction++) {
			// 다음 으로 넘어가기
			int nextRow = row + deltaRow[direction];
			int nextCol = col + deltaCol[direction];
			
			// 범위 탐색 내
			if (0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C) {
				// 다음으로 넘어가기
				dfs(nextRow, nextCol, count+1);	
			}
		}
		alphabet[alphabetIdx(charMap[row][col])] = false;
	}
	
	public static int alphabetIdx(char alphabet) {
		return alphabet - 'A';
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// 입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 배열 입력 초기화
		charMap = new char[R][C];
		for (int row = 0; row < R; row ++) {
			// 입력
			charMap[row] = br.readLine().trim().toCharArray(); // String -> char
		}
		
		// dfs 탐색
		result = 0;
		alphabet = new boolean['Z'-'A' + 1]; // 알파벳 visited
		
		dfs(0,0,0); // 첫번째 칸 부터 시작
		alphabet[alphabetIdx(charMap[0][0])] = true;
		System.out.println(result);
		
		
	}

}
