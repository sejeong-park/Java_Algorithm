import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * BOJ_15686 치킨배달 
 * 
 * [문제 이해]
 * 빈칸 0 , 치킨집2, 집1 중 하나
 * 치킨 거리
 * 집과 치킨 집 거리
 * 폐업 시키지 않을 치킨집 최대 M개 -> 도시의 치킨 거리의 최솟값
 * 
 * 
 * -> 조합 필요 ->> 조합으로 최대 치킨 집 combination 돌리고
 * 도시의 치킨 거리 최솟값 구하기 
 * */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] map; // 도시의  거리 
	
	
	// ArrayList로 선언한 이유 : 좌표 값들을 입력 할 때부터 바로 받고 싶은데, 
	// Size를 알지 못해 초기화를 하고 다시 2중 포문으로 돌아야 하기때문에 ArrayList로 선언함
	static ArrayList<mapPoint> chickenHouse; //  치킨집 구하기
	static ArrayList<mapPoint> myHouse; 	// 집 구하기
	
	
	// 반면 "폐업하지 않을 치킨집"은 M으로 최소값 지정을 해주었기 때문에 고정 배열로 선언함
	static mapPoint[] combiChickenHouse; // 폐업시키지 않을 치킨집 구하기
	
	// 최소 값 및 result 구하기
	static int RESULT;
	

	
	// map의 위치를 구하기 위함
	static class mapPoint {
		int x;
		int y;
		mapPoint(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	

	
	// 폐업되지 않은 치킨집의 거리
	public static void chickenDistance(mapPoint[] chickenHouseList) {
		
		/* 도시의 치킨 거리 !
		 * - 도시의 치킨 거리는 모든 집의 치킨거리의 합이다 !!!
		 *  치킨의 거리 구하고 최소 치킨의 거리를 찾아야 한다.
		 * */
		int cityDistance = 0; // 모든 집의 치킨 거리의 합
		
		// 치킨 거리 = 집과 가장 가까운 치킨집 사이의 거리
		// 치킨집이 기준이 아니라 집이 기준 ㅋㅋ ㅜ -> 문제 잘못이해
		for (mapPoint _house: myHouse) {
			
			int chickenDistance = Integer.MAX_VALUE; // 집 - 치킨집 사이의 가장 가까운 거리 
			
			// 한 치킨집이 집들을 탐색
			for(mapPoint _chicken_house : chickenHouseList) {
				// 치킨 거리 구하여, 더해주기
				 int distance = Math.abs(_chicken_house.x - _house.x) + Math.abs(_chicken_house.y - _house.y);
				 chickenDistance = Integer.min(distance, chickenDistance); // 두 값중 더 작은 값이 치킨 거리
			}
			
			cityDistance += chickenDistance; // 도시의 치킨집 거리 더해주기
		}
		// 도시의 치킨 거리
		
		// 도시의 치킨 거리를 최종 RESULT와 다시 비교
		RESULT = Integer.min(cityDistance, RESULT);
		

	}
	
	
	// 치킨지의 조합 구하기
	public static void combination(int elementIdx, int selectedIdx) {
		// 기저 조건
		
		// 전체 선택할 조건에 포함한다면?
		if (selectedIdx == combiChickenHouse.length) {
			// 결과
			chickenDistance(combiChickenHouse);
			return;
		}
		
		// 만약 조합해야할 치킨집의 길이 전체에 도달한다면?
		if (elementIdx == chickenHouse.size()) {
			return;
		}
		
		// 만약 결과에 넣는다면?
		combiChickenHouse[selectedIdx] = chickenHouse.get(elementIdx);
		combination(elementIdx+1, selectedIdx +1);
		
		// 안넣는다면 ->선택 안함
		//combiChickenHouse[selectedIdx] = 0; // 초기화
		combination(elementIdx+1, selectedIdx);
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		// 입출력 먼저 선언하기
		br = new BufferedReader(new InputStreamReader(System.in));
		RESULT = Integer.MAX_VALUE; // 결과 값 초기화
		
		// N과 M 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// N * N 입력 받기 & 초기화
		map = new int [N][N]; // 입력 받을 도시 맵 
		chickenHouse = new ArrayList<>(); // 객체 생성
		myHouse = new ArrayList<>();
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			// col 입력 받기
			for (int col = 0; col < N ; col++) {
				// 한개 씩 입력 받기
				map[row][col] = Integer.parseInt(st.nextToken()); // 다음 토큰 입력 받기
				// 치킨집의 combination을 구하기 위해 리스트에 넣기
				if (map[row][col] == 2) {
					chickenHouse.add(new mapPoint(row, col)); // enclose 에러 나면 원인 static 문제이다.
				}
				// 집이라면 집 입력 받기
				if (map[row][col] == 1) {
					myHouse.add(new mapPoint(row, col)); 
				}
			}
		} // end for map 
		
		// 치킨집 리스트 찾기
		combiChickenHouse = new mapPoint[M]; //	 M 크기 만큼의 조합 돌리기
		combination(0,0);
		
		// 출력
		System.out.println(RESULT);	
		
	} // end for main func
	
}
