

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
	
	public static boolean isSame(String keyword) {
		// 몇개가 같은 지 확인
		int target = keyword.length() / 2;
		
		// 앞 뒤 같은 지 확인하기
		for (int index = 0; index < target ; index++) {
			if (keyword.charAt(index) != keyword.charAt(keyword.length()-index-1)) {
				return false;
			}
			target--;
		}
		
		return true;
	}
	
	
	public static void main(String [] args) throws IOException{
		
		// 가장 첫 줄에 T 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// T입력 받기
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case ++ ) {
			// 입력 받기
			String keyword = br.readLine().trim();
	
			// 결과값 
			int answer = isSame(keyword) ? 1: 0;
			// 함수에 넣기
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");		
		} // end for test_case
		System.out.println(sb);
		
	}

}
