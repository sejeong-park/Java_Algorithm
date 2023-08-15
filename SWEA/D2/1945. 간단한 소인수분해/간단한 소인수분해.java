
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++){
            int target = sc.nextInt();

            int [] prime = {2, 3, 5, 7, 11};
            int[] result = new int[5];

            while(target != 1){
                for (int idx = 0; idx < 5; idx ++){
                    if (target % prime[idx] == 0){
                        target = target/prime[idx];
                        result[idx] ++;
                    }
                }
            } // end for target

            sb.append("#").append(test_case).append(" ");
            for (int idx = 0; idx < 5; idx++){
                sb.append(result[idx]).append(" ");
            }
            sb.append("\n");

        } // end for testcase
        System.out.println(sb);


    }


}
