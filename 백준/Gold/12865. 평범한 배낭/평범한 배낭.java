import java.util.*;
import java.io.*;

public class Main {
	public static int n, k;
	public static int weight[], price[], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = 
        		new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        // 인덱스 1부터 시작
        weight = new int[n+1];
        price = new int[n+1];
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	weight[i] = Integer.parseInt(st.nextToken());
        	price[i] = Integer.parseInt(st.nextToken());
        }
        
        dp = new int[n+1][k+1]; // dp[i][j]는 무게 제한이 j일때의 i번째 물건까지의 최대 가치이다
        System.out.println(knapsack());
    }
    
    public static int knapsack() {
    	/* 1부터 n까지의 물건에 대해 무게제한을 1부터 k까지 반복하며
    	 * 현재 물건이 그 무게제한보다 작다면 그 물건을 배낭에 넣는다.
    	 * 무게제한을 작은것부터 시작하여 더 무게가 작은 물건을 먼저 넣게 되고
    	 * k까지 반복하면서 가치가 더 높은 쪽을 선택한다.
    	 */
    	for(int i=1; i<=n; i++)
    		for (int j=1; j<=k; j++) {
    			if (weight[i] <= j)
    				// 이전값(i-1,j)과 현재값(i번째 물건을 포함시킨 경우)중 가치가 큰것을 선택
    				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]]+price[i]);
    			else
    				// 현재 i번째 물건이 k를 넘어선다면 이전 값을 사용해야 한다
    				dp[i][j] = dp[i-1][j];
    		}
    	return dp[n][k];
    }
}