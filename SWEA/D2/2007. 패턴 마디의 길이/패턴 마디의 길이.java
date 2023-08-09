
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int test_case = 1; test_case <=T ; test_case ++) {
			
			String strLang = sc.next();
			// 앞에 두글자식 비교
			for (int idx = 1; idx <= strLang.length(); idx++) {
				String str1 = strLang.substring(0, idx);
				String str2 = strLang.substring(idx, idx+idx);
				if (str2.equals(str1)) {
                    System.out.println("#"+test_case+" "+str2.length());
                    break;
				}
			}
		
		}
	}

}
