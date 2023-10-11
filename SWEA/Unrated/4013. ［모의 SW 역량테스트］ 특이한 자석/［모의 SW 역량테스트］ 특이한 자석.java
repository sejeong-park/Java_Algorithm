

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	// 입출력
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] map;
	static final int STARTIDX = 2;
	static final int ENDIDX = 6;
	
	// 왼쪽으로 재귀
	public static void rotateLeft(int rotateNum, int rotateDir) {
		// 자신보다 적은 수들
		int BEFORE_IDX = rotateNum+1; // 자신보다 오른쪽
		if (rotateNum < 1 || map[BEFORE_IDX][ENDIDX] == map[rotateNum][STARTIDX]) {
			return; // 종료조건 : 톱니의 index는 0이하로 없음 & 같은 극이면 회전하지 않음
		}
		if(map[BEFORE_IDX][ENDIDX] != map[rotateNum][STARTIDX]) {
			rotateLeft(rotateNum-1, rotateDir * -1); // 현재 배열 기준으로 톱니가 맞물리므로 재귀먼저
			rotate(rotateNum, rotateDir); // 현재 회전
		}

	}
	
	// 오른쪽으로 재귀
	public static void rotateRight(int rotateNum, int rotateDir) {
		// 자기보다 큰 수들
		int BEFORE_IDX = rotateNum-1; // 자신보다 왼쪽
		if(rotateNum > 4 || map[BEFORE_IDX][STARTIDX] == map[rotateNum][ENDIDX]) {
			return; // 종료 조건 : 톱니의 마지막 index는 4까지임 & 같은 극이면 회전하지 않음
		}
		if (map[BEFORE_IDX][STARTIDX] != map[rotateNum][ENDIDX]) {
			rotateRight(rotateNum+1, rotateDir * -1); // 다음 톱니 회전
			rotate(rotateNum, rotateDir); // 현재 톰니 회전
		}

	}
	
	// rotate
	public static void rotate(int rotateNum, int rotateDir) {
		
		int [] newIdxMap = new int[8];
		
		if (rotateDir == -1) {
			// 반시계방향 정렬
			for (int colIdx = 7; colIdx >= 1; colIdx--) {
				newIdxMap[colIdx-1] = map[rotateNum][colIdx];
			}
			newIdxMap[7] = map[rotateNum][0];
			
		}else if (rotateDir == 1) {
			// 시계방향 정렬
			for (int colIdx = 0; colIdx < 7; colIdx++) {
				newIdxMap[colIdx+1] = map[rotateNum][colIdx];
			}
			newIdxMap[0] = map[rotateNum][7];
		}
		// 갱신
		map[rotateNum] = newIdxMap;
	}

	// 디버깅용 print
	public static void print(int [][] map) {
		
		for (int rowIdx = 0; rowIdx < map.length; rowIdx++) {
			for (int colIdx = 0; colIdx < map[0].length; colIdx++) {
				System.out.print(map[rowIdx][colIdx] + " ");
			}
			System.out.println();
		}
	}
	
	public static int getPoint() {
		
		int result = 0;
		for (int engIdx = 1; engIdx < 5; engIdx ++) {
			int value = map[engIdx][0];
			if(value == 1) { // S극이라면
				 result += (int) Math.pow(2, engIdx-1);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			
			int rotateCnt = Integer.parseInt(br.readLine().trim());
			map = new int [5][8];
			for (int engIdx = 1; engIdx < 5; engIdx++) {
				// 입력 받기
				st = new StringTokenizer(br.readLine().trim());
				for (int colIdx = 0; colIdx < 8; colIdx++) {
					map[engIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			// 회전
			for (int rotateIdx = 0; rotateIdx < rotateCnt; rotateIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				int rotateNum = Integer.parseInt(st.nextToken());
				int rotateDir = Integer.parseInt(st.nextToken());
				// 재귀
				
				rotateLeft(rotateNum-1, rotateDir * -1);
				rotateRight(rotateNum+1, rotateDir * -1);
				rotate(rotateNum, rotateDir);
				// print(map);
			}
			sb.append("#").append(tc).append(" ").append(getPoint()).append("\n");
			
		} // end for testCase
		
		System.out.println(sb);
		
	}

}
