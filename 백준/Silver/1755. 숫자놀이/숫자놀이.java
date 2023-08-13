

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ_1755_숫자놀이
 * 79를 영어로 읽되, 하나씩 읽으면 seven nine
 * 80 -> eight zero
 * 영어로 숫자 하나씩 읽는 다면, eight zero가 seven nine 보다 사전 순으로 먼저온다
 * 사전 순으로 정렬하여 출력 -> compare
 *
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static final String[] STRING_NUM = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    // number int - string Pair
    static class NumberPair implements Comparable<NumberPair>{
        int intNum;
        String stringNum;

        // 생성자
        public NumberPair(int intNum, String stringNum){
            this.intNum = intNum;
            this.stringNum = stringNum;
        }

        @Override
        public int compareTo(NumberPair o) {
            // 비교 대상
            return stringNum.compareTo(o.stringNum);
        }
    }

    // 두자리수 일 경우 String을 만들어야 한다.
    public static String TransferStringNum(int number){
        if (number < 10){
            // 한자리수 일 경우 -> 바로 리턴
            return STRING_NUM[number];
        }else{
            // String 으로 리턴해줄것
            // 두자리 수 일경우
            return STRING_NUM[number/10] + " " + STRING_NUM[number%10];
        }
    }

    public static void main(String[] args) throws IOException {

        // 입출력 받기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 입력 받기
        st = new StringTokenizer(br.readLine().trim());
        // start, end 구하기
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 배열 생성
        NumberPair[] NumberPair = new NumberPair[end - start + 1];

        // start ~ end 까지 단어 넣기
        int index = 0; // 배열에 넣을 것
        for(int num = start ; num <= end ; num++){
            NumberPair[index++] = new NumberPair(num, TransferStringNum(num));
        }

        // 사전 순으로 정렬
        Arrays.sort(NumberPair);

        // 10개씩 정렬
        for (int idx = 1; idx <= NumberPair.length; idx ++){
            // 10개씩 끊기
            sb.append(NumberPair[idx-1].intNum).append(' ');
            if (idx % 10 == 0) {
                sb.append("\n"); // 10개 기준으로 줄바꿈
            }
        }
        System.out.println(sb);


    }

}
