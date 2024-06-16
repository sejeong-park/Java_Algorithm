import java.util.*;

/**
프로그래머스 - 기능 개선 작업 -> 100%일 때 서비스 반영 가능
각 기능의 개발 속도 다름 -> 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발 가능
이 때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포
먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 
개발 속도가 적힌 정수배열 speed가 주어질 때 각 배포마다 몇개의 기능 배포?
*/

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        
        int [] days = new int [progresses.length];
        for (int idx = 0; idx < progresses.length; idx ++) {
            days[idx] = (int) Math.ceil((100.0 - progresses[idx]) / speeds[idx]);
        } 
        // System.out.println(Arrays.toString(days));
        
        int [] count = new int [days.length];
        int tmpIdx = 0;
        for (int idx = 0; idx < days.length; idx ++) {
            if (days[tmpIdx] < days[idx]) {
                tmpIdx = idx;
            }
            count[tmpIdx] ++;
        }
        
        // System.out.println(Arrays.toString(count));
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int idx = 0; idx < days.length; idx ++) {
            if (count[idx] > 0) {
                list.add(count[idx]);
            }
        }
    
        
        int [] answer = new int [list.size()];
        for (int idx = 0; idx < list.size(); idx ++) {
            answer[idx] = list.get(idx); 
        }
        return answer;
    }
}