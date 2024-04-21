import java.util.*;

/**
나만의 카카오 성격 유형 검사지
각 지표 중 2개씩 결정

- 매우 동의 & 매우 비동의 선택지 선택 -> 3점
- 동의 & 비동의 선택지 -> 2점
- 약간 동의 & 약간 비동의 -> 1점
- 모르겠음 -> 점수 없음

검사 결과는 모든 질문의 성격 유형 점수를 더하여 각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형
- 하나의 지표에서 각 성격 유형 점수가 같으면, 두 성격 유형 중 사전 순으로빠른 성격

*/

class Solution {
    
    // 카카오 성격
    static Map<String, Integer> kakaoMBTI; // 카카오 엠비티아이
    
    public void init() {
        kakaoMBTI = new HashMap<>();
        // 한글자 씩 Map에 넣기
        String kakaoMBTIList= "RTCFJMAN";
        for (String str : kakaoMBTIList.split("")) {
            kakaoMBTI.put(str, 0);
        }
    }
    
    public String solution(String[] survey, int[] choices) {

        StringBuilder sb = new StringBuilder();
        
        // KBTI 초기화
        init();
        
        // 검사
        for (int idx = 0; idx < survey.length; idx ++) {
            String[] compareList = survey[idx].split(""); // 두개로 나누기
            int point = choices[idx]; // 점수
            
            // 4 이하이면 0번 idx에게 반대로
            if (point < 4) {
                int addPoint = 4 - point;
                kakaoMBTI.put(compareList[0], kakaoMBTI.get(compareList[0]) + addPoint);
            }
            else if (point > 4) {
                int addPoint = point - 4;
                kakaoMBTI.put(compareList[1], kakaoMBTI.get(compareList[1]) + addPoint);
            }
        }
        
        // 유형 만들기
        // 두개가 같으면, 사전 순으로 더 빠른 것이 -> 유형
        String [] compareList = {"RT", "CF", "JM", "AN"};
        for (String compareStr : compareList) {
            String[] list = compareStr.split("");
            if (kakaoMBTI.get(list[0]) >= kakaoMBTI.get(list[1])) {
                sb.append(list[0]);
            }
            else {
                sb.append(list[1]);
            }
            
        }
        
        return sb.toString();
    }
}