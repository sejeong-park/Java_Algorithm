
import java.util.Scanner;

public class Solution {

    static StringBuilder sb;

    // size 넣기
    public static void Pascal(int size) {

        int[][] pascalArr = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col <= row; col++) {
                // 삼각형의 양옆은 1
                if (col == 0 || col == row) pascalArr[row][col] = 1;
                else {
                    // 그렇지 않으면
                    // 자신의 왼쪽과 오른쪽 위의 숫자의 합
                    pascalArr[row][col] = pascalArr[row - 1][col] + pascalArr[row - 1][col - 1];
                }
            }
        }

        for (int row = 0; row < size; row++){
            for (int col = 0; col <= row; col++){
                sb.append(pascalArr[row][col] + " ");
            }
            sb.append("\n");
        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            sb.append("#").append(test_case).append("\n");
            Pascal(N);
        } // end for test_Case
        System.out.println(sb);
    }

}
