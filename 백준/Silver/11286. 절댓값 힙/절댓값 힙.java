
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * BOJ_11286 절댓값 힙
 * 
 * 
 * */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// java 우선순위 큐
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						// 절대값 기준으로 앞갚이 더 크다면 자리를 바꿔준다.
						if(Math.abs(o1) > Math.abs(o2)) {
							return Math.abs(o1) - Math.abs(o2);
						}else if (Math.abs(o1) == Math.abs(o2)) {
							return o1-o2;
						}else {
							return -1;
						}
					}
				});
		StringBuilder sb = new StringBuilder();
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for (int idx=0; idx < N; idx++) {
			int x = sc.nextInt();
			
			if (x==0) {
				if (queue.isEmpty()) {
					sb.append(0).append("\n");
				}else {
					sb.append(queue.poll()).append("\n");
				}
			}else {
				// 절대값이 가장 작은 값 출력 & 그  값을 배열에서 제거
				queue.add(x);
			}
			
		}
		
		System.out.println(sb);
	} // end main 

}
