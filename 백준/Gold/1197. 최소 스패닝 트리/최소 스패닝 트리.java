
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 
 * SWEA_3124_최소스패닝트리
 * 
 * [문제 풀이]
 * 1. 주어진 그래프의 모든 정점들을 연결하는 부분에서 가중치의 합이 최소 인 트리
 * 		1-1. 주어진 그래프는 하나의 연결 그래프
 * 2. T 전체 testCase 입력 받기
 * 3. 정점 V, 간선 개수 E 입력 받기
 * 4. E개의 줄에 간선에 대한 정보 A,B,C 입력 받기 (A와 B가 C 간선으로 입력)
 * @author sejeong-park
 * */


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int V, E;

	static class Node implements Comparable<Node>{
		int from;
		int to;
		int weight;
		
		public Node(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	
	// 그래프
	static int[] parents;
	static Node[] nodeList;
	
	
	static void make() {
		parents = new int[V+1];
		// 부모를 자기 자신으로 만들어서 초기화 한다.
		for (int idx = 0; idx < V; idx++) {
			parents[idx] = idx;
		}
	}
	
	static int find(int element) {
		
		// 부모가 자기 자신이라면 element리턴
		if (parents[element] == element) return element;
		
		// 부모가 따로 있다면, 부모에 자기 자신을 다시 넣는다.
		return parents[element] = find(parents[element]);
		
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		// 사이클 발생
		if (aRoot == bRoot)
			return false;
		
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		// 입출력
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
	
        st = new StringTokenizer(br.readLine().trim());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        nodeList = new Node[E];
        // E개 줄 ABC 입력
        for (int set = 0; set < E; set++) {
            st = new StringTokenizer(br.readLine().trim());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            nodeList[set] = new Node(A, B, C); // 그래프 노드 생성
        }			

        // 먼저 정렬
        Arrays.sort(nodeList);

        // V개의 정점인 parent 만들기
        make();

        int result = 0;
        int count = 0;

        for (Node node : nodeList) {
            if (union(node.from, node.to)) {
                result += node.weight;
                count ++;


                if (count == E-1) break;

            }

        }
        System.out.println(result);
		

		
	}

}
