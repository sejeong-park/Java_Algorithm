
import java.util.Scanner;

/*
 * BOJ 2563 색종이
 * 
 * [문제 이해]
 * - 가로, 세로의 크기 각 100 정사각현
 * - 색종이가 붙은 검은 영역의 넓이 구하기
 *
 * */

public class Main {
	
	static int [][] whitePaper;
	static int result; // 결과 값
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		// 배열
		whitePaper = new int[101][101]; // 흰색 도화지 
		result = 0; // 초기화 
		
		int T = sc.nextInt(); //  test case 입력
		
		for (int test_case = 0 ; test_case < T ; test_case ++) {
			// 색종이의 시작값 
			int startY = sc.nextInt(); // 좌우 - 열
			int startX = sc.nextInt(); // 상하 - 행
			

			for (int x = startX; x<startX+10; x++) {
				for (int y = startY; y <startY+10 ; y++) {
					if (whitePaper[x][y] == 0) {
						whitePaper[x][y] += 1; // 중복 영역 확인
						result++;
					}
				}
			}
		
		}
		
		System.out.println(result);
		
	}

}
