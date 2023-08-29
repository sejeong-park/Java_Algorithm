

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @date : 23.08.29
 * @problem : BOJ_1149_RGB거리
 * 
 * RGB거리에 집 N개 -> 거리 선분으로 나타낼 수 있다.
 * 집 - 빨강, 초록, 파랑 중 하나의 색으로 칠하기
 * [조건]
 * 1. 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * 2. N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * 3. i번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * @author sejeong-park
 * */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;

	static int[][] dp; // dp table
	static int[][] RGBCost; // 비용 입력
	
	// 배열을 확인하기 위한 함수 - 디버깅 용
	public static void print(int[][] args) {
		for (int row =0 ; row < args.length; row++) {
			System.out.println(Arrays.toString(args[row]));
		}
	}
	
	// 최소값 구하기
	public static int findMinCost(int rCost, int gCost, int bCost) {
		int tmp = Math.min(rCost, gCost);
		return Math.min(tmp, bCost);
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력 받기
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int houseCnt = Integer.parseInt(br.readLine().trim());
		
		// RGB 값을 받는다.
		RGBCost = new int[3][houseCnt];
		for (int house = 0; house < houseCnt; house++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int color = 0; color < 3; color++) {
				RGBCost[color][house] = Integer.parseInt(st.nextToken());
			}
		} // end for house Cost
		
		// 현재 RGB의 Cost + 이전의 최소값 
		for(int house = 1; house < houseCnt; house++) {
			// Red일 경우
			RGBCost[0][house] = RGBCost[0][house] + Math.min(RGBCost[1][house-1], RGBCost[2][house-1]);
			// Green
			RGBCost[1][house] = RGBCost[1][house] + Math.min(RGBCost[0][house-1], RGBCost[2][house-1]);
			// Blue인 경우
			RGBCost[2][house] = RGBCost[2][house] + Math.min(RGBCost[0][house-1], RGBCost[1][house-1]);
		}
		//print(RGBCost);
		
		// 최소값 구하기
		System.out.println(findMinCost(RGBCost[0][houseCnt-1], RGBCost[1][houseCnt-1], RGBCost[2][houseCnt-1]));
		
		
	}

}
