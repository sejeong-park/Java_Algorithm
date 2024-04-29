

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_14889_스타트와링크
 *
 * N/2 -> Sij i번 사람과 j번 사람이 같은 팀에 속했을 때 팀에 더해지는 능력치
 * Sij Sji 와 다를 수 있음
 * 팀에 더해지는 능력치 -> Sij Sji
 *
 * 스타트 팀과 링크 팀의 능력치 차이의 최소값
 * */


public class Main {


    static BufferedReader br;
    static StringTokenizer st;

    static int mapSize;
    static int[][] map;

    static boolean [] visited;
    static int min;

    public static void findStartLink() {
        int startTeam = 0;
        int linkTeam = 0;

        for (int rowIdx = 0; rowIdx < mapSize - 1; rowIdx ++) {
            for (int colIdx = rowIdx + 1; colIdx < mapSize; colIdx ++) {
                if (visited[rowIdx] == true && visited[colIdx] == true) {
                    startTeam += map[rowIdx][colIdx];
                    startTeam += map[colIdx][rowIdx];
                }
                else if (visited[rowIdx] == false && visited[colIdx] == false) {
                    linkTeam += map[rowIdx][colIdx];
                    linkTeam += map[colIdx][rowIdx];
                }
            }
        }

        int result = Math.abs(startTeam - linkTeam);
        if (result == 0) {
            System.out.println(result);
            System.exit(0);
        }

        min = Math.min(result, min);

    }

    // idx는 인덱스, count 는 깊이
    public static void combination(int targetIdx, int count) {

        if (count == mapSize / 2) {
            findStartLink();
            return;
        }

        for (int idx = targetIdx; idx < mapSize; idx ++) {

            if (!visited[idx]) {
                visited[idx] = true;
                combination(idx + 1, count + 1);
                visited[idx] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        mapSize = Integer.parseInt(br.readLine());
        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize];
        for (int rowIdx = 0; rowIdx < mapSize; rowIdx ++) {
            st = new StringTokenizer(br.readLine());
            for (int colIdx = 0; colIdx < mapSize; colIdx ++) {
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        combination(0,0);
        System.out.println(min);
    }
}
