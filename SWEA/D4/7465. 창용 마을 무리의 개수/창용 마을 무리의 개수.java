

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * SWEA_7465_창용 마을 무리의 개수
 * 1. 두 사람은 서로를 알고있는 관계일 수도 있고, 아닐 수도 있다.
 * 2. 서로 이웃 관계 모두 거친다면
 * 3. 몇개의 무리 
 * */

public class Solution {
	
	
	// 입출력 선언
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int V, E;
	static int[] parents;
	
	// make
	private static void make() {
			
		parents = new int[V+1];
		// 부모를 자기자신으로 만들어서 우선 초기화
		for (int idx = 0; idx < V+1; idx++) {
			parents[idx] = idx;
		}
	}
	
	
	// find
	private static int find(int element) {
		
		// 부모가 자기 자신이라면, element 리턴
		if (parents[element] == element) return element;
		
		// 부모가 따로 있으면, 부모에 자기 자신 다시 넣기
		return parents[element] = find(parents[element]);
	}
	
	
	// union
	private static void union(int a, int b) {
		// 루트 구하기
		int parentA = find(a);
		int parentB = find(b);
		
		// 더 작은 것이 부모 그래프 하위에 들어간다.
		if(parentA < parentB){
			parents[parentB] = parentA;
		}else {
			parents[parentA] = parentB;
		}
	
	}
	

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= testCase; tc++) {
			// 케이스 입력 
			st = new StringTokenizer(br.readLine().trim());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			make(); // 초기화
			for (int idx = 0; idx < E; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// union
				union(a,b);
			}
			
			// find
			for (int idx = 1; idx < V+1; idx++) {
				find(idx);
			}
		
			// parent 개수 세기

			Set<Integer> parentList = new HashSet<Integer>();
			for (int idx = 1; idx < V+1; idx++) {

				parentList.add(parents[idx]);
			}

			sb.append("#").append(tc).append(" ").append(parentList.size()).append("\n");
					
		} // end for testCase
		
		System.out.println(sb);
		
		
		
		
		

	}

}
