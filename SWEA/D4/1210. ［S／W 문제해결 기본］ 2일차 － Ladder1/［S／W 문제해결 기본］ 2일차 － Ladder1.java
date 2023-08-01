
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제
 * - 사다리 게임을 통해 아이스크림 구입
 * - 어느 사다리를 고르면 X 표시에 도달하는 가
 * 주어진 조건
 * - 100 * 100 2차원 배열
 * - 사다리는 연속된 1 도착지점은 2로 표현
 * 반환 조건 : 지정된 도착점에 대응되는 출발점 X를 반환
 *
 * 풀이 설계
 * 1. 100 * 100 입력을 받을 때, "2"(도착지점을 기준으로) 위로 탐색
 * 2. 위로 갈 경우 앞에서 만나는 꺾음에서 무조건 양 옆을 우선으로 탐색
 * */
public class Solution {

    static int[][] ladder; // ladder
    static boolean[][] visited; // false로 기본값 설정

    // 사다리를 타는 x,y
    static int x;
    static int y;

    // 사다리의 밑에서부터 탐색할 것이기 때문에 "하"로 돌아갈 일이 없다.
    // 좌, 우, 상 만 먼저 만나는 것을 탐색할 것
    static int [] dx = {0, 0, -1};
    static int [] dy = {-1, 1, 0};

    static int result = -1;

    public static void FindStartPoint(int x, int y){
        // 종료 조건 (x = row / y = col)
        if (x == 0){
            result = y; // 최종 colum number
            return;
        }

        // 탐색 조건
        for (int direction = 0 ; direction < 3 ; direction++){
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 탐색 조건
            // 1. 좌표 값 내에 있는 지 확인
            // 2. ladder 값은 1로 지정되어 있다.
            // 3. 방문하지 않은 값 (3개 탐색이고, 한방향으로 탐색하는 것이라 visited가 필요한가? 했는데, 안하면 StackOverFlow 터진다)
            if (0 <= nx && nx <100 && 0<= ny && ny <100 && ladder[nx][ny] == 1 && !visited[nx][ny]){
                // 재귀적으로 다시 탐색
                visited[nx][ny] = true;
                FindStartPoint(nx, ny);
                break; // "상"보다 좌, 우가 걸리면, 추가적으로 탐색할 필요가 없기 때문 : 사다리
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // test_case 10개
        for(int test_case = 1; test_case<=10; test_case++){
            // 사다리 100 * 100 사다리
            // Test Case 별로 다시 선언 후 초기화 !! // 중요
            ladder = new int[100][100];
            visited = new boolean[100][100];
            result = -1; // result 초기화


           // 테스트 케이스 주어진다.
            int InputTestCase = Integer.parseInt(br.readLine().trim());
            // 사다리 입력 받기
            for (int row = 0; row < 100; row ++){
                // 사다리 row 입력 받기
                st = new StringTokenizer(br.readLine().trim(), " "); // split으로 입력 받기
                for (int col = 0; col <100; col++){
                    ladder[row][col] = Integer.parseInt(st.nextToken()); // 입력 받은 token str to integer
                    // 만약 row 마지막에서 사다리의 끝점 2가 존재한다면? 따로 저장!
                    if (row == 99){
                        if (ladder[row][col] == 2){
                            x = row;
                            y = col;
                        }
                    }
                }

            }
            //
            FindStartPoint(x, y);

            System.out.println("#" + InputTestCase + " " + result);
        }

    }

}
