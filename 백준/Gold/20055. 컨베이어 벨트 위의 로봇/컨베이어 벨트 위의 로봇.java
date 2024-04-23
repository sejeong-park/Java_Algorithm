
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_20055 컨베이어 벨트 위의 로봇
 *
 * N인 컨베이어 벨트가 있고 2N인 벨트가 컨베이어 벨트를 감싸며 돌고 있다.
 * 1번 칸 올리는 위치 / N번 칸 내리는 위치
 *
 * 1. 벨트가 각 칸 위에 있는 로봇과 한 칸 회전한다.
 * 2. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한 칸 이동할 수 있으면 이동한다.
 * 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며 그 칸의 내구도가 1이상 남아있어야 한다.
 * 3. 올리는 위치에 있는 칸의 내구도가 0 이 아니면 올리는 위치에 로봇을 올린다.
 * 4. 내구도가 0인 칸의 개수가 k개 이상이면 과정을 종료한다. 그렇지 않으면 1번으로 돌아간다.
 * */
public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int beltSize, totalCnt; // 기본값 N, K
    static int [] beltList; // belt
    static boolean [] robot; // robot

    static final int UP = 0;

    public static int simulation() {
        int cnt = 0;

        // 내구도가 k개 이상이라면 종료 / 아니면 반복
        while(check()) {
            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
            rotateBelt(beltList);
            // 2. 로봇의 벨트 이동
            rotateRobot(robot);

            // 3. 가장 먼저 벨트에 올라간 로봇부터 벨트가 회전하는 방향으로 한칸 이동
            // - 로봇의 이동을 위해, 이동하려는 칸에 로봇이 없으며 그 칸의 내구도가 1이상 남아있어야 함
            for (int idx = robot.length - 1; idx > 0 ; idx -- ){
                if (robot[idx - 1] && !robot[idx] && beltList[idx] >= 1) {
                    beltList[idx] --;
                    robot[idx] = true;
                    robot[idx - 1] = false;
                }
            }

            // 4. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇 올림
            if (beltList[0] > 0) {
                robot[UP] = true;
                beltList[UP]--;
            }
            cnt ++;
        }
        return cnt;
    }

    public static int[] rotateBelt(int [] belt) {
        // 컨테이너 벨트의 회전
        int tmp = belt[belt.length - 1];
        for (int idx = belt.length - 1; idx > 0; idx --) {
            belt[idx] = belt[idx - 1];
        }
        belt[0] = tmp;
        return belt;
    }

    public static boolean [] rotateRobot(boolean[] belt) {
        // 로봇의 회전
        for (int idx = belt.length - 1; idx > 0 ; idx --) {
            belt[idx] = belt[idx - 1];
        }
        // 0 번에서는 탑승하고
        belt[UP] = false;
        // n - 1번에서는 내린다.
        belt[beltSize - 1] = false;
        return belt;
    }

    public static boolean check() {
        // 내구도가 0칸의 개수가 k 개 이상이라면 과정을 종료, 그렇지 않으면 1번으로 돌아간다.
        int cnt = 0;

        for (int idx = 0; idx < beltList.length; idx ++ ){
            if (beltList[idx] == 0) {
                cnt ++;
            }
            if (cnt >= totalCnt) {
                return false; // 내구도가 k개 이상이라면
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        st = new StringTokenizer(br.readLine().trim());
        beltSize = Integer.parseInt(st.nextToken());
        totalCnt = Integer.parseInt(st.nextToken());

        // 컨테이너 벨트 리스트
        beltList = new int[beltSize * 2];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < beltSize * 2; idx ++) {
            beltList[idx] = Integer.parseInt(st.nextToken());
        }

        // 로봇
        robot = new boolean[beltSize];

        int result = simulation();
        System.out.println(result);
    }

}
