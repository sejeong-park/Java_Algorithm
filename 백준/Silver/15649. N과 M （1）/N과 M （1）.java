
import java.util.Scanner;


// N과 M이 주어졌을 때
// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
// 순열 -> 앞에서 뽑은 수는 다시 뽑으면 안된다.

public class Main {
	
	static int [] targetArray;
	static int N;
	static int M;
	static int[] outputArray;
	static boolean[] visited;
	
    public static void permutation(int depth){

        // depth가 끝났을 경우
        if (depth == M){
            for (int idx = 0; idx < M; idx ++){
                System.out.print(outputArray[idx] + " ");
            }
            System.out.println();
            return;
        }
        
        // 순열 로직 
        for (int idx = 0; idx < N ; idx ++){
            if (!visited[idx]){
                visited[idx] = true;
                outputArray[depth] = targetArray[idx];
                permutation(depth+1);
                visited[idx] = false;
            }
        }


        
    }
    

	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // N
		M = sc.nextInt(); 	  // M

		targetArray = new int[N]; // 1~N까지 배열
		// 출력 대상
		outputArray = new int[M]; 
		visited = new boolean[N]; // 방문 여부 탐색 
		
		// 1부터 N까지 배열에 넣기
		for (int idx = 1; idx <= N; idx ++ ) {
			targetArray[idx-1] = idx; 
		}
		
		// 순열 탐색
		permutation(0);		
	}

}
