
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * SWEA_1238_Contact
 * [문제 풀이]
 * - 비상연락망
 * - 화살표가 있는 그래프
 *   - BFS로 풀 것 : "한번 연락을 받은 사람에게 다시 연락을 하는 일이 없다" -> 한 번 방문 표시 되면 탐색 X
 * 1. <입력 받는 데이터의 길이>와 <시작점> 입력 받는다.
 * 2. from to from to ... 하는 과정으로 입력을 받는다.
 * 3. 마지막으로 연락 받은 사람 중 가장 큰 경우를 구한다.
 * 
 *  
 * @author sejeong-park
 * **/


public class Solution {
	
	// 입출력
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int inputSize, startPoint;
	static int [][] graph; // 링크 방향을 나타내는 그래프
	static int [] visited; // 노드 방문 여부
	
	
	public static int bfs(int start) {
		
		int depth = 1; // 현재 탐색한 개수 
		
		// bfs를 진행할 큐 입력
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		visited[start] = depth; // 탐색 시작
		
		
		while (!queue.isEmpty()) {
			int node = queue.poll(); 
			
			// 해당 노드와 연결되어 있는 노드들 bfs 탐색 
			for (int idx = 1 ; idx < 101; idx++) {
				// 연결되어 있고, 방문하지 않았었다면 탐색
				if (graph[node][idx] == 1 && visited[idx] == 0) {
					queue.add(idx); // 다음 노드 탐색
					visited[idx] = visited[node] + 1; // 직전 노드의 깊이와 비교한다.
				}
			}
			
			// 최대 깊이를 갱신한다 !
			depth = Math.max(depth, visited[node]); // 탐색하는 노드의 깊이 갱신 
		}
		
		// 3. 마지막으로 연락 받은 사람 중 가장 큰 경우를 구한다.
		// 3-1. 마지막 연락을 받은 것  = 깊이가 가장 깊은 node
		
		// node의 번호가 큰 것부터 작은 것 순서대로 최대 깊이와 일치한 번호를 찾는다.
		for (int node = 100; node > 0; node--) {
			if (depth == visited[node]) return node;
		}
		
		return -1; // depth 0이 최대일 경우
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		// 입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// testCase
		for (int test_case = 1; test_case <= 10; test_case ++) {
			//1. 입력 받는 데이터의 길이와 시작점
			st = new StringTokenizer(br.readLine().trim());
			inputSize = Integer.parseInt(st.nextToken());
			startPoint = Integer.parseInt(st.nextToken());
			
			// 연락인원은 최대 100명 / 부여 가능한 번호 1이상 100 이하 -> 2차원 배열로 링크된 리스트를 구할 것이다.
			graph = new int[101][101]; // 번호 노드를 기준으로 한다.
			visited = new int[101]; // 방문 탐색
			
			// 2. from to from to 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			for (int idx = 0; idx < inputSize/2 ; idx ++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from][to] = 1; // 그래프 입력
			}
			
			// 결과
			int result  = bfs(startPoint);
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		
		} // end for testcase
		System.out.println(sb);
		

	}

}
