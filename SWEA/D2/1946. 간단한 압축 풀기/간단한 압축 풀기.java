
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 원본 문서 너비 10줄
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        // 입출력 받기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 입력 받기
        int T = Integer.parseInt(br.readLine().trim());

        for (int test_case = 1; test_case <= T; test_case++){
            // 주어지는 알파벳 개수
            int charCnt = Integer.parseInt(br.readLine().trim());
            // String 초기화
            String strZip = ""; // 압축 초기화
            // 입력
            for (int idx = 0; idx < charCnt ; idx++){
                st = new StringTokenizer(br.readLine().trim());
                String str = st.nextToken();
                int cnt = Integer.parseInt(st.nextToken());

                // repeat를 이용해 풀었는데! java 11부터 적용되었다고 한다. -> SWEA는 java8까지임 ㅜ
                // strZip += str.repeat(cnt); // java에서 String에 연속적으로 집어 넣는 것
                while(cnt>0){
                    strZip+=str;
                    cnt--;
                }
            }

            // 줄바꿈 넣기
            sb.append("#").append(test_case).append("\n");
            // 줄당 문자수가 10개씩되도록
            for (int idx = 0; idx < strZip.length(); idx += 10){
                if ((idx+10) > strZip.length()){
                    sb.append(strZip.substring(idx, strZip.length()));
                }else{
                    sb.append(strZip.substring(idx, idx + 10));
                }
                sb.append("\n");
            }
        } // end for test_case
        System.out.println(sb);


    }

}
