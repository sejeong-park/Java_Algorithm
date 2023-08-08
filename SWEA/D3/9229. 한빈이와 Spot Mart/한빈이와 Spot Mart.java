

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * SWEA_9229_한빈이와 Spot Mart
 * 
 * 문제 해석
 * - 스팟 마트에 N개의 과자 봉지 존재 -> 각 과자봉지는 a그램의 무게
 * - 배가 많이 고픈 한빈이 최대한 양이 많은 과자 봉지 고르고 싶으나
 * - 과자 두봉지 무게 M 그램 초과 시 과자 들 수 업어.
 * - 한빈이가 들고다닐 수 있는 과자들의 최대 무게 합 -> 과자 정확히 2봉지
 * 
 * 문제 풀이
 * - 범위 내에서 
 * */

public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String args[]) throws IOException{
		
		// 입출력 
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		// 첫번재 테스트 케이스
		
		for (int test_case = 1; test_case <=T; test_case ++ ) {
			// N, M
			st = new StringTokenizer(br.readLine().trim());
			// 입력 받기
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 3개의 과자 봉지 무게 누적합 
			Integer []snackBox = new Integer[N]; // Arrays.sort에서Collections.~ 를 사용하려면 genric 형태를 사용해야 함
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx <N ; idx ++) {
				snackBox[idx] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(snackBox, (i1, i2) -> i2 - i1); // lambda를 이용하여 Compareㅇ
			
			// System.out.println(Arrays.toString(snackBox));
			
			int item1 = 0;
			// start index가 N범위 내에 있을 경우까지만 허용
			int maxSum = Integer.MIN_VALUE; //  maxValue
			
			while(item1 < N) {
				// 만약 item1이 M보다 무게가 더 나가지 않는다면
				if(snackBox[item1] < M) {
					// 뒤에서부터 start까지 for 문으로 찾기
					for (int item2 = N-1; item2 > item1; item2--) {
						// item2 가 어디있는지 찾자
						int itemSum = snackBox[item1] + snackBox[item2];
						if (itemSum <=M) {
							// maxSum 갱신
							if (maxSum < itemSum) {
								maxSum = itemSum;
							}
						}
						
					}
				}
				// 모두 끝나면
				item1++; // item1 증가
			}
			// 
			sb.append("#").append(test_case).append(" ");
			if (maxSum<0) {
				sb.append("-1").append("\n");
			}else {
				sb.append(maxSum).append("\n");
			}
		
		}// end for test case
		System.out.println(sb);
		
	}

}
