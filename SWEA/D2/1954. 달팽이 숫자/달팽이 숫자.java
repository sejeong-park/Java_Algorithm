import java.io.*;
import java.util.Arrays;


/*
 * @author : sejeong-park
 * SWEA_1954_달팽이숫자
 * 1. T 입력
 * 2. N 입력
 * 3. 달팽이 입력 받기
 * 	- 달팽이의 델타 배열 순서 (우, 하, 좌, 상)
 *  - 첫 줄은 1번 , 그 이후로는 2번에 걸쳐 회전한다.
 *  - 회전 수는 N*2-1까지 반복
 *  
 *  // 계속 디버깅 해서 풀었는데, 실패 했던 원인
 *  1. 처음에 첫 줄은 1번, 그 이후로 2번에 걸쳐 distance를 -1씩 넣어주었다.
 *  2. 코드를 짜면서 달팽이의 시작 점을 (0,0)로 지정하고 처음 설계 했던 대로 풀어서 index가 꼬였다 (처음대로 할거였으면, -1,-1에서 시작해줘야함)
 *  3. X,Y = (0,0)에서 시작하려면 DISTANCE -- 조건이 처음 3번까지는 회전하고 이후로는 2번에 걸쳐 회전한다.
 *  // 달팽이 변수의 변경으로 인한 실패
 * */

public class Solution {
	
	// 입출력 선언
	static BufferedReader br;
	static StringBuilder sb;
	
	// 델타 배열 - 달팽이 이기 때문에 회전 순서 (우, 하, 좌, 상)
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	// SNAIL 달팽이 숫자를 만드는 함수
	// 입력 : n 
	// 출력 : 2차원 배열
	public static int[][] snail(int N){

		// 이차원 배열 초기화
		int [][] snailList = new int[N][N]; // N*N 사이즈의 2차원 배열
		final int MAX_TURN = 2*N-1; 			// 모서리를 마주하면 회전한다.	 
		
		// 달팽이 변수
		int distance = N-1; 		// distance는 회전 되어야 하는 길이 
		int turnCnt = 3; 		// 처음은 1번에 회전하지만, 이후로는 2번 회전이 되어야 distance -1 만큼 준다.
		
		// 달팽이 시작 조건 (0,0)부터 출발 number는 1부터 ++
		int x = 0; int y = 0;
		snailList[x][y] = 1;
		int direction = 0; // 우측 부터 시작
		
		// 모서리를 마주하면 회전하는 숫자 (while문 대신에)
		for (int turn = 0; turn < MAX_TURN; turn++) {
			
			// 만약 turnCnt가 0이 되면, 이동하는 거리가 1씩 감소한다. 
			if (turnCnt == 0) {
				// 모서리를 만나면 회전하는 수가 없을 경우 turnCnt 갱신해주기 -> 초기 이후로는 2
				turnCnt = 2;			
				// turn Cnt가 종료되면, distance는 1만큼 감소
				distance --;	
			}
			
			// distance 만큼 한칸씩 이동
			// 방향이 변하지 않는 거리 까지 // 이동거리만 카운트
			for(int idx = 0; idx < distance; idx++) {
				// 한칸씩 이동
				int nx = x + dx[direction]; 
				int ny = y + dy[direction]; 
				
				// 현재 위치의 번호 값을 이차원 배열에 기록
				snailList[nx][ny] = snailList[x][y] + 1;
				// 현재 위치를 갱신
				x = nx;
				y = ny;
			}
			
			turnCnt--; // 회전
			direction = (direction+1) %4 ; // 큰 회전을 하였으므로 방향 변환
		}
		
	
		return snailList;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력 객체 생성
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder(); 
		
		// test case 수 입력
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1 ; test_case <= T; test_case ++ ) {
			int N = Integer.parseInt(br.readLine().trim()); // N 입력
			// snail(N)은 snail 메서드에서 return 된 2차원 배열
			int [][] snailList = snail(N);
			// 출력
			sb.append("#").append(test_case).append("\n");
			for(int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					sb.append(snailList[row][col]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		
		// 최종 출력
		System.out.println(sb);
		
	}

}