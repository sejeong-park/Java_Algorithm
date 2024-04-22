
import java.util.*;

/*
신입 사원 무지는 "게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템 개발"

1. 각 유저는 한번에 한 명의 유저를 신고할 수 있다.
    - 신고 횟수에 제한 없음 -> 서로 다른 유저를 계속해서 신고할 수 있다.
    - 한 유저를 여러 번 신고할 수 있지만, 동일한 유저에 대한 신고 횟수는 1회 처리
2. k번 이상 신고된 유저는 게시판 이용이 정지 & 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송
    - 유저 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면 정지 메일을 발송

*/

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
    
        Map<String, Integer> reportId = new HashMap<>(); 
        for (int idx = 0; idx < id_list.length; idx ++) {
            reportId.put(id_list[idx], idx); // index 기록
        }
        
        // 신고의 중복 제거
        Set<String> set = new HashSet<>();
        for (String str : report) {
            set.add(str);
        }
        List<String> list = new ArrayList<>(set);
        
        // report  List
        int [] reportCnt = new int[id_list.length];
        boolean [][] reportList = new boolean[id_list.length][id_list.length]; 
        for (int idx = 0; idx < list.size(); idx ++) {
            String [] strList = list.get(idx).split(" ");
            int fromIdx = reportId.get(strList[0]);
            int toIdx = reportId.get(strList[1]);
            
            // 신고 수 세기
            reportCnt[toIdx] ++; // 이용 정지된 유저
            reportList[fromIdx][toIdx] = true; // 참
        }
        
        // 순차적으로 카운트
        int [] answer = new int[id_list.length];
        for (int idx = 0; idx < id_list.length; idx++) {
            String name = id_list[idx];
            int count = 0;
            for (int reportIdx = 0; reportIdx < id_list.length; reportIdx ++) {
                // 이용 정지된 유저가 k개 이상 넘긴다면?
                if(reportList[idx][reportIdx] && reportCnt[reportIdx] >= k) {
                    count ++;
                }
            }
            answer[idx] = count;
        }
             
        return answer;
    }
}