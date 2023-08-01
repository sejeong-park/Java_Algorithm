

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA_1208 Flatten
 *
 * 문제
 * - 높은 곳의 상자를 낮은 곳에 옮기는 방식 -> 최고점과 최저점의 간격을 줄이는 작업 "평탄화"
 * - 평탄화 모두 수행하고 나면, 가장 높은 곳, 가장 낮은 곳 차이 최대 1이내
 * - 반환 조건 : 최고점과 최저점의 차이를 반환
 * - 최고에서 최저 구하면, 각자 자리를 한개씩 빼주고, 더해줘야 한다.
 *
 * 재귀
 * - 기저 조건 : 제한된 횟수
 * - 유도 조건 : max와 min 값을 구한다.
 * - 재귀 돌때마다 cnt --
 *
 * */
public class Solution {

    // 전체 입력 받을 사이즈
    static final int ROW_SIZE = 100;
    static int[] box = new int[ROW_SIZE];

    // minIndex, maxIndex 초기화
    static int minIndex = 0;
    static int maxIndex = 0;
    // maxValue - minValue in [0,1] 일 경우 flag
    static boolean Done = false;

    // 최대, 최소 인덱스 찾는 메서드
    public static void FindMinMaxIndex(){

        // minIndex와 maxIndex 탐색 한다
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        // 최대 index, 최소 index를 구하고 각 값을 더하고 뺀다!
        for (int idx = 0; idx < 100; idx ++) {
            if (box[idx] < minValue) {
                minValue = box[idx]; // min 값갱신
                minIndex = idx;
            }
            if (box[idx] > maxValue) {
                maxValue = box[idx];
                maxIndex = idx;
            }
        }

        // 만약 maxValue와 minValue가 0,1 일 경우 평탄화가 완료되었다.
        if (maxValue - minValue <= 1){
            Done = true;
        }
    }

    public static void dump(int count){

        // 유도 조건
        // minIndex와 maxIndex 탐색 한다
        FindMinMaxIndex();

        // 종료 조건
        // count가 0이거나, 평탄화가 이미 완료되었을 때
        if (count == 0 || Done){
            return;
        }

        // 가장 큰 박스와 작은 박스 값 + , - 처리해주기
        box[maxIndex] -- ;
        box[minIndex] ++ ;

        // 재귀적으로 탐색
        dump(count - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case<=10; test_case ++){
            int count = Integer.parseInt(br.readLine().trim()); // Dump 횟수 입력받기
            // StringTokenizer을 사용하여 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            // 100개 만큼 입력 받기
            for (int idx = 0; idx<100; idx++){
                box[idx] = Integer.parseInt(st.nextToken());
            }

            // 재귀로 탐색
            dump(count);

            // 결과 반환
            System.out.println("#"+ test_case + " " + (box[maxIndex] - box[minIndex]));
        }

    }

}