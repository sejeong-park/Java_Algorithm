

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * SWEA_6808 규영이와 인영이의 카드게임 
 * 
 * [문제 이해하기]
 * - 1 ~ 18 카드 게임 (둘은 카드를 섞어 9장씩 카드를 나눈다.)
 * - 아홉 라운드에 걸쳐 게임을 진행한다.
 * - 한 라운드에는 한 장씩 카드를 낸 다음 두 사람이 낸 카드에 적힌 수를 비교해서 점수 계산
 * <점수 구하는 공식>
 * - 높은 수 : 두 카드에 적힌 수의 합
 * - 낮은 수 : 아무런 점수 없음
 * -> 총점이 더 높은 사람 승자 (두 사람의 승점 같으면 무승부)
 * 
 * 규영이가 내는 카드의 순서 고정할건데, 인영이가 카드를 내는 순서에 딸 승패 결정
 * -> 규영이가 이기는 경우와 지는 경우 몇가지인지?
 * 
 * 
 *
 * */

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	
	static final int CARD_SIZE = 9;
	
	static boolean[] initCardSet;
	static int[] kuyoungCardSet;
	static int[] inyoungCardSet;
	
	// visited 확인
	static boolean[] isSelected;
	static int[] cardSet;
	
	// 규영이가 이기는 경우 & 지는 경우
	static int WinCase;
	static int LoseCase;
	
	// 인영이의 카드와 규영이의 카드를 비교 ! 게임 진행
	public static void playGame(int[] kuyoungCardSet, int[] inyoungCardSet) {
		// 카드의 값 자체가 같을 수가 없다 ! -> 카드는 서로 겹치지 않기 때문
		
		
		// 두 사람의 카드 셋 총점
		int kuyoungTotal = 0;
		int inyoungTotal = 0;
		
		// 두사람의 카드를 비교한다.
		for (int index = 0; index < CARD_SIZE; index++) {
			// 만약 규영이가 이겼을 때
			if (kuyoungCardSet[index] > inyoungCardSet[index]) {
				kuyoungTotal += kuyoungCardSet[index] + inyoungCardSet[index];
			}else {
				// 인영이가 이겼을 때
				inyoungTotal += kuyoungCardSet[index] + inyoungCardSet[index];
			}
		}
		
		
		// 승패 결정
		// 승점 값이 같으면 무승부 
		if (kuyoungTotal > inyoungTotal) {
			WinCase ++;
		} else if (kuyoungTotal < inyoungTotal){
			LoseCase ++;
		}
		
		
	}
	
	
	// 순열을 구한다. -> 인영이의 카드셋에 대해 모든 경우의 수
	public static void permutation(int depth) {
		if (depth == CARD_SIZE) {
			// 게임 진행
			playGame(kuyoungCardSet, cardSet);
			return;
		}
		
		for (int idx = 0; idx < CARD_SIZE; idx++) {
			// 방문하지 않았다면
			if (!isSelected[idx]) {
				isSelected[idx] = true;			
				cardSet[depth] = inyoungCardSet[idx];	// inyoung depth로 들어가기
				permutation(depth + 1);			// dfs 재귀적으로 호출
				isSelected[idx] = false;
			}
		}
	}
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// test case 입력 받기
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T; test_case++) {
			
			// 규영이의 카드 
			st = new StringTokenizer(br.readLine().trim());
			// card에 대한 값 초기화 
			initCardSet = new boolean[(CARD_SIZE*2) + 1]; // 초기 값
			kuyoungCardSet = new int[CARD_SIZE];
			inyoungCardSet = new int[CARD_SIZE];
			
			
			for (int idx = 0; idx < CARD_SIZE ; idx ++ ) {
				// 규영이 카드 입력 받기
				int cardNum = Integer.parseInt(st.nextToken());
				kuyoungCardSet[idx] = cardNum; // 규영이의 카드셋에 넣는다.
				initCardSet[cardNum] = true;			
			}
			
			
			// 인영이 카드 셋을 만든다.
			int index = 0;
			for (int cardNum = 1; cardNum < (CARD_SIZE*2)+1 ; cardNum++) {
				if (!initCardSet[cardNum]) inyoungCardSet[index++] = cardNum;
			}
			
			
			// 결과에 필요한 값 초기화
			WinCase = 0;
			LoseCase = 0;
			
			// 인영이가 카드를 어떻게 내느냐에 다라 결과가 달라짐 
			// 순열 
			cardSet = new int[CARD_SIZE];
			isSelected = new boolean[CARD_SIZE];
			permutation(0);

			
			sb.append("#").append(test_case).append(" ").append(WinCase).append(" ").append(LoseCase).append("\n");
	
		} // end for testcase
		
		System.out.println(sb);
	
	} // main

}
