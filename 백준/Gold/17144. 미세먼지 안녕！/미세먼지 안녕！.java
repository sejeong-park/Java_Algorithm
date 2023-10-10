
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_17144_미세먼지 안녕
 * 
 * 1. 미세먼지의 확산 
 *  - 미세먼지는 인접한 네 방향으로 확산된다.
 *  - 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로 확산이 일어나지 않는다.
 *  - 값 / 5d이고 , 소수점 버림
 *  - (r,c)는 a - (a/5) * (확산된 방향의 개수)
 *  
 * 2. 공기 청정기의 작동
 *  - 공기 청정기의 위쪽 바람은 반시계 방향으로 순환 
 *  - 아래쪽은 시계방향으로 순환
 * */


public class Main {

	// 입출력 선언
	static BufferedReader br;
	static StringTokenizer st;
	
	// 맵정보
	static int rowSize, colSize, time;
	static int[][] map;
	
	// 기본 값
	static final int [] deltaRow = {-1, 1, 0, 0};
	static final int [] deltaCol = {0, 0, -1, 1};
	
	
	// dustCleaner
	static Point[] dustCleaner;
	
	
	// 맵 밖에 있는 지 확인하기
	public static boolean inMap(int row, int col) {
		
		if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
			return false;
		}
		return true;
	}
	
	
	// 미세먼지의 확산
	public static void spreadDust() {
		
		int [][] newMap = new int[rowSize][colSize];
		
		
		int spreadCnt, spreadAmount;
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				// 먼지가 존재한다면
				if (map[rowIdx][colIdx] > 0) {
					spreadCnt = 0; // 퍼지기 가능한 방향의 수
					spreadAmount = map[rowIdx][colIdx] / 5;
					for (int direction = 0; direction < 4; direction++) {
						int nextRow = rowIdx + deltaRow[direction];
						int nextCol = colIdx + deltaCol[direction];
						
						if (!inMap(nextRow, nextCol)) continue;
						if (map[nextRow][nextCol] == -1) continue; // 이동하는 칸에 공기청정기가 있다면
						// 퍼지기가 가능하다면
						newMap[nextRow][nextCol] += spreadAmount;
						spreadCnt ++;
					}
					// 현재 자기 자신에도 갱신해줄 양
					newMap[rowIdx][colIdx] += map[rowIdx][colIdx] - (spreadAmount * spreadCnt);
				}
			}
		} // end for map
		
		map = newMap; // 맵 갱신		
	}
	
	// 맵 복사하기
	public static int[][] copy(int [][] map) {
		
		int [][] newMap = new int[rowSize][colSize];
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			newMap[rowIdx] = map[rowIdx].clone();
		}
		
		return newMap;
	}
	
	// 공기청정기의 공기 교환
	public static void clearAir() {
		// 공기청정기에 의해 갱신된 맵
		
		
		// 새로운 맵 생성
		int [][] newMap = copy(map); // 맵 카피 하기
		
		// 1. 위쪽 공기 흐르기
		Point top = dustCleaner[0];	
		// 왼 -> 오
		for (int colIdx = 0; colIdx < colSize-1; colIdx++) {
			newMap[top.x][colIdx + 1] = map[top.x][colIdx];
		}
		//  하 -> 상
		for (int rowIdx = top.x; rowIdx > 0; rowIdx--) {
			newMap[rowIdx-1][colSize-1] = map[rowIdx][colSize-1];
		}
		// 오 -> 왼
		for (int colIdx = colSize-1; colIdx > 0; colIdx--) {
			newMap[0][colIdx-1] = map[0][colIdx];
		}
		// 상 -> 하
		for (int rowIdx = 0; rowIdx < top.x; rowIdx++) {
			newMap[rowIdx+1][0] = map[rowIdx][0];
		}
		
		
		// 2. 아래쪽 공기 흐르기
		Point down = dustCleaner[1];
		// 왼 -> 오
		for (int colIdx = 0; colIdx < colSize-1; colIdx++) {
			newMap[down.x][colIdx+1] = map[down.x][colIdx];
		}
		// 상 -> 하
		for (int rowIdx = down.x; rowIdx < rowSize-1; rowIdx++) {
			newMap[rowIdx+1][colSize-1] = map[rowIdx][colSize-1];
		}
		// 오 -> 왼
		for (int colIdx = colSize-1; colIdx> 0; colIdx--) {
			newMap[rowSize-1][colIdx-1] = map[rowSize-1][colIdx];
		}
		// 하 -> 상
		for (int rowIdx = rowSize-1; rowIdx > down.x; rowIdx--) {
			newMap[rowIdx-1][0] = map[rowIdx][0];
		}
		
		
		// 이동한 공기 청정기 다시 잡아오기 (둘 다 왼쪽에서 오른쪽 흐름)
		for (int clearIdx = 0; clearIdx < dustCleaner.length; clearIdx++) {
			Point point = dustCleaner[clearIdx];
			newMap[point.x][point.y+1] = 0; // 공기청정기에서 부는 바람은 정화
			newMap[point.x][point.y] = -1; // 공기 청정기 다시 세우기
		}
		
		
		map = newMap; // 맵 갱신
	}
	
	// 1초에 움직이는 메서드 -> 1초 작업 양 
	public static void spendTime() {
		
		// 1. 미세먼지가 확산된다.
		spreadDust();

		// 2. 공기 청정기가 작동한다.
		clearAir();

	}
	
	public static int countingDust() {
		
		int result = 0;
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				if (map[rowIdx][colIdx] > 0) {
					result += map[rowIdx][colIdx];
				}
			}
		}
		return result;
	}
	

	
	public static void main(String[] args) throws IOException{
		
		// 입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// r,c,t
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		// 맵 정보 입력
		map = new int[rowSize][colSize];
		dustCleaner = new Point[2]; // 상, 하 공기청정기 넣기
		int dustIdx = 0;
		for (int rowIdx =0 ; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if (map[rowIdx][colIdx] == -1) {
					dustCleaner[dustIdx++] = new Point(rowIdx, colIdx); // 좌표값 넣기
				}				
			}
		} // end for map 
		
		// 시간을 보내면
		for (int timeCount = 0; timeCount < time; timeCount++) {
			spendTime();
		} 
		
		System.out.println(countingDust());
		
	}
	
	
	// 디버깅을 위한 메서드
	public static void print(int [][] map) {
		System.out.println("디버깅용 Map 정보");
		for (int rowIdx =0 ; rowIdx < rowSize; rowIdx++) {
			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				System.out.print(map[rowIdx][colIdx] + " ");
			}
			System.out.println();
		}
	}
	
	
	
}
