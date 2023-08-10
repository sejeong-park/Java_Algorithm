
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * SWEA_5215 햄버거다이어트 - 조합
 * 민기가 이용하는 햄버거 -> 고객이 원하는 조합
 * 
 * 조합은 "순서"를 가지지 않음 -> {1,2,3}, {3,2,1}을 같다고 간주
 * 중복 조합은 순서를 가지며, 중복을 허용
 * 
 * */
/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
*/
public class Solution {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	// 음식에 대한 정보 저장할 리스트
	static ArrayList<FoodInfo> foodList;
	static int [] foodIndexList; // food를 소환하기 위한 index를 조합으로 검사한다.
	static int N, L;
	static int result; // 최대 맛의 값 출력

	// 음식에 대한 정보
	static class FoodInfo{
		// 점수와 칼로리
		int tastePoint;
		int calories;
		// 생성자 
		public FoodInfo(int item1, int item2) {
			// TODO Auto-generated constructor stub
			this.tastePoint = item1;
			this.calories = item2;
		}
	}

	// 주어진 제한 칼로리 이하의 조합 중에서 가장 맛에 대한 점수가 높은 햄버거의 점수!
	public static int getTasteScore(int[] foodIndexList) {
		
		int totalPoint = 0;
		int totalCalories = 0;
		// 이 조합의 FoodInfo의 정보로 알아낸다.
		for (int index : foodIndexList) {
			totalCalories += foodList.get(index).calories;
			totalPoint += foodList.get(index).tastePoint;
		}
		
		// 만약 칼로리가 최대 카로리를 넘어간다면?
		if (totalCalories > L) {
			return -1; // -1 리턴
		}
		return totalPoint; // 전체 포인트 넘기기
	}
	
	// 반환한 점수를 바탕으로 최대값인지 확인한다.
	public static void compareWithMaxTaste(int tastePoint) {
		
		if (tastePoint == -1) return;
		
		// 결과보다 현재가 맛의 점수가 더 높다면
		if (result < tastePoint) {
			result = tastePoint; // 현재의 맛의 점수 넣기
		}
		return;
	}
	
	
	
	// 자바 조합 !! -> Limit는 조합을 어떤 크기 만큼인지 
	public static void combination(int depth, int start, int Limit) {
		// 고정 n이 아닐 경우????? -> 그냥 다시 선언해주네...ㅜ 다시 결과값을 할당해야되네 ... 없다보다,,.
		// 기저 조건
		if (depth == Limit) {
			compareWithMaxTaste(getTasteScore(foodIndexList));
			//System.out.println(Arrays.toString(foodIndexList) + " " +getTasteScore(foodIndexList));
			return;
		}
		
		// 조합의 개수
		for (int idx = start; idx < N; idx++) {
			foodIndexList[depth] = idx; // idx를 기준으로 foodIndexList를 조합으로 찾기
			combination(depth+1, idx+1, Limit);
		}
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		// test case 입력
		int T = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= T ; test_case++) {
			// 재료 수와 제한 칼로리 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken()); 	// 재료 수
			L = Integer.parseInt(st.nextToken());  	// 제한 칼로리 수
			
			// foodList 초기화
			foodList = new ArrayList<FoodInfo>(); // Type -> FoodInfo
			
			for (int index = 0; index < N; index++) {
				//제한 재료 수
				st = new StringTokenizer(br.readLine().trim());
				int item1 = Integer.parseInt(st.nextToken());
				int item2 = Integer.parseInt(st.nextToken());
				foodList.add(new FoodInfo(item1, item2));
		
			}
			
			// 결과 값 초기화
			result = Integer.MIN_VALUE; // 최소값 
			// 조합
			for (int cnt = 1; cnt <= N; cnt++) {
				foodIndexList = new int[cnt]; // cnt의 크기 만큼의 리스트를 구한다.
				combination(0,0, cnt);
			}
		
			sb.append("#").append(test_case).append(" ").append(result).append("\n"); 
		} // test case 종료
		
		System.out.println(sb);
		

	}

}
