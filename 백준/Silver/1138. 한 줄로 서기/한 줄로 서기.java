
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ_1138_한줄서기
 * N명 사람 -> 한줄
 * 오민식 사람 줄서는 위치 기록함
 * 매일 자기가 기록한거랑 사람 줄 위치랑 맞는 지 확인함
 * 자기보다 큰 사람이 왼쪽에 몇명이 있었는지만 기억한다.
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        int[] list = new int[N + 1];
        for (int idx = 1; idx < N + 1; idx ++) {
            list[idx] = Integer.parseInt(st.nextToken());
        }
        ArrayList<Integer> order = new ArrayList<>();
        // 키 큰 순서대로 순서에 넣는다
        for (int idx = N  ; idx >= 1; idx --) {
            order.add(list[idx], idx); // 몇번째 순서에 몇번째키인지
        }

        sb = new StringBuilder();
        for (int idx : order) {
            sb.append(idx).append(" ");
        }
        System.out.println(sb.toString());
    }
}
