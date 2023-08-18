

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * SWEA_1873_상호의 배틀 필드
 * 
 * [문제]
 * 1. 사용자의 전차의 동작
 * 	- . : 평지가 
 * 
 * 문제 풀이 시간 : 3시 시작
 * */
public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// map 초기화
	static char[][] gameMap;
 	static char[] userMap;
 	
 	// delta Row, Col -> 상하 좌우
 	static final int[] deltaRow = {-1,1,0,0};
 	static final int[] deltaCol = {0,0,-1,1}; 	
 	static HashMap<Character, Integer> moveKey;
 	static HashMap<Character, Integer> carKey;
 	
 	// 전차
 	static class Car{
 		
 		int row;
 		int col;
 		int direction;
 		Car(int row, int col, int direction){
 			setNextPoint(row, col, direction);
 		}
 		
 		public void setNextPoint(int row, int col, int direction) {
 			this.row = row;
 			this.col = col;
 			this.direction = direction;
 		}
 		
 	}
 	
 	// 전차 static 객체
 	static Car car; // 기준하는 전차
 	static int direction; // 전차의 초기 방향 값
 	
 
 	// 처음 시작하는 전차의 위치를 찾는 함수
 	public static void findCar(int row) {
 		// direction 초기화
 		// 맵의 좌우 값
 		for (int col = 0; col < gameMap[row].length; col++) {
	 		// 위쪽을 바라보는 함수
 			// 만약 주어진 문자열들이라면, 
	 		if (gameMap[row][col] == '^' || gameMap[row][col] == 'v' || gameMap[row][col] == '<' || gameMap[row][col] == '>') {
	 			direction = carKey.get(gameMap[row][col]);
				car.setNextPoint(row, col, direction);// 시작값 지정
				gameMap[row][col] = '.'; // 평지로 변경해주기			
	 		} 
 		}
 		
 	}
 	
 	// 폭탄이 터지는.. -> DFS
 	public static void goBomb(int row, int col) {
 		
 		// 종료 조건
 		// *, # 이면 종료 -> 벽에 닿아 종료
 		if (gameMap[row][col] == '*' || gameMap[row][col] == '#') {
 			// 만약 벽돌로 만들어진 벽이라면
 			if (gameMap[row][col] == '*') {
 				// 벽돌이라면, 평지로 변경
 				gameMap[row][col] = '.'; // 평지 되었다!
 			}
 			return; // 종료 
 		}
 		
 		// 진행 조건 -> car의 direction은 변경되지 않는다.
 		int nextRow = row + deltaRow[car.direction];
 		int nextCol = col + deltaCol[car.direction];
 		// 맵 밖에 잇으면 종료
 		if (0 > nextRow || nextRow >= gameMap.length || 0 > nextCol || nextCol >= gameMap[0].length) return;
 		
 		// 맵 안에 있으면 계속 탐색
 		goBomb(nextRow, nextCol);
 	}
 	
 	// 전차의 이동 실행 함수
 	public static void goMove(char key) {
 		// Shoot : 전차가 현재 바라보고 잇는 방향으로 포탄 발사
 		if (key == 'S') {
 			// 포탄 발사 시 
 			// 포탄은 '*' , '#'에 충돌하거나 게임 밖으로 나갈때까지 직진한다.
 			// 포탄이 벽에 부딪히면 -> 포탄 소멸
 			// 부딪힌 '*' 이라면, 벽은 파괴 되어서 평지(.)가 된다.
 			goBomb(car.row, car.col); // 현재 위치 기준으로
 		}else {
 			// 방향의 경우 -> HashMap에 있는 방향 키만큼 회전
 			int nextDir = moveKey.get(key);
 			int nextRow = car.row + deltaRow[nextDir];
 			int nextCol = car.col + deltaCol[nextDir];
 			/*
 			 * 실수 했던 부분 : 문제에서 방향 정보는 계속 유효한 값 (맵 밖으로 나가도 방향은 변경된다.)
 			 * */
 			car.setNextPoint(car.row, car.col, nextDir); // 방향은 변경
 			
 			// 맵 밖으로 나가는 지 확인
 			if (0<=nextRow && nextRow < gameMap.length && 0 <= nextCol && nextCol < gameMap[0].length) {
 				// 만약에 맵 밖으로 나가는 경우가 평지라면,
 				if (gameMap[nextRow][nextCol] == '.') {
 					car.setNextPoint(nextRow, nextCol, nextDir); // 다음 좌표를 세팅
 				} 				
 			}
 		}
 		
 	}
 	
 	// key 값으로 문양 찾기
 	public static <K,V> K getKey(Map<K,V> map, V value) {
 		for (K key: map.keySet()) {
 			if (value.equals(map.get(key))) {
 				// value
 				return key;
 			}
 		}
 		return null;
 	}
 	
 
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입력 받기
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 기본 move key 생성
		moveKey = new HashMap<>();
		moveKey.put('U', 0);
		moveKey.put('D', 1);
		moveKey.put('L', 2);
		moveKey.put('R', 3);
		moveKey.put('S', -1);

		// car에 문양에 따라 매칭
		carKey = new HashMap<>();
		carKey.put('^', 0);
		carKey.put('v', 1);
		carKey.put('<', 2);
		carKey.put('>', 3);
		
		
		// TEST CASE
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case ++) {
			
			st = new StringTokenizer(br.readLine().trim());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			
			// 배열 입력 
			gameMap = new char[height][width];
			// car 값 초기화 -> 갱신 해야함
			direction = -1;
			car = new Car(0,0,direction); // 초기화
			for (int row = 0; row < height ; row++) {
				gameMap[row] = br.readLine().trim().toCharArray(); // char
				// 방향 값이 초기화 되지 않았다면, 전차 위치 찾고, 객체 생성 해주기
				if (direction == -1) {
					findCar(row);
				}
			} // end for input gameMap
			
			// 사용자 입력
			int N = Integer.parseInt(br.readLine().trim());
			userMap = new char[N];
			userMap = br.readLine().trim().toCharArray();
			
			// 게임 순차적으로 실행하기
			for (int play = 0; play < N; play++) {
				// 전차의 이동
				goMove(userMap[play]);
			}
			
			// 모든 게임이 끝나고, map 그리기
			// 현재 위치 방향대로 그림그리기
			
			gameMap[car.row][car.col] = getKey(carKey, car.direction); // Key값으로 대체
			
			sb.append("#").append(test_case).append(" ");
			for (int row = 0; row < height; row ++) {
				for (int col = 0; col <width ; col++) {
					sb.append(gameMap[row][col]);
				}
				sb.append("\n");
			}
			
		} // end for testcase
		System.out.println(sb);
		
	}

}
