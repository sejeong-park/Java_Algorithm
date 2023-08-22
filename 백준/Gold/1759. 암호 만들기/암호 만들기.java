
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ_1759_암호 만들기
 * 
 * [문제]
 * - 서로 다른 L개의 알파벳 소문자들로 구성 
 * - 최소 한개의 모음(a,e,i,o,u)
 * - 최소 두개의 자음으로 구성
 * - 조교 : 정렬된 문자열 성향
 * - 가능성이 있는 암호들을 모두 구한다.
 * 
 * [문제 풀이]
 * - 조합을 기준으로 문제를 푼다. 
 * */


public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int wordSize;
	static int wordCnt;
	
	static char[] wordList;
	static char[] selectList; // 선택된 리스트
	
	static int cnt;
	
	// 자음과 모음 확인하는 리스트
	public static boolean checkCondition(char [] checkList) {
		int 자음 = 0;
		int 모음 = 0;
		
		for(int idx = 0; idx < checkList.length ; idx++) {
			// 모음일 경우
			if (checkList[idx] == 'a' || checkList[idx] == 'e' || checkList[idx] == 'i' || checkList[idx] == 'o' || checkList[idx] =='u') {
				모음 ++;
			}else { // 자음일 경우
				자음 ++;
			}
		}
		
		if (모음 >= 1 && 자음 >= 2) {
			return true;
		}else {
			return false;
		}
		
	}

	
	public static void combination(int selectIdx, int elementIdx) {
		
		// 기저 조건		
		// 만약 선택한 수가 다 찼다면
		if (selectIdx == selectList.length) {
			// 만약 자음과 모음을 확인해서 true이라면
			if (checkCondition(selectList)) {
				sb.append(selectList).append("\n");
			}		
			
			return;
		}
		
		if (elementIdx == wordList.length) {
			return;
		}
		
		// 유지 조건
		// 선택했을 경우
		selectList[selectIdx] = wordList[elementIdx];
		combination(selectIdx+1, elementIdx+1);
		
		// 선택하지 않았을 경우
		combination(selectIdx, elementIdx+1);	
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력 선언
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 첫째줄 L, C
		st = new StringTokenizer(br.readLine().trim());
		wordSize = Integer.parseInt(st.nextToken()); 	//  출력한 word의 리스트
		wordCnt = Integer.parseInt(st.nextToken());  //  입력할 문자
		
		// wordList		
		wordList = new char[wordCnt]; // char로 된 list
		selectList = new char[wordSize]; // 출력할 리스트
		st = new StringTokenizer(br.readLine().trim());
		for (int cnt = 0; cnt < wordCnt; cnt++) {
			char word = st.nextToken().charAt(0); // String to char
			wordList[cnt] = word;
		}
		// wordList의 순서 유지
		Arrays.sort(wordList);
		combination(0,0); // 조합

		System.out.println(sb);
	}

}
