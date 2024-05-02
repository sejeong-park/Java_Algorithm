import java.util.*;

/**
파일명 정렬
1. HEAD 기준으로 사전 순 정렬 -> 문자열 대소문자 구분 X
2. HEAD 일치하다면, number의 숫자 순으로 정렬 -> 앞의 0 무시
3. HEAD 와 number 같다면, 원래 입력에 주어진 순서 유지

코멘트
- 파이썬으로 되게 쉽게 풀었었는데, 자바로 풀려면 공부가 필요한거 같다.
- 다른 사례들 잘 알아보기 ㅜㅜ
- 자바 넘어렵다 ㅜ


*/



class Solution {
    
    static String[][] sliceFileNameList;
    
    // 슬라이싱 
    public void sliceFileName(int index, String fileName) {
        
        boolean first = true;  // 처음 index 찾기
        int size = 0;
        int startNumberIdx = -1; // NUMBER 의 시작 위치
        int endNumberIdx = -1; // NUMBER의 종료 위치
        for (int idx = 0; idx < fileName.length() ; idx ++) {
            // 숫자를 찾는다.
            if (Character.isDigit(fileName.charAt(idx))) {
                if (first) {
                    startNumberIdx = idx;
                    first = false;
                }
                size ++;
            }
            // 숫자가 아니고, 이미 첫번째 num Idx 찾으면 종료
            else {
                if (!first) break;
            }
        }
        
        endNumberIdx = startNumberIdx + size - 1;
        
        sliceFileNameList[index][0] = fileName.substring(0, startNumberIdx); // HEAD
        sliceFileNameList[index][1] = fileName.substring(startNumberIdx, endNumberIdx + 1); // NUMBER
        sliceFileNameList[index][2] = fileName.substring(endNumberIdx + 1); // TAIL

    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        // 나누는 fileName 저장
        sliceFileNameList = new String[files.length][3];
        
        for (int idx = 0; idx < files.length; idx ++ ){
            sliceFileName(idx, files[idx]);
        }
        
        // 정렬
        Arrays.sort(sliceFileNameList, (o1, o2) -> {
            if (o1[0].toLowerCase().compareTo(o2[0].toLowerCase(Locale.ROOT)) > 0) {
                return 1;
            }
            else if (o1[0].toLowerCase().compareTo(o2[0].toLowerCase(Locale.ROOT)) < 0) {
                return -1;
            }
            else {
                if (Integer.parseInt(o1[1]) > Integer.parseInt(o2[1])) {
                    return 1;
                }
                else if (Integer.parseInt(o1[1]) < Integer.parseInt(o2[1])) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        });
        
        
        for (int idx = 0; idx < files.length; idx ++) {
            answer[idx] = sliceFileNameList[idx][0] + sliceFileNameList[idx][1] + sliceFileNameList[idx][2];
        }
        
        
        return answer;
    }
}