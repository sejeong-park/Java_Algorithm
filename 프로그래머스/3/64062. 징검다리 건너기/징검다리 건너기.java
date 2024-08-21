import java.util.*;


class Solution {
    
    static int [] stones;
    static int k;
    public static boolean check(int mid) {
        int count = 0;
        for (int idx = 0; idx < stones.length; idx ++) {
            if (stones[idx] - mid < 0) {
                count ++;
            } else {
                count = 0;
            }
            if (k == count) {
                return false;
            }
        }
        
        return true;
    }
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        this.stones = stones;
        this.k = k;
        // 그 사이에 몇명 갈 수 있는지?
        int min = 1;
        int max = 200000000;
        while(min <= max) {
            
            int mid = (min + max) / 2;
            
            // 성공하면
            if (check(mid)) {
                answer = Math.max(mid, answer);
                min = mid + 1;
            }
            else {
                max = mid - 1;
            }   
        }
        return answer;
    }
}