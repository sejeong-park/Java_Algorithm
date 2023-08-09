// 소요시간 : 1시간 21분 정도.. -> 어떡하냐 나..

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 16935 배열돌리기 3
 * 
 * [문제 풀이]
 * - 1번 연산 : 상하반전
 * - 2번 연산 : 좌우반전
 * - 3번 연산 : 오른쪽으로 90도 회전
 * - 4번 연산 : 왼쪽으로 90도 회전
 * 
 * 그룹을 4등분으로 나누어서 
 * - 5번 연산 : 부분 집합 시계 방향 회전 - 1->2 / 2->3 / 3->4 / 4->1
 * - 6번 연산 : 부분 집합 반시계 방향 회전 - 4->3 / 3->2 / 2->1  
 * */

public class Main {
	
	// tokenizer 선언
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 배열 
	static int [][] array;
	static int N, M;
	// 리턴해줄 결과 값 생성
	static int [][] result; // 최종 출력
	
	
	// 연산 1 : 상하반전 
	public static void flipUpDown() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				// row값만 반대로 바뀌고, col값은 그대로 유지
				result[row][col] = array[N-row-1][col];
			}
		}
	}
	
	// 연산 2 : 좌우반전
	public static void flipLeftRight() {
		for (int row = 0; row < N; row ++) {
			for (int col = 0; col <M ; col++) {
				result[row][col] = array[row][M-col-1];
			}
		}
	}
	
	// 연산 3 : 오른쪽으로 90도 회전
	public static void rotateRight() {
		// column과 row를 서로 변환함 
		for (int row = 0; row < M; row ++) {
			for (int col = 0; col < N; col++) {
				result[row][col] = array[N-col-1][row]; 
			}
		}
	}
	
	// 연산 4 : 왼쪽으로 90도 회전
	public static void rotateLeft() {
		for (int row = 0; row < M; row ++) {
			for (int col = 0; col <N; col ++) {
				result[row][col] = array[col][M-row-1];
			}
		}
	}
	
	// 연산 5 : 부분 집합 시계 방향 회전
	public static void seqRotateRight() {
		
		// half 기준으로 자르기
		final int nHALF = N/2;
		final int mHALF = M/2;
		
		// 1 -> 2
		for (int x = 0; x < nHALF; x++) {
			for (int y = 0; y < mHALF ; y++) {
				result[x][y + mHALF] = array[x][y];
			}
		}
		// 2-> 3
		for (int x = 0 ; x < nHALF; x++) {
			for (int y = mHALF ; y < M; y++) {
				result[x+nHALF][y] = array[x][y];
			}
		}
		// 3-> 4
		for (int x = nHALF; x < N; x++) {
			for (int y = mHALF; y <M; y++) {
				result[x][y-mHALF] = array[x][y];
			}
		}
		// 4-> 1
		for (int x = nHALF; x< N; x++) {
			for (int y = 0; y < mHALF; y++) {
				result[x-nHALF][y] = array[x][y];
			}
		}
	
	}
	
	// 연산 6 : 부분 집합 반시계 방향 회전
	public static void seqRotateLeft() {
		
		final int nHALF = N/2;
		final int mHALF = M/2;
		
		//  1-> 4
		for (int x = 0; x < nHALF; x++) {
			for (int y = 0; y < mHALF; y++) {
				result[x+nHALF][y] = array[x][y];
			}
		}
		// 4 -> 3
		for (int x = nHALF; x < N; x++) {
			for (int y =0; y<mHALF; y++) {
				result[x][y+mHALF] = array[x][y];
			}
		}
		// 3 -> 2
		for (int x = nHALF; x < N; x++) {
			for (int y = mHALF; y < M ; y++) {
				result[x- nHALF][y] = array[x][y];
			}
		}
		// 1 -> 4
		for (int x = 0 ; x < nHALF; x++) {
			for (int y = mHALF; y < M; y++) {
				result[x][y - mHALF] = array[x][y];
			}
		}
		
		
	}
	

	// 연산 번호 호출하는 함수
	public static void callNumber(int number) {
		result = new int[N][M];
		if (number == 1) {
			flipUpDown(); // 상하 반전
		}else if (number == 2) {
			flipLeftRight(); // 좌우 반전
		}else if (number == 3) {
			result = new int[M][N];
			rotateRight(); // 오른쪽 90도 회전
		}else if (number == 4) {
			result = new int[M][N];
			rotateLeft();
		}else if (number == 5) {
			seqRotateRight();
		}else if (number == 6) {
			seqRotateLeft();
		}
		
		
		// original 이였던 array result 값으로 갱신해주고
		// 초기 설정 값이였던 N, M은 변경된 array 기준으로 갱신해준다. -> N,M이 90도 회전하면서 바뀐경우 존재
		array = result;
		N = array.length;
		M = array[0].length;

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// 입출력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫번째 줄 입력 받기 (필요 조건)
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken()); // 연산 입력 변호
		
		// 배열 선언 및 입력
		array = new int[N][M]; // 이차원 배열 초기화
		for (int x = 0; x <N; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int y =0; y < M; y++) {
				array[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// R만큼의 연산을 일렬로 받는다.
		st = new StringTokenizer(br.readLine().trim()); // 한줄로 몇번인지 입력 받는다
		// 연산의 수 
		for (int test_case = 0; test_case < R; test_case++) {
			
			// 연산 번호를 입력한다.
			int CountNum = Integer.parseInt(st.nextToken()); 
	
			// 입력되는 연산을 호출하는 함수
			callNumber(CountNum); // R 번호를 입력하면, 배열을 생성해주자
			// array 값도 갱신해야 함 // N, M도 갱신해야돼
		}
		
		
		// 결과 값을 반환하고, StringBuilder에 넣어주기
		// N, M 값이 고정할 수 없음 ! 일괄 적으로 찾을 거기 때문
		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result[row].length ; col++) {
				sb.append(result[row][col]).append(" "); // 한개씩 더한다.
			}
			sb.append("\n"); // 출력에 줄바꿈 적용
		}		
		
		
		// 전체 연산 결과 출력하기
		System.out.println(sb);
		
		
	} // end main

}
