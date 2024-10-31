

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 모바일 광고 입찰
 * - N 개에 대한 입찰 데이터 분석 / 입찰 가격 결정 로직을 개선하는 것
 * -
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static class Call implements Comparable<Call> {
        int a;
        int b;
        public Call(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Call o) {
            return (o.a - o.b) - (this.a-this.b);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Call> list = new ArrayList<>();
        for (int idx = 0; idx < N; idx ++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Call(a, b));
        }
        Collections.sort(list);

        // 몇번째까지.
        // System.out.println(list.get(K - 1).a + " " + list.get(K - 1).b);
        int x = 0;
        if (list.get(K - 1).a < list.get(K - 1).b) {
            x = list.get(K-1).b - list.get(K-1).a;
        }
        System.out.println(x);



//        for (int idx = 0; idx < N; idx ++) {
//            System.out.println(list.get(idx).a + " " + list.get(idx).b);
//        }

    }

}
