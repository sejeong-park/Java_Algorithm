
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 지름길
 *
 * - 세준이 학교가려고 D 고속도로 지남
 * - 고속도로는 심각하게 커브가 많아서,
 * - 모든 지름길은 일방통행, 고속도로를 역주행할 수 없음
 * - 세준이가 운전해야하는 최솟값 구하기
 * */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int cnt;
    static int totalDistance;

    // 위치를 지정할 포인트
    static class Point{
        int start;
        int distance;
        Point(int start, int distance) {
            this.start = start;
            this.distance = distance;
        }
    }

    static ArrayList<Point> [] path ; // 지름길 정보를 저장하기 위한 리스트
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        st = new StringTokenizer(br.readLine().trim());
        cnt = Integer.parseInt(st.nextToken());
        totalDistance = Integer.parseInt(st.nextToken());

        int[] distanceList = new int[totalDistance + 1];
        path = new ArrayList[10001]; // 초기화
        Arrays.fill(distanceList, Integer.MAX_VALUE); // 거리 배열 최대값으로 초기화
        for (int idx = 0; idx < 10001; idx ++) { // 조건에서 주어진 거리
            path[idx] = new ArrayList<>();
        }


        // 반복
        for (int idx = 0; idx < cnt; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            // 지름길이 무조건으로 최단 거리는 아니다.
            if (endPoint - startPoint > distance) {
                path[endPoint].add(new Point(startPoint, distance));
            }
        }

        // 최단거리 구하기
        distanceList[0] = 0; // 초기화
        for (int idx = 1; idx <= totalDistance; idx ++) {
            // path(자름길)이 존재하는 조건 -> 지름길 있다면 지름길 중 가장 최단거리 갱신
            if (path[idx].size() > 0) {
                // 지름길 내에서 갱신
                for (Point point : path[idx]) {
                    if (distanceList[point.start] + point.distance > distanceList[idx]) continue; // 이미 갱신
                    distanceList[idx] = Math.min(distanceList[idx - 1] + 1, distanceList[point.start] + point.distance);
                }
                continue;
            }
            distanceList[idx] = distanceList[idx - 1] + 1;
        }
        System.out.println(distanceList[totalDistance]); // D거리까지 이동하는 최단 거리
    }

}
