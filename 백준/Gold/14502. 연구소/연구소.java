

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * BOJ_14502_연구소_박세정
 * 
 * [문제 풀이]
 * - 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈칸으로 모두 퍼져나갈 수 있다.
 * - 세울 벽의 개수는 3개
 * - 구해야 할 것 : 안전영역의 크기 = 바이러스가 퍼질 수 없는 곳  
 * */

public class Main {
	
	// 입출력
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int rowSize, colSize;
	static int [][] map;

	// 상하 좌우
	static int [] deltaRow = {-1, 0, 1, 0};
	static int [] deltaCol = {0, 1, 0, -1};
	
	// 맵의 값
	static final int VIRUS = 2;
	static final int WALL = 1;
	static final int NON_WALL = 0;
	
	// 바이러스 위치 저장하는 리스트
	static List<Point> virusList;
	static List<Point> nonWallList;
	
	static Point[] selectList;
	
	static int totalResult;
	
	
	// 영역 안인지 확인
	public static boolean inMap(int row, int col) {
		
		// 맵 밖에 나갔다면
		if (row < 0 || col < 0 || row >= rowSize || col >= colSize) {
			return false;
		}
		return true;
	}
	
	// 안전 영역 카운팅
	public static int countSafeArea(Point[] selectList) {
		int [][] newMap = copyMap(map); // copy
		boolean [][] visited = new boolean[rowSize][colSize]; // 방문 확인 
		
		// 임시 벽 놓기
		for (int wallIdx = 0; wallIdx < selectList.length ; wallIdx++) {
			// 벽에 인덱스
			newMap[selectList[wallIdx].x][selectList[wallIdx].y] = WALL; 
		}
		
	
		// 바이러스가 퍼진다.
		Deque<Point> queue = new ArrayDeque<>();
		for (Point virusPoint : virusList) {
			queue.add(virusPoint);
		}

		// 바이러스 퍼지기
		while(!queue.isEmpty()) {
			
			Point current = queue.poll(); // 맨 첫번째꺼 빼기
			
			// 사분면 탐색
			for (int direction = 0; direction < 4 ; direction++) {
				int nextRow = current.x + deltaRow[direction];
				int nextCol = current.y + deltaCol[direction];
				
				// 만약 범위 밖에 있거나 하면 continue
				if (!inMap(nextRow, nextCol) || visited[nextRow][nextCol]) continue;
				
				// 벽이나 바이러스라면 continue
				if (newMap[nextRow][nextCol] == VIRUS || newMap[nextRow][nextCol] == WALL) continue;
				
				// 그렇지 않은 경우				
				newMap[nextRow][nextCol] = VIRUS; // 현재 이동한 거리 방문 표시
				visited[nextRow][nextCol] = true;
				queue.add(new Point(nextRow, nextCol));				
			}
		}
		
		
		// safe 영역 개수 세기
		int totalSafeArea = 0;
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for (int colIdx = 0; colIdx < colSize ; colIdx++) {
				if (newMap[rowIdx][colIdx] == NON_WALL) {
					totalSafeArea++;
				}
			}
		}
		// System.out.println(totalSafeArea);
		return totalSafeArea;
	}
	
	
	// 조합
	public static void combination(int selectIdx, int idx) {
		
		
		// 기저 조건 - 두가지 경우
		
		if (selectIdx == selectList.length) {
			int result = countSafeArea(selectList);

			totalResult = Math.max(result, totalResult); // 최대값 갱신
					
			return;
		}
		
		
		if (idx == nonWallList.size()) {
			// 전체 탐색을 완료했을 경우
			return;
		}
		
		
		// 진행 조건
		
		// 1. 선택할 경우
		selectList[selectIdx] = nonWallList.get(idx); 
		combination(selectIdx+1, idx+1);
		
		// 2. 선택하지 않을 경우
		
		combination(selectIdx, idx+1);
		
	}
	
	
	// 벽이 없는 경우 클론하기
	public static int[][] copyMap(int [][] map) {
		int [][] copyMap = new int[rowSize][colSize];
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			copyMap[rowIdx] = map[rowIdx].clone(); // 한줄씩 클론하기
		}
		return copyMap;
	}
	
	public static void print(int[][] map) {
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			System.out.print(Arrays.toString(map[rowIdx]));
		}
		System.out.println();
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 맵의 크기 구하기
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 맵 구하기
		map = new int[rowSize][colSize];
		
		// 바이러스 위치 저장하는 리스트
		virusList = new ArrayList<>();
		// 벽이 없는 리스트
		nonWallList = new ArrayList<>();
		// 맵 입력 받기
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for (int colIdx = 0 ; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				// 만약 바이러스가 있다면, 위치 생성 후 넣기 
				if (map[rowIdx][colIdx] == VIRUS) {
					virusList.add(new Point(rowIdx, colIdx));
				}
				// 벽이 없는 경우
				if (map[rowIdx][colIdx] == NON_WALL) {
					nonWallList.add(new Point(rowIdx, colIdx));
				}
			}
		}
		
		// print(map);
		totalResult = Integer.MIN_VALUE; // 최소값 선언
		
		// 벽이 없는 경우 3개씩 조합하기
		selectList = new Point[3]; // 최대 3개
		combination(0,0);
		System.out.println(totalResult);
	}

}
