

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ_3055_탈출
 * - 고슴도치는 비버의 굴로 가능한 빨리 도망가 홍수를 피해야한다.
 * - D : 비버의 굴 S : 고슴도치의 윛
 * - 물이 있는 칸과 인접해 있는 비어있는 칸은 물을 차게 된다.
 * - 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간?
 * - 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. -> 다음 시간에 물이 찰 예정인 칸으로 이동할 수 없다.
 * -> 고슴도치가 이동하기 전에 물이 찬다고 가정하자
 * */
public class Main{

    // 입출력
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // 맵 정보
    static int rowSize, colSize;
    static char[][] map;
    static int[][] visited;

    static Point 고슴도치;
    static Point 비버;
    static ArrayList<Point> waterList;

    static int invalidCnt = 0;
    static int movement = 0; // 불가능한 영역

    // delta
    static int [] deltaRow = {-1, 1, 0, 0};
    static int [] deltaCol = {0, 0, -1, 1};

    public static boolean inMap(int row, int col){

        if (row < 0 || row >= rowSize || col < 0 || col >= colSize){
            return false;
        }

        return true;
    }

    public static ArrayList<Point> flood(ArrayList<Point> waterList){
        // 홍수 난 경우

        ArrayList<Point> newWaterList = new ArrayList<>();

        Point water = null;
        for (int idx = 0; idx < waterList.size(); idx++){

            water = waterList.get(idx);

            for (int direction = 0; direction < 4; direction++){
                int nextRow = water.x + deltaRow[direction];
                int nextCol = water.y + deltaCol[direction];

                // 조건에 만족하지 않으면 -> "." 빈칸만 이동 가능
                if (!inMap(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] != '.') continue;
                if (visited[nextRow][nextCol] == -1) continue;

                // 만약 이동가능한 물이라면
                map[nextRow][nextCol] = '*';
                visited[nextRow][nextCol] = -1;
                newWaterList.add(new Point(nextRow, nextCol)); // 홍수 구간
            }
        } // end for flood

//        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
//            System.out.println(Arrays.toString(visited[rowIdx]));
//        }

        return newWaterList;
    }


    public static Deque<Point> move(Deque<Point> queue, int time){

        Deque<Point> nextQueue = new ArrayDeque<Point>();
        Point now = null;

        while(!queue.isEmpty()){

            // 현재
            now = queue.poll();

            for (int direction = 0; direction < 4; direction++){
                int nextRow = now.x + deltaRow[direction];
                int nextCol = now.y + deltaCol[direction];

                if (!inMap(nextRow, nextCol)) continue; // 맵 밖으로 나가면 종료
                // 만약 고슴도치가 돌이나 물을 만나면
                if (map[nextRow][nextCol] == 'X' || map[nextRow][nextCol] == '*') continue;
                if (visited[nextRow][nextCol] != 0) continue; // 이미 방문했던 곳이라면

                // 그렇지 않을 경우
                nextQueue.add(new Point(nextRow, nextCol)); // 새로 추가하는 큐에 넣기
                visited[nextRow][nextCol] = time;
                movement += 1;
            }
        }

        return nextQueue; // 새로운 큐로 갱신
    }

    // 움직이는 프로세스
    public static void solution(){

        Deque<Point> queue = new ArrayDeque<>();
        queue.add(고슴도치); // 고슴도치 위치로 시작
        movement += 1;

        int time = 0;
        while(true) {
            // 고슴도치가 비버 굴에 만났을 경우
            waterList = flood(waterList); // 홍수 났다.
            queue = move(queue, ++time); // 고슴도치 이동한다.-> 현재 시간

            // 이동을 완료했는데, 비버의 굴에 이동이 끝났다면?
            if (visited[비버.x][비버.y] != 0){
                // 비버 굴에 들어왔다면?
                sb.append(visited[비버.x][비버.y]);
                break; // 반복 종료
            }
            if (queue.size() == 0){ // 더이상 탐색할 것이 없다면 종료
                sb.append("KAKTUS");
                break;
            }
        }
        return;
    }


    public static void main(String[] args) throws IOException {

        // 1. 입출력 받기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 맵 정보 입력
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 맵 입력
        map = new char[rowSize][colSize];
        visited = new int[rowSize][colSize];
        waterList = new ArrayList<Point>();
        // 맵 입력
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
            String line = br.readLine().trim();
            for (int colIdx = 0; colIdx < colSize; colIdx++){
                map[rowIdx][colIdx] = line.charAt(colIdx);

                // 고슴도치
                if (map[rowIdx][colIdx] == 'S') 고슴도치 = new Point(rowIdx, colIdx);
                if (map[rowIdx][colIdx] == 'D') 비버 = new Point(rowIdx, colIdx);
                if (map[rowIdx][colIdx] == '*') {
                    waterList.add(new Point(rowIdx, colIdx));
                    visited[rowIdx][colIdx] = -1;
                }
                if (map[rowIdx][colIdx] == 'X') invalidCnt += 1;
            }
        } // end for map

        solution();
        System.out.println(sb);
    }
}