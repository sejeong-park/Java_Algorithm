
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;


/*
 * 
테스트케이스가 다음과 같은 경우가 존재한다..

1 3
M.Z

3 7
.14....
.M.Z...
..23...

 * 
 * */

public class Main {

    // 입출력
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // map Size
    static int rowSize, colSize;
    static char [][] map;
    static boolean[][] visited;

    // startPoint & endPoint
    public static class Point{
        int row;
        int col;
        int nextDir;

        Point(int row, int col, int dir){
            this.row = row;
            this.col = col;
            this.nextDir = dir;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "row=" + row +
                    ", col=" + col +
                    ", nextDir=" + nextDir +
                    '}';
        }
    }
    // 필요한 포인트
    static Point startPoint, endPoint;
    static Point resultPoint;

    // delta (상하 좌우)
    static int[] deltaRow = {-1,1,0,0};
    static int[] deltaCol = {0,0,-1,1};

    // 처음 시작할 때 & 기호의 방향을 찾는 함수
    public static int findDirection(Point point, boolean isPipe){
        // 4방향에서 탐색한다
        int count = 0;
        int tmpDirection = 0;
        for (int direction = 0; direction < 4; direction++){
            int nextRow = point.row + deltaRow[direction];
            int nextCol = point.col + deltaCol[direction];

            // 만약 범위 안에 있고, 파이프라인이 있는 상태라면
            if (0 <= nextRow && nextRow < rowSize
                    && 0 <= nextCol && nextCol < colSize)
            {
                // 방향이 몇개인지 확인한다.
            	if (isPipe == true) {
            		if (map[nextRow][nextCol] != '.' && map[nextRow][nextCol] != 'M' && map[nextRow][nextCol] !='Z') {
	            		count++;
	            		tmpDirection = direction;
	            		}
                }else {
                	// 파이프가 존재하지 않는 다면,
                	if (map[nextRow][nextCol] == '.') {
                		count++;
                		tmpDirection = direction;
                	}
                }
           
                
            } // 처음 direcion만
        } // end for search direction
            
        // 방향에 대해 정립하기
        if (count == 4) {
        	// + 로 내보내는 경우
            return 4;
        }else if (count == 1) {
        	// start & end 일 경우 
        	// 1개인 direction값을 내보내기
        	return tmpDirection;
        }else if (count == 0) {
        	return findDirection(point, false);
        }
      
        return -1;
    }

    

    // 문자로 방향 컨트롤 하기
    // input : 현재의 방향 map 문자, 현재의 방향
    // output : 가능한 방향
    public static int findDir(int direction, char input){
        // 만약 현재 가는 방향이 "상" 이라면
        // 가능한 위치 -> '|', '+', '1', '4'
        if (direction == 0){
            if (input == '|') return 0;
            else if (input == '+') return 0;
            else if (input == '1') return 3;
            else if (input == '4') return 2;

        }
        // 만약 현재 가는 방향이 "하" 이라면
        // 가능한 위치 -> '|', '+' ,'2', '3'
        else if (direction == 1){
            if (input == '|') return 1;
            else if (input == '+') return 1;
            else if (input == '2') return 3;
            else if (input == '3') return 2;
        }
        // 만약 현재 가는 방향이 "좌" 라면
        else if (direction == 2){
            if (input == '-') return 2;
            else if (input == '+') return 2;
            else if (input == '2') return 0;
            else if (input == '1') return 1;
        }
        // 만약 현재 가는 방향이 "우"라면
        else if (direction == 3){
            if (input == '-') return 3;
            else if (input == '+') return 3;
            else if (input == '3') return 0;
            else if (input == '4') return 1;
        }


        // 만약 해당하는 것이 없다면
        // input 방향 그대로 반환해준다.
        return direction;
    }


    // 깊이 우선 탐색
    public static void dfs(Point point){
        // 방문 표시
        // 기저조건
        // 다음 블록을 가야하는 것이 4방향 모두 . 일 경우
        if (map[point.row][point.col] == '.'){
            resultPoint = new Point(point.row, point.col, point.nextDir);
            return;
        }

        // 현재 방향
        int currentDir = point.nextDir; // 현재의 방향
        // 다음 방향 탐색
        int nextRow = point.row + deltaRow[currentDir];
        int nextCol = point.col + deltaCol[currentDir];
        int nextDir = findDir(currentDir, map[nextRow][nextCol]);

        // 만약 범위 안에 있고, .이 아니라면, 다음 블록으로 넘어가 탐색
        if (0<=nextRow && nextRow < rowSize &&
                        0<= nextCol && nextCol < colSize ){
        	
            dfs(new Point(nextRow, nextCol, nextDir));

        }
    }

    // 방향 반대 확인
    public static int oppositeDir(int target){
        if (target == 0) return 1;
        else if (target == 1) return 0;
        else if(target == 2) return 3;
        else if(target == 3) return 2;
        return -1;
    }


    public static void main(String[] args) throws IOException {

        // 입출력 받기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        // map 초기화
        map = new char[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            String str = br.readLine().trim();
            map[row] = str.toCharArray();
            for (int col = 0; col < colSize; col++) {
                // start와 end 값 찾기
                if (map[row][col] == 'M') {
                    startPoint = new Point(row, col, -1);
                } else if (map[row][col] == 'Z') {
                    endPoint = new Point(row, col, -1);
                }
            }
        }

        // 1. 시작 점의 탐색 방향 정의하기
        int startDirection = findDirection(startPoint, true);
        startPoint.nextDir = startDirection;

        // 2. dfs를 타고, 4방향이 . 일 때까지 간다.
        resultPoint = new Point(0, 0, 0); // 결과값 초기화
        dfs(startPoint); // start 시작 점
        startPoint = resultPoint; // result 복사

        // 가운데 출력 값 추가하기
        sb.append(resultPoint.row + 1)
                .append(" ")
                .append(resultPoint.col + 1)
                .append(" ");

        // 3. 끝 점의 탐색 방향 정의하기
        int endDirection = findDirection(endPoint, true);
        endPoint.nextDir = endDirection;

        // 4. dfs를 타고 4방향이 . 일 때까지 간다.
        resultPoint = new Point(0, 0, 0);
        dfs(endPoint);
        endPoint = resultPoint;
       

        // 파이프라인 매칭 하는 것 찾기
        if (findDirection(endPoint, true) == 4){
            // 만약 상하좌우가 모두 있다면, + : 의미 없는 블록은 없다는 가정이 있으므로
            sb.append("+");
        }else{
            if (startPoint.nextDir == oppositeDir(endPoint.nextDir)){
                if (startPoint.nextDir == 2 || startPoint.nextDir == 3) sb.append("-");
                else sb.append("|");
            }
            else if (startPoint.nextDir == 0 && oppositeDir(endPoint.nextDir) == 3 || startPoint.nextDir == 2 && oppositeDir(endPoint.nextDir) == 1) sb.append('1');
            else if (startPoint.nextDir == 1 && oppositeDir(endPoint.nextDir) == 3 || startPoint.nextDir == 2 && oppositeDir(endPoint.nextDir) == 0) sb.append('2');
            else if (startPoint.nextDir == 3 && oppositeDir(endPoint.nextDir) == 0 || startPoint.nextDir == 1 && oppositeDir(endPoint.nextDir) == 2) sb.append('3');
            else if (startPoint.nextDir == 3 && oppositeDir(endPoint.nextDir) == 1 || startPoint.nextDir == 0 && oppositeDir(endPoint.nextDir) == 2) sb.append('4');

        }


        System.out.println(sb);

    }


}
