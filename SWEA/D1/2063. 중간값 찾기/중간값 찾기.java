import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


class Solution
{
       public static void main(String[] args) throws IOException {
            // 중간 값 찾기

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());

            String[] str = br.readLine().split(" "); // split 기준으로 String 배열에 넣기
            int[] numList = new int[str.length];
            for(int idx = 0; idx <str.length; idx ++){
                numList[idx] = Integer.parseInt(str[idx]);
            }
            // 숫자 값에 대해 정렬
            Arrays.sort(numList);
            System.out.println(numList[N/2]); // 정렬된 값 중 가운데 출력
        }
    
}