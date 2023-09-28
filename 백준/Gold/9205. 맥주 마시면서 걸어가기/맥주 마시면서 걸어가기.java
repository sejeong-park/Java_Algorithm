
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 *
 *
 *
 * */

public class Main {

    static BufferedReader br;
    static StringBuilder sb;

    // 편의점 정보 입력
    static int storeCnt;
    static Point home;
    static Point festival;
    static Point[] storeList;

    // 입력 만들기 -> 입력의 반복 줄이기
    static public Point makePoint(String input){
        StringTokenizer st = new StringTokenizer(input);

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        return new Point(row, col);
    }

    public static int getDistance(Point point1, Point point2){
        return Math.abs(point1.x - point2.x) + Math.abs(point1.y - point2.y);
    }

    // 상근이 맥주마시면서 가자.
    static public boolean moveWithBeer(){

        // 최단거리 탐색을 위한 큐
        Deque<Point> queue = new ArrayDeque<Point>();
        queue.add(home); // home 넣기

        // 방문한 편의점 체킹하기
        boolean [] visited = new boolean[storeCnt]; // 방문 여부 확인

        while(!queue.isEmpty()){
            Point 상근이 = queue.poll(); // 상근이의 현재 위치

            // 페스티벌에 도달했다면, 종료
            if (getDistance(상근이, festival) <= 20 * 50){
                return true; // 만약 페스티벌까지 도달했다면 종료
            }

            // 상근이의 현재 위치부터 편의점 방문하기
            for (int idx = 0; idx < storeCnt; idx ++){
                if (!visited[idx]){ // 방문하지 않았다면
                    Point store = storeList[idx];
                    if (getDistance(상근이, store) <= 20 * 50){
                        queue.add(store); // 만약 상근이가 이동할 수 있는 편의점이라면, 이동 경로에 추가
                        visited[idx] = true;
                    }
                }
            }
        } // queue 종료
        return false;
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // test case 입력 받기
        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++){
            // 몇개의 store 있는지
            storeCnt = Integer.parseInt(br.readLine().trim());
            storeList = new Point[storeCnt]; // 편의점 개수 넣기

            // home 시작 위치 지정
            home = makePoint(br.readLine().trim());

            // 편의점 위치 지정
            for (int idx = 0; idx < storeCnt; idx++){
                Point store = makePoint(br.readLine().trim()); // 새로운 포인트 만들기
                storeList[idx] = store;
            }

            // festival 지정
            festival = makePoint(br.readLine().trim());

            String result = moveWithBeer() ? "happy" : "sad";
            sb.append(result).append("\n");
        } // end for testCase

        System.out.println(sb);

    }


}
