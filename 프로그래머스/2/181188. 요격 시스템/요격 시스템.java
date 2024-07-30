import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int count = 0;
        
        // end 기준으로 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        int check = 0; // 마지막 시간 갱신
        for (int [] target : targets) {
            // start 기준으로 비교
            if (check <= target[0]) {
                count += 1;
                check = target[1]; //end
            }
        }
        
        return count;
    }
}