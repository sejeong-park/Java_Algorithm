import java.util.*;
/**
예시 : [10, 5, 6, 7, 8, 9, 1, 2, 3, 4] 

a 박스 : 1번 -> 10번 -> 4번 -> 8번 : [1, 10, 4, 7]
b 박스 : 2번 -> 5번 -> 8번 -> 2번 : [2, 5, 8]
c 박스 : 3번 -> 6번 -> 9번 -> 3번 : [3, 6, 9]
d 박스 : 4번 -> 7번 -> 1번 -> 10번 -> 4번 : [4, 7, 1, 10] (중복)
-> 4, 3, 3 구성
-> 4 * 3= 12
*/
class Solution {
    
    static int[] cards;
    static int size;
    static boolean [] visited;
    static PriorityQueue<Integer> queue; // 최대 개수를 나타냄
    
    // count는 몇개가 쌓였는지
    public static void dfs(int num, int count) {
        // 열었던 상자라면
        if (visited[num]) {
            queue.add(count);
            return;
        }
        
        visited[num] = true; 
        dfs(cards[num] - 1, count + 1);
    }
    
    public int solution(int[] cards) {
        int answer = 0;
        
        this.cards = cards;
        size = cards.length;
        visited = new boolean[size];
        
        queue = new PriorityQueue<>(Collections.reverseOrder()); // 최대 힙
        // 열지 않ㄴ은 상자라면
        for (int idx = 0; idx < size; idx ++) {
            if (!visited[idx]) {
                dfs(idx, 0); // dfs 
            }
        }
        
        // queue의 크기가 1이면, 1번 상자 그룹밖에 없음
        if (queue.size() != 1) {
            answer = queue.poll() * queue.poll();
        }
         
        return answer;
    }
}