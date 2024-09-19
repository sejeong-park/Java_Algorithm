
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 시작 : 12시 30분 !
 * 다리 N개 트럭이 건너감
 * 트럭 순서 바꿀 수 없음
 * 트럭 무게 서로 같지 않을
 * 다리에 w대의 트럭만 올라가기 가능
 * 트럭이 다리를 모두 건너는 최단 시간
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int truckCnt, bridgeWeight, maxWeight;
    static int time;
    static Deque<Integer> truck = new ArrayDeque<>();
    static Deque<Integer> bridge = new ArrayDeque<>();

    public static void go() {
        time = 0;

        int getBridge = 0; // 다리에 있는 트럭이 한대씩 차에서 내려옴
        while(!bridge.isEmpty()) {
            time ++;
            getBridge -= bridge.poll(); // 한대씩 다리에서 내려옴
            if (!truck.isEmpty()) {
                if (truck.peek() + getBridge <= maxWeight) {
                    getBridge += truck.peek();
                    bridge.add(truck.poll()); // 다리에 한대씩 올라감
                } else {
                    bridge.add(0); // 트럭큐에 트럭이 존재하지만, 다리로 못올라갈 경우
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        truckCnt = Integer.parseInt(st.nextToken());
        bridgeWeight = Integer.parseInt(st.nextToken());
        maxWeight = Integer.parseInt(st.nextToken());

        // 트럭의 무게를 큐에 담는다.
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < truckCnt; idx ++) {
            truck.add(Integer.parseInt(st.nextToken()));
        }

        // 현재 다리에 올라와 있는 트럭의 무게를 넣는다.
        for (int idx = 0; idx < bridgeWeight; idx ++) {
            bridge.add(0);
        }

        go();

        System.out.println(time);
    }

}
