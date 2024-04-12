import java.util.*;


class Solution {
    
    /**
    개인 정보 수집 유효기간
    
    - 고객의 약관동의 얻어서 수집된 1~n -> n개
    - 각 개인정보가 어떤 약관으로 수집됐는지 알고 있음 & 수집된 개인정보는 유효기간 전까지 보관 가능 && 유효기간 지났을 시 파기
    - A 라는 약관 유효기간 12달 / 
    - 모든 달은 28일까지 존재
    */
    
    public static Map<String, Integer> termMap;
    
    public int [] sepDate(String date) {

        
        String [] dateList = date.split("\\.");
        
        int year = Integer.parseInt(dateList[0]);
        int month = Integer.parseInt(dateList[1]);
        int day = Integer.parseInt(dateList[2]);
        
        return new int[] {year, month, day};
    }
    
    public int countDate(int[] dateList) {
        int date = 0;
        date += dateList[0] * 12 * 28;
        date += dateList[1] * 28;
        date += dateList[2];
        return date;
    }
    
    public boolean checkPrivacies(String today, String target, String type){
        
        int typeMonth = termMap.get(type);
        
        int todayDayCnt = countDate(sepDate(today));
        int targetDayCnt = countDate(sepDate(target));
        targetDayCnt += typeMonth * 28 - 1;
        
        if (todayDayCnt > targetDayCnt) {
            return true;
        }else {
            return false;
        }
    }
    
    
    public int[] solution(String today, String[] terms, String[] privacies) {

        termMap = new HashMap<>(); // 초기화
        
        // term 분할
        for (int idx = 0; idx < terms.length; idx ++) {
            String [] line = terms[idx].split(" ");
            termMap.put(line[0], Integer.parseInt(line[1]));
        }
        
        // 정책 확인
        ArrayList<Integer> endPeriod = new ArrayList<>();
        for (int idx = 0; idx < privacies.length; idx++) {
            String [] line = privacies[idx].split(" "); 
            boolean result = checkPrivacies(today, line[0], line[1]);
            if (result) {
                endPeriod.add(idx + 1);
            }
        }
        
        // 변환
        int [] answer = new int[endPeriod.size()];
        for (int idx = 0; idx < endPeriod.size(); idx ++ ) {
            answer[idx] = endPeriod.get(idx);
        }        
    
        return answer;
    }
}