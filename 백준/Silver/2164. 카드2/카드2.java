

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
* author : sejeong-park
* BOJ_2164_카드 2
*
* */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // Queue
        Queue<Integer> queue = new LinkedList<>();

        // queue에 넣기
        for (int number = 1; number <= N; number++){
            queue.offer(number);
        }

        // 한개를 버리고, 뒤에 것을 맨 뒤로 넣는다.
        // 마지막 한개가 남을 때까지 반복
        // while queue의 크기가 1보다 클때까지만 반복
        while (queue.size()>1){
            queue.poll(); // 한 개 빼기
            queue.offer(queue.poll());
        }

        // 마지막에 남는 카드를 구하자
        System.out.println(queue.peek());
    }
}
