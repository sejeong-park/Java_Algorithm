
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 시계방향으로 90도 180도 270도 회전
public class Solution {

    // 기본 입출력 변수
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // 회전 대상 배열 입력
    static int arraySize;
    static int [][] baseArray;
    static String[] result; // 출력 결과

    // 출력 값 만들기 -> int 형을 string 형의로 변경해주기
    public static String makeResult(int [] row){
        String rowString = ""; // string 초기화
        // int 형을 string 형으로 변경해주기
        for (int col = 0; col < arraySize; col++){
            rowString += Integer.toString(row[col]);
        }
        rowString += " "; // 공백 추가
        return rowString;
    }

    // 90도 기본 회전 시키기
    public static void rotateArray(){
        int [][] rotatedArray = new int[arraySize][arraySize];
        // 90도 배열 회전시키기
        for (int row = 0; row < arraySize; row ++){
            for (int col = 0; col < arraySize; col ++){
                rotatedArray[row][col] = baseArray[arraySize - col - 1][row];
            }
        }
        for (int row = 0; row < arraySize; row++){
            result[row] += makeResult(rotatedArray[row]); // 열 기준으로 result 결과를 만들어줄 함수에 넣는다.
        }
        baseArray = rotatedArray; // baseArray에 회전한 값 넣기
    }

    public static void main(String[] args) throws IOException {

        // 기본 입출력
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 테스트 케이스 입력 받기
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T ; test_case++){
            // 배열 입력 받기
            arraySize = Integer.parseInt(br.readLine().trim());
            // 회전 입력 받기
            baseArray = new int[arraySize][arraySize];
            for (int row = 0; row < arraySize; row++){
                // row 기준 사용자에게 입력
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < arraySize; col++){
                    baseArray[row][col] = Integer.parseInt(st.nextToken());
                }
            } // 배열 입력 받기
            // 결과 입력 받을 String 배열
            result = new String[arraySize];
            Arrays.fill(result, ""); //  JAVA 에서 String 배열은 기본 default 설정이 null 이므로 초기화하기 위해 다음과 같이 선언

            // 회전 시키기 90 , 180, 270
            rotateArray(); // 처음 90도 회전
            rotateArray(); // 한번 더 90도 회전 -> 180도
            rotateArray(); // 한번 더 90도 회전 -> 270도

            sb.append("#").append(test_case).append("\n");
            for (int row = 0; row<arraySize; row++){
                sb.append(result[row]).append("\n");
            }


        }// end for test_case
        System.out.println(sb);
    }

}
