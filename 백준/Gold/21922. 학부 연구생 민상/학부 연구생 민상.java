import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BOJ_학부연구생_민상
 *
 * 연구실 격자모양 -> 에어컨 (상하좌우 4방향으로 분다.) & 다양한 물건들이 있어, 바람의 방향을 바꾼다.
 * 민상이 더위 많이 타서, 에어컨 바람이 지나가는 곳 중 하나 선택하여 앉는다.
 *
 * 오답 원인
 * 조건 1 : 에어컨은 0개 이상 50개 이하가 들어온다. -> 여러 조건 고려하지 못함
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    // 맵 조건 변수
    static int rowSize;
    static int colSize;
    static int[][] map;
    static boolean [][][] visited;
    static Point aircon; // 에어컨 위치

    // delta 방향 (상 우 하 좌 )
    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, 1, 0, -1};

    static final int 상 = 0;
    static final int 우 = 1;
    static final int 하 = 2;
    static final int 좌 = 3;

    // 맵 밖에 있는 지 확인
    public static boolean inMap(int row, int col){
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize){
            return false;
        }
        return true;
    }

    public static int nextDirection(int mapNum, int currentDir){
        if (mapNum == 0){
            return currentDir;
        }
        // 다음 direction 찾기
        int nextDir = currentDir;

        if (mapNum == 1){
            // 우&좌 일 경우
            if (currentDir % 2 != 0){
                nextDir = (currentDir + 2) % 4;
            }
        }
        else if (mapNum == 2) {
            // 상&하일 경우
            if (currentDir % 2 == 0) {
                nextDir = (currentDir + 2) % 4;
            }
        }
        else if (mapNum == 3){
            if (currentDir == 상) nextDir = 우;
            else if (currentDir == 우) nextDir = 상;
            else if (currentDir == 좌) nextDir = 하;
            else if (currentDir == 하) nextDir = 좌;
        }
        else if (mapNum == 4){
            if (currentDir == 상) nextDir = 좌;
            else if (currentDir == 좌) nextDir = 상;
            else if (currentDir == 하) nextDir = 우;
            else if (currentDir == 우) nextDir = 하;
        }

        return nextDir; // 다음 방향
    }

    public static void goWind(int currentRow, int currentCol, int currentDir){
        // 맵 밖에 있거나, 이미 방문했던 것이면, 재귀 종료
        if (!inMap(currentRow, currentCol) || visited[currentRow][currentCol][currentDir]){
            return;
        }
        // System.out.println("current :: " + currentRow + " , " + currentCol + " , " + currentDir);

        visited[currentRow][currentCol][currentDir] = true; // 현재 이동 값 방문
        // 다음 이동할 블록
        int nextDir = nextDirection(map[currentRow][currentCol], currentDir); // 다음 direction
        int nextRow = currentRow + deltaRow[nextDir];
        int nextCol = currentCol + deltaCol[nextDir];
        goWind(nextRow, nextCol, nextDir); // 재귀적으로 탐색
    }

    public static int countSeat(){
        int count = 0;
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < colSize; colIdx ++) {
                if (visited[rowIdx][colIdx][상] || visited[rowIdx][colIdx][하] ||
                        visited[rowIdx][colIdx][좌] || visited[rowIdx][colIdx][우]){
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) throws IOException {

        // 입출력 선언
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 맵 입력 받기
        map = new int[rowSize][colSize];
        visited = new boolean[rowSize][colSize][4];
        for (int rowIdx = 0; rowIdx < rowSize ; rowIdx++){
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0 ; colIdx < colSize ; colIdx++){
                map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
            }
        } // end for input map

        // 4방향으로 에어컨의 이동 => 에어컨은 여러개일 수 있으므로 계속 탐색해줘야 한다.
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
            for (int colIdx = 0; colIdx < colSize; colIdx++){
                if (map[rowIdx][colIdx] == 9){
                    aircon = new Point(rowIdx, colIdx);
                    for (int direction = 0; direction < 4; direction ++ ){
                        goWind(aircon.x, aircon.y, direction);
                    }
                }
            }
        }

        // 바람이 방문한 애들 카운트
        System.out.println(countSeat());
        //  print();
    }

}
