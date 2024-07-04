import java.util.*;

/**
1. 징검다리는 일렬로 놓여 있고, 각 징검다리의 디딤돌에는 모두 숫자가 적혀 있다. -> 디딤돌의 숫자는 한번 밟을 때마다 1씩 줄어든다.
2. 디딤돌의 숫자가 0이 되면, 더 이상 밟을 수 없으며, 이때는 그 다음 디딤돌로 한번에 여러 칸을 건너 뛸 수 있다.
3. 단, 다음으로 밟을 수 있는 디딤돌이 여러 개인 경우 무조건 가장 가까운 디딤돌로만 건너뛸 수 있다.
*/

class Solution {
    
    static int [] stones;
    static int k;
    
    // 사이에 몇명 건널 수 있는지? -> mid는 사람 수 
    public static boolean check(int mid) {
        int count = 0; // 몇명?
        for (int idx = 0; idx < stones.length; idx ++) {
            if (stones[idx] - mid < 0) {
                count ++;
            } else {
                count = 0; // 초기화
            }
            // 몇번 이상?
            if (k == count) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        int min = 1;
        int max = 200000000;
        this.stones = stones;
        this.k = k;
        
        while(min <= max) {
            int mid = (min + max) / 2;
            // 건널 수 있음 -> 단, 학생이 max일 경우를 구해야 한다.
            if (check(mid)) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        

        return answer;
    }
}