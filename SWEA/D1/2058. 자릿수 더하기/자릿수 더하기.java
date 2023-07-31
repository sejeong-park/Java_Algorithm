import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{
        /*
        * 하나의 자연수를 입력받아 각 자릿수의 합을 계산
        * */
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = 0;
        // N이 0이상일때까지 반복
        while(N>0){
            result += (N % 10);
            N /= 10;
        }
        System.out.println(result);
	}
}