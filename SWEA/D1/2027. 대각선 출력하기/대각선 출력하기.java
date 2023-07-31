public class Solution {
    public static void main(String[] args) {
        // StringBuilder 출력 최적화 -> StringBuilder에 담아서, 출력 직전에 문자열로 생성 후 반환
        StringBuilder sb = new StringBuilder();
        String str = "+++++"; // 기본 문자열

        for (int idx=0 ; idx<str.length() ; idx++){
            char[] chars = str.toCharArray();
            chars[idx] = '#' ; // char형이라 "가 아닌 '
            sb.append(chars).append("\n"); // 줄바꿈 추가해줘야 한다.
        }
        System.out.println(sb);
    }
}
