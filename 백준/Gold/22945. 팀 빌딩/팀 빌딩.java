

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_22945_팀빌딩
 *
 * 하나의 팀을 만들기 위해 개발자 2명이 반드시 모여얗 ㅏㅁ
 * A와 B
 * A와 B 사이에 존재하는 다른 개발자 수 * min(A, B)
 *
 *
 * */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        int [] team = new int [N];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++) {
            team[idx] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N - 1;
        int max = Integer.MIN_VALUE;

        while(start <= end) {
            // 사이의 값 * min(start, end)
            int ability = (end - start - 1) * Math.min(team[start], team[end]);
            max = Math.max(max, ability);

            // 능력치가 더 작은 것이 클수록 좋음
            if (team[start] < team[end]) {
                start ++;
            } else {
                end --;
            }
        }
        System.out.println(max);
    }

}
