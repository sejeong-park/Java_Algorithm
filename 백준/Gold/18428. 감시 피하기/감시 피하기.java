import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ_18428_감시피하기
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int mapSize;
    static char [][] map;

    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, -1, 0, 1};

    static ArrayList<Point> teacherList;

    // 지역으로 사용하는 전역 변수
    static int count;
    static String result;

    private static boolean inMap(int row, int col) {
        return row >= 0 && row < mapSize && col >= 0 && col < mapSize;
    }

    private static void findStudent(Point point, int direction) {
        // 만약 맵 밖으로 나가면 종료
        if (!inMap(point.x, point.y)) {
            return;
        }
        // 벽이면 더 이상 탐색을 종료
        if (map[point.x][point.y] == 'O') {
            return;
        }
        // 학생을 찾았다면
        if (map[point.x][point.y] == 'S') {
            // 만약 이미 카운팅 한것이면
            count += 1;
        }

        int nextRow = point.x + deltaRow[direction];
        int nextCol = point.y + deltaCol[direction];
        findStudent(new Point(nextRow, nextCol), direction);
        return;
    }

    private static boolean catchableStudent() {
        // 선생님이 학생을 찾는다
        count = 0;
        // 선생님 입장에서 학생 레이더를 찾는다 (횟수는 중복 포함)
        for (Point teacher : teacherList) {
            // 4방향 dfs(찾기)
            for (int direction = 0; direction < 4; direction ++) {
                findStudent(teacher, direction);
            }
        }
        if (count == 0) {
            return false; // 잡을 수 없는 게 있으면
        }
        return true; // 잡는 경우
    }

    private static void makeMap(int wall) {
        if (wall == 3) {
            // 탐색
            if (!catchableStudent()){
                result = "YES"; // 잡을 수 없다!
            }
            return;
        }
        // wall 세우기
        for (int row = 0; row < mapSize; row ++) {
            for (int col = 0; col < mapSize; col ++) {
                if (map[row][col] == 'X') {
                    map[row][col] = 'O'; // 넣기
                    makeMap(wall + 1);
                    map[row][col] = 'X'; // 다시 풀기
                }
            }
        }
    }



    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        mapSize = Integer.parseInt(br.readLine().trim());
        teacherList = new ArrayList<>();

        // 입력
        map = new char[mapSize][mapSize];
        for (int row = 0; row < mapSize; row ++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col ++) {
                map[row][col] = st.nextToken().charAt(0);
                if (map[row][col] == 'T') {
                    teacherList.add(new Point(row, col));
                }
            }
        }


        result = "NO"; // 결과물
        // 장애물 설치 3개
        makeMap(0);
        System.out.println(result);
    }
}
