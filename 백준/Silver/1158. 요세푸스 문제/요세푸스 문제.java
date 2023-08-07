

/*
* BOJ_1158 요세푸스 문제
*
* 1번 부터 N번까지 N명의 사람이 원을 이루면서 앉아있다.
* 이제 순서대로 K번째 사람을 제거된다.
* 한사람을 제거하면 남은사람들로 이루어진 원을 따라 이 과정을 계속한다.
* 제거되는 순서를 출력한다 !
* */


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // 입출력
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int K = sc.nextInt();

        // DequeArray 사용해보자 (Python deque)
        Deque<Integer> deque = new ArrayDeque<>();
        // deque완성하기
        for(int number = 1; number <= N; number++){
            deque.add(number);
        }

        sb.append("<"); // 입력
        // queue 가 존재 하는 동안
        while(!deque.isEmpty()){
            for(int count = 1; count < K; count++){
                deque.add(deque.poll()); // 빼주기
            }

            // 마지막 구분해주기
            if (deque.size() <= 1){
                sb.append(deque.poll());
            }else {
                sb.append(deque.poll()).append(", "); // deque에서 뽑은 거 넣기
            }
        }

        sb.append(">"); // 마무리
        System.out.println(sb); // 출력
    }

}
