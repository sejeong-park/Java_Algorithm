
import java.util.Arrays;
import java.util.Scanner;



/*
 * BOJ_16435_스네이크버드
 * 스네이크 버드 -> 뱀과 새
 * 과일 하나를 먹으면 길이가 1만큼 늘어남
 * 과일들0 -> 지상으로부터 일정 높이 두고 떨어져 있음 과일 높이 h
 * 스네이크 버드는 자신의 길이보다 작거나 같은 높이 과일 머긍ㄹ 수 있어
 * L 과일들 최대 길이 -> 먹을 수 있는
 * 
 * */
public class Main {
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//입력
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int L = sc.nextInt();
		
		// 배열 선언
		
		int snakebird = L; // 스네이크 버드의 몸통길이
		int [] fruitList = new int[N]; 
		for (int h =0 ; h < N; h++) {
			fruitList[h] = sc.nextInt();
		}
		Arrays.sort(fruitList);
		
		for (int h = 0; h< N; h++) {
			if (fruitList[h] <= snakebird) {
				snakebird++;
			}
			else {
				break;
			}
		}
		
		System.out.println(snakebird);
	}

}
