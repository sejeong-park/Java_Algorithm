

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BOJ_2239_스도쿠_다시
 *
 * 1. 스도쿠 보드 판 입력 받기 (9*9 -> final 상수)
 * 2. 스도쿠 로직
 *  1. 가로의 1~9에서 중복된 숫자는 위치할 수 없다.
 *  2. 세로의 1~9에서 중복된 숫자는 위치할 수 없다.
 *  3. 3*3 묶음에서 중복된 숫자는 위치할 수 없다.
 *
 * */



public class Main {

    static BufferedReader br;
    static StringBuilder sb;

    // map에 대한 정보
    static final int puzzleSize = 9;
    static final int BLANK = 0;
    static int[][] map;
    static List<Point> blankList;

    public static boolean[] checkNumbers(Point curPoint){
        // 스도쿠 조건에 맞는 지 확인하기
        // 1. 1 ~ 9 까지 같은 열에 있는데 중복되는 숫자 없는지
        // 2. 1 ~ 9 까지 같은 행에 있는데 중복되는 숫자 없는지
        // 3. 1 ~ 9 까지 같은 3*3에 있는데 중복되는 숫자 없는지

        // 초기화
        boolean[] checkList = new boolean[puzzleSize + 1];

        // 1. 1 ~ 9 까지 같은 열에 있는데 중복되는 숫자 없는지
        for (int colIdx = 0; colIdx < puzzleSize; colIdx ++){
            int number = map[curPoint.x][colIdx];
            if (number != BLANK){ // 공백이 아니라 숫자가 있으면
                checkList[number] = true;
            }
        }

        // 2. 1 ~ 9 까지 같은 행에 있는데 중복되는 숫자 없는지
        for (int rowIdx = 0; rowIdx < puzzleSize; rowIdx++){
            int number = map[rowIdx][curPoint.y];
            if (number != BLANK){
                checkList[number] = true;
            }
        }

        // 3. 1 ~ 9 까지 같은 3*3에 있는데 중복되는 숫자 없는지
        // 3씩 나눠주면 -> 자기가 3*3기준으로 어떤 섹션에 속해있는지 나온다.
        int miniMapRow = curPoint.x / 3;
        int miniMapCol = curPoint.y / 3;

        // 미니 맵 기준으로 탐색
        for (int rowIdx = miniMapRow * 3; rowIdx < miniMapRow * 3 + 3 ; rowIdx++){
            for (int colIdx = miniMapCol * 3 ; colIdx < miniMapCol * 3 + 3; colIdx++){
                int number = map[rowIdx][colIdx];
                if (number != BLANK){
                    checkList[number] = true;
                }
            }
        }

        // 존재하는 숫자인지 아닌지 확인하는 리스트 반환
        return checkList;
    }



    public static void sudoku(int curIdx){

        // 1. 기저 조건
        // 현재 인덱스가 blankList의 크기와 일치할 경우
        if (curIdx == blankList.size()){
            print();
            System.out.println(sb);
            // 문제 출력 조건에서 -> 사전식으로 앞서는 것 출력
            // 앞에서부터 서칭하므로, 처음 결론 짓는것이 끝!
            // 종료
            System.exit(0);
        }

        // 2. 진행조건
        // 1 ~ 9까지의 숫자로 찾는데, 같은 열, 행, 같은 묶음에 같은 숫자가 없을 경우

        // 현재 좌표 위치
        Point curPoint = blankList.get(curIdx);
        boolean[] checkList = checkNumbers(curPoint); // 현재 위치 기준으로 가능 여부 리스트 반환
        // 스도쿠 조건에 존재하지 않은 경우의 숫자만 탐색
        for (int number = 1; number <= puzzleSize; number++){
            if (!checkList[number]){
                // 맵에 number 넣고 다음으로 넘어가기
                map[curPoint.x][curPoint.y] = number;
                sudoku(curIdx + 1); // 다음 인덱스로 넘어가기
                // 위 케이스 말고 다음 케이스로도 탐색
                map[curPoint.x][curPoint.y] = BLANK;
            }
        }
    }

    public static void print(){
        for (int rowIdx = 0; rowIdx < puzzleSize; rowIdx++){
            for (int colIdx = 0; colIdx < puzzleSize; colIdx++){
                sb.append(map[rowIdx][colIdx]);
            }
            sb.append("\n");
        }
    }



    public static void main(String[] args) throws IOException {

        // 기본 입출력 선언
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 맵과,  blankList 초기화하기
        map = new int[puzzleSize][puzzleSize];
        blankList = new ArrayList<>();

        // 맵 입력 받기
        for (int rowIdx = 0; rowIdx < puzzleSize; rowIdx ++){
            String [] line = br.readLine().trim().split("");
            for (int colIdx = 0; colIdx < puzzleSize; colIdx++){
                int input = Integer.parseInt(line[colIdx]);
                // 빈 공간인 경우 리스트에 넣기
                if (input == BLANK){
                    blankList.add(new Point(rowIdx, colIdx));
                }
                map[rowIdx][colIdx] = input; // 맵만들기
            }
        }

        // 스도쿠 게임하기
        sudoku(0);

        // 출력 -> // 끝나고 나서 출력하면, 0으로 백트래킹 해줌으로 안돼

    }


}
