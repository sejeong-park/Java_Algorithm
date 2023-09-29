import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int n;
    static int[][] dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            dist = new int[n][n];  // 거리 저장

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    dist[i][j] = Integer.parseInt(st.nextToken());
                    if(i!=j && dist[i][j] == 0) dist[i][j] = 999999;   // 주 대각선이 아닌데 0인 경우
                }
            }

            floydWarshall();
            int min = Integer.MAX_VALUE;
            for(int i=0; i<n; i++){
                int ret = 0;
                for(int j=0; j<n; j++)
                    ret += dist[i][j];
                min = Math.min(min, ret);
            }
            System.out.println("#" + tc + " " + min);
        }
    }

    private static void floydWarshall(){
        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                if(i == k) continue;
                for(int j=0; j<n; j++){
                    if(i == j || k == j) continue;
                    // 바로 가는 것 보다 k를 거쳐서 가는게 더 빠른 경우 update
                    if(dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
}

