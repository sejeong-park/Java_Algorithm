
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * BOJ_ABCDE
 * 1. 사람의 수 N명 & 친구관계의 수 M명
 * 2. M개의 줄에 정수 A,B는 친구라는 의미
 * 3. 모두 친구관계이면 1리턴, 아니면 0 리턴
 * 4. 몇개의 그룹이 리턴되는지 확인한다.
 * 
 * 처음 인접리스트를 단순 2차원배열로 그렸는데, <시간초과> 나서  ArrayList로 변경해주었다.
 * **/
public class Main{

	static BufferedReader br;
	static StringTokenizer st;
	
	static ArrayList<Integer>[] friendsGraph; // 인접 행렬 int [][] -> ArrayList[]
	static boolean[] visited;
	
	static int N;
	static int result;
	
	// 처음 person 기준으로 탐색
	public static void dfs(int current, int depth) {
		
		// 만약 친구관계가 모두가 같은 친구라면,
		if (depth == 5) {
			result = 1;
			return;
		}
		
		// 방문했다!
		visited[current] = true;
		// 진행 조건
		// row줄에 있는 친구 기준으로 탐색
		for (int next : friendsGraph[current]) {
			if (visited[next]) continue;
			dfs(next, depth+1);
		}
		visited[current] = false; // 재사용을 위한 미사용 처리 -> 백트래킹
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		// n과 m 입력
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		friendsGraph = new ArrayList[N];
		for (int idx = 0; idx < N; idx ++) {
			friendsGraph[idx] = new ArrayList<>();
		}
		visited = new boolean[N];
		
		
		for (int friends = 0; friends < M; friends++) {
			// 친구 관계 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 인접 행렬 만들기
			friendsGraph[a].add(b);
			friendsGraph[b].add(a);
		}
		
		// DFS 탐색
		// row 기준으로 1인 것 부터 탐색
		for (int num = 0; num < N; num++) {
			if (result == 1) break;
			dfs(num, 1); // number와 depth 갱신
		}
		
		System.out.println(result);
		
	}

}
