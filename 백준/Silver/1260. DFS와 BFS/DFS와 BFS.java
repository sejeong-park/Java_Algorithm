

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	// 기본 입출력
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 그래프
	static int[][] graph; // 그래프를 넣어줄 리스트
	static boolean[] visited;
	static int N;

	
	// 방문 dfs
	public static void dfs(int start) {
		// 현재 노드 방문 표시
		sb.append(start).append(" ");
		visited[start] = true; 
		for (int node = 0; node <= N; node++) {
			// 1은 존재하는 노드표시
			// 만약 방문하지 않았다면, 방문
			if (graph[start][node] == 1 && !visited[node]) {
				dfs(node);
			}
		}// end for graph[start] 		
	}

	// 방문 bfs
	public static void bfs(int start) {
		
		// 큐 만들기
		Deque<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start); // 처음 입력 값 넣기
		visited[start] = true; // 방문 표시
		
		// 큐가 비지 않을 때까지 탐색
		while (!queue.isEmpty()) {
			int now = queue.pop(); // 마지막 큐 빼기
			sb.append(now).append(" ");
			
			// 큐가 탐색 할 수 있는 node 파악하기
			for (int node = 0; node <= N; node++) {
				if (graph[now][node] == 1 && !visited[node]) {
					queue.add(node);
					visited[node] = true;
				}
			}
		}
		return;
	}
	
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력 선언
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 입력 
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int startNode = Integer.parseInt(st.nextToken()); // 탐색을 시작하는 번호
		
		// 탐색 리스트
		// 그래프 만들기
		graph = new int[N+1][N+1]; //  graph를 2차원 배열로
		
		// 간선 입력 받기
		for (int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			// start, end 값 입력 받기
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			// 둘다 탐색하는 것에 1
			graph[start][end] = 1; // 입력 넣기 
			graph[end][start] = 1;			
		}
		
		
		// 방문 그래프 초기화
		visited = new boolean[N+1]; // 그래프 노드 탐색 여부 표시
		dfs(startNode);
		sb.append("\n"); // 줄바꿈 넣어주기
		// 방문 그래프 초기화
		visited = new boolean[N+1];
		bfs(startNode);
		
		// 출력
		System.out.println(sb);
	

	}

}
