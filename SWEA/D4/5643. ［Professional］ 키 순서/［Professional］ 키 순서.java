

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_5643_키순서
 * - 키 비교 
 * - 자신의 키가 몇번째인지 알 수 있는 학생이 모두 몇명인지??
 * - 자신의 키가 몇 번째인지 알 수 있다 = 모든 학생과의 비교가 가능하다.
 * 
 * */
public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int studentCnt, compareCnt;
	static final int INF = 999999;
	static int [][] distance; // 거리 리스트
	
	public static void init() {
		for (int rowIdx = 0; rowIdx < studentCnt+1 ; rowIdx++) {
			for (int colIdx = 0; colIdx < studentCnt + 1; colIdx++) {
				distance[rowIdx][colIdx] = INF;
			}
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// testCase
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			// n,m 입력
			studentCnt = Integer.parseInt(br.readLine().trim()); // 학생들의 수 입력
			compareCnt = Integer.parseInt(br.readLine().trim()); // 두 학생의 키를 비교한 횟수
			
			distance = new int[studentCnt+1][studentCnt+1];
			init(); //distance 초기화
			// a와 b비교
			for (int idx = 0; idx < compareCnt; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				distance[a][b] = 1;
			}
			
			// 플로이드 워셜
			for (int middle = 1; middle < studentCnt + 1; middle++) {
				for (int start = 1; start < studentCnt + 1; start++) {
					for (int end = 1; end < studentCnt + 1; end++) {
						if (distance[start][end] > distance[start][middle] + distance[middle][end]) {
							distance[start][end] = distance[start][middle] + distance[middle][end];
						}
					}
				}
			}
			
			int answer = 0;
			// 끝까지 탐색하는 경우 확인
			for (int rowIdx = 1; rowIdx < studentCnt + 1; rowIdx++) {
				int count = 0;
				for (int colIdx = 1; colIdx < studentCnt + 1; colIdx++) {
					if (distance[rowIdx][colIdx] != INF || distance[colIdx][rowIdx] != INF) {
						count++;
					}
				}
				
				// 카운트
				if (count == studentCnt-1) {
					answer+=1;
				}
				
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			
			
		} // end for testCase
		System.out.println(sb);
		
	}

}
