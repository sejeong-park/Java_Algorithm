
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_230801 D3 농작물 수확하기
 *
 * 1. 처음 접근
 * - N*N 가운데(x,y) 점 기준으로 가장자리 넓혀간다. half 만큼만 재귀로 펼쳐지고, visited 탐색 안한것만 result에 넣기
 * - 문제 : 시간초과
 *
 * 2. 구현
 * - 마름모꼴에서 가운데 row를 기준으로 별찍기 마름모와 같이
 * - index 찾아서 단순하게 더한다.
 * - half row 기준으로 col영역은 1 ->  3 -> 5 -> 3 -> 1 과 같이 증감이 반영되어야 한다.
 * */

public class Solution {

    static StringTokenizer st; // StringTokenizer

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case=1 ; test_case <= T; test_case ++ ){
            int N = Integer.parseInt(br.readLine().trim());
            int [][] array = new int[N][N] ; // int 형 N*N 할당
            // 세로로 입력 받기
            for (int row = 0; row<N; row++){
                char[] charArr = br.readLine().trim().toCharArray();
                for (int col = 0; col < N; col ++){
                    array[row][col] = charArr[col] - '0'; // int 형으로 변환하여 저장
                }

            }
            // 별찍기 처럼 다이아몬드 고려
            int result = 0;

            // 마름모 모양으로 index를 기준으로 퍼져나갈 것
            int half = N/2;
            int start = half;   // column의 시작 index
            int amount = 1;     // 한 column으로 부터 이전보다 증가하는 양

            for (int row = 0; row < N; row ++ ){
                // 위의 삼각형
                for(int col = start; col < start+amount ; col++){
                    result += array[row][col];
                }
                // 위쪽 마름모라면 1 -> 3 -> 5 : 마름모 모양의 index는 1개씩 준다.
                if (row < half){
                    start -= 1;
                    amount += 2; // 1 -> 3 -> 5 ::마름모 모양이 2개 기준
                } else{
                    start += 1; // 다시 start index가 1개씩 증가한다. (흰색 영역)
                    amount -= 2; // 5 -> 3-> 1
                }
            }
            System.out.println("#" + test_case + " " + result);

        }
    }

}
