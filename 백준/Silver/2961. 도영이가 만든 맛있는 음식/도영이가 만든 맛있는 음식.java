import java.util.Scanner;

public class Main {

	static int[][] readyFood; // 입력 받을 재료
	static boolean[] selected;
	static int N;
	static int minValue = Integer.MAX_VALUE; // 신맛과 쓴맛의 차이가 가장 작을 경우
	static int totalS, totalB;
	static int taste; // 최종 맛

	public static void SubSet(int cnt) {

		// 종료되었을 때
		if (cnt == N) {
            int tCnt = 0;
			// 만약 선택한 재료일 경우
			totalS = 1; // 신맛 (곱하기이므로 초기화를 1로한다.)
			totalB = 0; // 쓴맛
			for (int foodIdx = 0; foodIdx < N; foodIdx++) {
				if (selected[foodIdx]) {
					// 선택된 재료라면
					totalS *= readyFood[foodIdx][0]; // 신맛은 곱한다.
					totalB += readyFood[foodIdx][1]; // 쓴맛은 더한다.
                    tCnt ++;
				}
			}

			// 신맛과 쓴맛의 차이가 덜나는 것 찾기
			// System.out.print("신맛 : " + totalS + " 쓴맛 : " + totalB + "결과 : " +
			// Math.abs(totalS - totalB));

			// 제일 점수차이가 안나는 것 찾기
            
            // taste가 곱하기여서 초기화를 1로 해주었으므로
			// 만약 totalS가 1인것은 0
            
            if (tCnt > 0){
			    if (totalS == 1) {
				    totalS = 0;
			    }
            
			    taste = Math.abs(totalS - totalB);
			    if (taste < minValue) {
				    minValue = taste;
			    }
            }
			return;
		}

		// 선택했을 때
		selected[cnt] = true;
		SubSet(cnt + 1);
		selected[cnt] = false;
		SubSet(cnt + 1);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 입력이 적으므로 Scanner 사용
		Scanner sc = new Scanner(System.in);

		// 재료의 수 입력
		N = sc.nextInt();
		// 재료 입력
		readyFood = new int[N][2]; // S,B
		selected = new boolean[N]; // index 기준
		for (int food = 0; food < N; food++) {
			readyFood[food][0] = sc.nextInt(); // 신맛
			readyFood[food][1] = sc.nextInt(); // 쓴맛
		}
		// 준비된 재료의 부분집합
		SubSet(0);

		// 결과 출력
		System.out.println(minValue);

	}

}
