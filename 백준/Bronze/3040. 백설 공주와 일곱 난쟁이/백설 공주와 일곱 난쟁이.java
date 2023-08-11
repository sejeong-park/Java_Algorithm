

import java.util.Arrays;
import java.util.Scanner;

/*
 * SWEA_3040_백설공주와 일곱난쟁이
 * 
 * 백설공주 의자 7 / 접시 7 / 나이프 7
 * 아홉난쟁이가 자기가 7명 안에 든다고 모두 주장 
 * 난쟁이 모자 안에 100보다 작은 양의정수  -> 합이 100이 되도록 적어놓았다.
 * 일곱
 * 
 * */

public class Main {

	static int[] elementList; // 난쟁이 리스트
 	static int[] selectedList; // 선택 여부 리스트
	static int[] resultList;
 	
 	public static void find난쟁이() {
 		int TotalSum = 0;

 		for (int idx = 0; idx < selectedList.length ; idx++) {
 			TotalSum += selectedList[idx];
 		}
 		
 		if (TotalSum == 100) {
 			int idx = 0;
 			for (int item : selectedList) {
 				resultList[idx++] = item;
 			}
 		}
 		
 	}
 	
 	
	// 조합 7개 
	public static void combination(int elementIdx, int selectedIdx) {
		
		// 기저 조건 2개 
		if (selectedIdx == selectedList.length) {
			find난쟁이();
			return;
		}
		// 종료 조건
		if (elementIdx == elementList.length) {
			return;
		}
		
		// 현재 것을 선택 한다.
		selectedList[selectedIdx] = elementList[elementIdx];
		combination(elementIdx+1, selectedIdx+1);
		
		// 선택하지 않는다.
		selectedList[selectedIdx] = 0;
		combination(elementIdx+1, selectedIdx);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 초기화
		elementList = new int[9]; 
		selectedList = new int[7];
		resultList = new int[7];
		
		
		Scanner sc = new Scanner(System.in);
		// 난쟁이의 입력
		for (int index = 0; index < 9; index++) {
			elementList[index] = sc.nextInt(); // 입력 받기 
		}
		
		combination(0,0);
		
		for (int item : resultList) {
			System.out.println(item);
		}

		
		
	}
	
	
	
	
	
	

}
