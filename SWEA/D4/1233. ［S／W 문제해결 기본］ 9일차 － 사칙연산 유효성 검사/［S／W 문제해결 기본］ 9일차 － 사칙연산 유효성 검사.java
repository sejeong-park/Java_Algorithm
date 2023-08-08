
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA_사칙연산 유효성 검사_박세정
 * 
 * 문제 해석
 * - 이진트리를 이용해 유효성 검사 -> 계산 가능 1 / 계산 불가능 0
 * - 중간 노드에는 무조건 연산자가 있도록 해야한다.
 * - 입력 : n개  해당 정점의 알파벳, 해당 정점의 왼쪽,오른쪽 자식의 정점번호  
 * 
 * 문제 풀이
 * - 단순 이진 트리 
 * 
 * - SWEA break 쓰면 안돼
 * */
public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 입력 받는 문자열이 연산자인지 확인
	public static boolean checkCounter(String node) {
		if (node.equals("+") || node.equals("-") || node.equals("*") || node.equals("/")) {
			return true;
		}else {
			return false;
		}
	}
	
	// 정수 여부 판단
	public static boolean isInteger(String node) {
		try {
			Integer.parseInt(node);
			return true;
		}catch (NumberFormatException e){
			// 만약 Integer 변환에 예외가 발생한다면
			return false;
			
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력 먼저 선언
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// testcase는 10번
		for (int test_case = 1; test_case <= 10; test_case ++) {
			// 입력
			int N = Integer.parseInt(br.readLine().trim());
			
			int answer = 1; // 유효한 노드
			
			// N번 반복하고 각 노드에 집어넣기
			for (int node=1; node<=N; node++) {
				// 입력 시작
				st = new StringTokenizer(br.readLine().trim());
				st.nextToken();
				
				// String Tokeninzer split 개수 세기
				int splitCnt = st.countTokens();

				// 가운데 부모 노드 일경우 (연산자가 들어가야한다.)
				if (splitCnt == 3) {
					// split tokenizer의 값이 무엇인지 확인해야한다.
					if (!checkCounter(st.nextToken())) {
						answer = 0; // 유효하지 않은 조건
						//break; // for 문 밖으로 탈출
					}
				}
				// 만약 자식노드인 조건이라면? -> 연산자면 안된다. -> Int형이여야 한다.
				if (splitCnt == 1) {
					// int 형인지 확인하고, 아니라면 유효하지 않음
					if (!isInteger(st.nextToken())) {
						answer = 0;
						// break;
					}
				}
				
			}// end for node check
			//System.out.println("#" + test_case + " : "  + answer);
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");

		} // end for test_case
		System.out.println(sb);
		
		
	
		

	}

}
