
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @date 23.08.30
 * @problem BOJ_11048_이동하기
 *
 * [문제]
 * 1. 준규  N*M 미로에 같혀있다.
 * 2. 미로는 (1,1)부터 있다고 간주
 * 3. 준규는 (r+1, c) (r, c+1) (r+1,c+1)로만 이동 가능
 * 4. 각 방을 방문할 때마다 방에 놓여져 있는 사탕을 모두 가져갈 수 있다.
 * 5. 준규가 맵끝으로 갈 때 가져올 수 있는 사탕의 최대값
 *
 *
 * @author sejeong-park
 * */
public class Main {

    // 입출력 객체
    static BufferedReader br;
    static StringTokenizer st;

    // 맵 정보
    static int rowSize, colSize;
    static int [][] map;

    // 준규가 움질일 수 있는 방향 (하, 좌, 대각선 아래)
    static final int[] deltaRow = {1, 0, 1};
    static final int[] deltaCol = {0, 1, 1};

    // 맵 밖으로 나가는지 여부 확인
    public static boolean isInMap(int row, int col) {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize)
            return false;
        return true;
    }

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub

        // 입출력 객체
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 맵 입력
        map = new int[rowSize][colSize];

        // 맵 초기화
        for (int row = 0; row < rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < colSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 준규 초기화
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++){
                // 준규 이동하기
                int prev = 0; // 최대값 찾기 
                // 이전 탐색(3방향)에서 가장 큰 값을 비교하여 map에 넣을 것
                for (int direction = 0; direction < 3; direction++){
                    int nextRow = row - deltaRow[direction];
                    int nextCol = col - deltaCol[direction];
                    if (!isInMap(nextRow, nextCol)) continue;
                    prev = Math.max(prev, map[nextRow][nextCol]);
                }
                map[row][col] += prev;
            }
        } // end for dp

        // 결과 출력
        System.out.println(map[rowSize-1][colSize-1]);
    }
}
