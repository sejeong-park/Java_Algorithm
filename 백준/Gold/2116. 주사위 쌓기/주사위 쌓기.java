
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *  1번 주사위는 마음대로 놓을 수 있음
 *  2번 주사위는 밑면이 1번주사위의 윗면과 같아야함.
 *  3. 사각 기둥에 4개의 긴 옆면이 있는데, 4개의 옆면 중 어느 한 면의 숫자의 합이 최대가 되도록 주사위를 쌓는다.
 *  결론. 한 옆면의 숫자의 합이 최댓값일 경우 리턴
 * */


public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int[][] diceList;
    static HashMap<Integer, Integer> map;
    static int N;
    static int resultMax;

    public static void pair() {
        map = new HashMap<>();
        // A : 0 B : 1 C : 2 D : 3 E : 4 F : 5
        map.put(0, 5);
        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 1);
        map.put(4, 2);
        map.put(5, 0);
    }

    public static void addStack(int bottomIndex, int diceIndex, int totalMax) {

        if (diceIndex == N) {
            resultMax = Math.max(resultMax, totalMax);
            return;
        }

        int topIndex = map.get(bottomIndex);

        // bottom 과 top을 제외한 옆면의 최대값
        int max = 0;
        for (int index = 0; index < 6; index ++) {
            if (bottomIndex == index || topIndex == index ) continue;
            max = Math.max(max, diceList[diceIndex][index]);
        }
        // System.out.println("bottomNum " + diceList[diceIndex][bottomIndex] + " topNum " + diceList[diceIndex][topIndex] + " max " + max);

        // 다음 diceIndex에서 top과 동일하게
        int nextBottomIndex = bottomIndex;
        if (diceIndex != N-1) { // 마지막 전까지만
            for (int index = 0; index < 6; index++) {
                if (diceList[diceIndex + 1][index] == diceList[diceIndex][topIndex]) {
                    nextBottomIndex = index;
                }
            }
        }
        addStack(nextBottomIndex, diceIndex + 1, totalMax + max);
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        pair(); // 주사위 양면
        diceList = new int[N][6]; // 주사위 목록
        for (int diceIdx = 0; diceIdx < N; diceIdx ++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < 6; idx ++) {
                diceList[diceIdx][idx] = Integer.parseInt(st.nextToken());
            }
        }
        // 6번 반복
        for (int idx = 0; idx < 6; idx ++) {
            addStack(idx, 0, 0);
        }
        System.out.println(resultMax);
    }
}
