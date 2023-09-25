

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 17143 낚시왕
 *
 * - 낚시왕 R * C 인 격자 판
 * - 1초 동안 일어나는 일 / 상어의
 * 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서, 땅과 제일 가장 가까운 상어를 잡는다. -> 상어를 잡으면, 격자판에서 잡은 상어가 사라진다.
 * 3. 상어가 이동한다.
 * 결론 : 낚시왕이 잡은 상어 크기의 합
 * -> 문제 풀이 시간 약 3시간 반 정도 소요 ㅜㅅㅠ 
 * */
public class Main {

    // 입출력
    static BufferedReader br;
    static StringTokenizer st;


    // 상어 격자의 크기
    static int rowSize, colSize, sharkCnt;
    static HashMap<Integer, Shark> sharkHashMap;
    static List<Integer>[][] map;
    static int totalSharkSize;

    // 상, 하, 오, 왼
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, 1, -1};


    // 각 shark의 정보
    static class Shark{

        int idx;
        int row;
        int col;
        int speed;
        int direction;
        int size;

        Shark(){};
        // 파라미터 생성자 입력
        Shark(int idx, int row, int col, int speed, int direction, int size){
            this.idx = idx;
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }

        @Override
        public String toString(){
            return "[ shark " + this.idx + "]" +
                    "speed " + this.speed + " , direction"
                    + this.direction + " , size " + this.size;
        }

    }

    public static void fishing(int colIdx){

        // 아래에서 위로 -> 인덱스 자체가
        for (int rowIdx = 0; rowIdx < rowSize ; rowIdx++){
            // 상어가 존재한다면
            if (map[rowIdx][colIdx].size()>0){
                // 상어는 null;
                int sharkIdx = map[rowIdx][colIdx].get(0); // 첫번째 것 가져오기
                totalSharkSize += sharkHashMap.get(sharkIdx).size; // 상어의 사이즈 지정하기
                // 상어 없애기
                map[rowIdx][colIdx].remove(0); // 땅과 가장 제일 가까운 상어는 사라진다.
                sharkHashMap.remove(sharkIdx);
                return; // 만약 가장 가까운 것 잡았으면 종료
            }
        }
        return; // 같은 열에 상어가 없을 수도 있다.
    }

    public static boolean inMap(int row, int col){
        if (row < 0 || col < 0 || row >= rowSize || col >= colSize)
            return false;
        else
            return true;
    }

    public static void moveShark(){

        Shark shark = null;
        // 각 상어들이 있으면 좌표에서 움직이기
        for (int sharkIdx : sharkHashMap.keySet()){
            // hash에서 꺼내기

            shark = sharkHashMap.get(sharkIdx); // shark 값 꺼내기

            // 자기 자신 map에서 빼기 -> 위치 이동하니까 !!
            for (int idx = 0; idx < map[shark.row][shark.col].size(); idx++){
                // 자기 빼기
                if (map[shark.row][shark.col].get(idx) == sharkIdx){
                    map[shark.row][shark.col].remove(idx);
                }
            }

            // 한칸씩 증가
            int nextRow = shark.row;
            int nextCol = shark.col;
            int count = 0;
            while (count != shark.speed){
                nextRow += deltaRow[shark.direction];
                nextCol += deltaCol[shark.direction];

                if (!inMap(nextRow, nextCol)){
                    // 이동 전으로 돌아가
                    nextRow -= deltaRow[shark.direction];
                    nextCol -= deltaCol[shark.direction];
                    // 방향 전환
                    if (shark.direction == 0) shark.direction = 1;
                    else if (shark.direction == 1) shark.direction = 0;
                    else if (shark.direction == 2) shark.direction = 3;
                    else if (shark.direction == 3) shark.direction = 2;
                    count -=1;
                }
                count += 1;
            }


            shark.row = nextRow; // 갱신
            shark.col = nextCol;

            map[nextRow][nextCol].add(sharkIdx); // shark Idx 넣기

        }

        // 전체 상어를 이동시켰다면, 잡아먹은 것이 잇나 확인
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
            for (int colIdx = 0; colIdx < colSize; colIdx ++){
                if (map[rowIdx][colIdx].size() > 1) {
                    eatShark(rowIdx, colIdx);
                }
            }
        }

        // print(map);

    }

    public static void eatShark(int row, int col){
        // 크기가 가장 큰 상어가 나머지를 잡아먹는다.
        List<Integer> sharkList = map[row][col];

        // 사이즈 초기화
        int maxSharkIdx = 0;
        int maxSharkSize = Integer.MIN_VALUE;
        // 다음 것 부터 탐색
        for (int idx = 0; idx < sharkList.size() ; idx++){

            int sharkIdx = sharkList.get(idx);

            if (maxSharkSize < sharkHashMap.get(sharkIdx).size) {
                maxSharkSize = sharkHashMap.get(sharkIdx).size;
                maxSharkIdx = sharkIdx;
            }
        }

        // 최대가 아닌 것들 상어 먹어버리기
        for (int idx = 0; idx < sharkList.size(); idx ++){
            int sharkIdx = sharkList.get(idx);
            if (sharkIdx != maxSharkIdx) {
                sharkHashMap.remove(sharkIdx);
            }
        }

        // 최대 인덱스만 남기기
        map[row][col] = new ArrayList<>();
        map[row][col].add(maxSharkIdx); // 제일 큰 것만 넣기

    }

    public static void print(List[][] map){
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
            for (int colIdx = 0; colIdx < colSize ; colIdx ++){
                if (map[rowIdx][colIdx].size() > 0){
                    for (int idx = 0; idx < map[rowIdx][colIdx].size(); idx++) {
                        System.out.print(" " + map[rowIdx][colIdx].get(idx) + " ");
                    }
                }else{
                    System.out.print(" X ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }

    // 낚시왕과 상어가 이동하는 메서드
    public static void move(){

        // 낚시왕은 가장 왼쪽부터 오른족 칸까지 이동하면 이동을 멈춘다. -> col만큼
        for (int colIdx = 0; colIdx < colSize; colIdx++){
            // 낚시왕이 있는 열에 있는 상어 중 땅과 제일 가까운 상어를 잡는다.
            fishing(colIdx);
            moveShark();

        }
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // Map의 정보
        st = new StringTokenizer(br.readLine().trim()); // 토크나이저 입력
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        sharkCnt = Integer.parseInt(st.nextToken());

        // 상어가 있는 맵 생성
        map = new List[rowSize][colSize]; // 상어 맵 생성
        // 상어 맵 초기화
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
            for (int colIdx = 0; colIdx < colSize; colIdx++){
                map[rowIdx][colIdx] = new ArrayList<>(); // 객체 모두 생성
            }
        }

        sharkHashMap = new HashMap<>();

        // 낚시왕이 잡은 상어의 크기 초기화
        totalSharkSize = 0;

        // 상어의 정보
        for (int sharkIdx = 1; sharkIdx <= sharkCnt ; sharkIdx ++) {
            st = new StringTokenizer(br.readLine().trim());

            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken()) - 1 ; // 1 ~ 4이므로 인덱스 맞춰주기 위해 1빼기
            int size = Integer.parseInt(st.nextToken());

            // 생성하기
            Shark shark = new Shark(sharkIdx, row, col, speed, direction, size); // 배열에 상어 넣기
            sharkHashMap.put(sharkIdx, shark); // 샤크 맵에다 넣기
            map[row][col].add(sharkIdx);
        }

        // 낚시왕과 상어가 이동하는 메서드
        move();
        // 결과
        System.out.println(totalSharkSize);
    }

}
