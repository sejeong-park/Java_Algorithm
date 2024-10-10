import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int arraySize;
    static int [] array;

    static int max;
    static int min;

    public static void calculation(int plus, int minus, int multi, int divide, int index, int answer) {
        // 종료
        if (index == arraySize) {
            min = Math.min(min, answer);
            max = Math.max(max, answer);
            return;
        }
        if (plus > 0) {
            calculation(plus - 1, minus, multi, divide, index + 1, answer + array[index]);
        }
        if (minus > 0) {
            calculation(plus, minus - 1, multi, divide , index + 1,answer - array[index]);
        }
        if (multi > 0) {
            calculation(plus, minus, multi - 1, divide, index + 1, answer * array[index]);
        }
        if (divide > 0) {
            calculation(plus, minus, multi, divide - 1, index + 1,answer / array[index]);
        }
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        arraySize = Integer.parseInt(br.readLine().trim());
        array = new int[arraySize];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < arraySize; idx++) {
            array[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        int plus = Integer.parseInt(st.nextToken());
        int minus = Integer.parseInt(st.nextToken());
        int multi = Integer.parseInt(st.nextToken());
        int divide = Integer.parseInt(st.nextToken());

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        calculation(plus, minus, multi, divide, 1, array[0]);

        sb.append(max).append("\n");
        sb.append(min).append("\n");
        System.out.println(sb.toString());
    }
}
