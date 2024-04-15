import java.util.*;
import java.awt.*;

/**
뉴스 클러스터링
- 자카드 유사도 J(A, B) : 두 집합의 교집합 크기를 두 집합의 합집합의 크기로 나눈 값으로 정의
A = {1, 2, 3}
B = {2, 3, 4}
교집합 = {2,3} 
합집합 = {1, 2, 3, 4}
자카드 유사도 = 2/4
*/

class Solution {
    
    // 문자열
    public ArrayList<String> changeList(String str) {
        
        ArrayList<String> arrayList = new ArrayList<>();
        for (int idx = 0; idx < str.length()-1; idx ++) {
            String parseStr = str.substring(idx, idx + 2);
            char first = parseStr.charAt(0);
            char second = parseStr.charAt(1);
            
            if ('a' <= first && first <= 'z' && 'a' <= second && second <= 'z') {
                arrayList.add(parseStr);
            }
        }
        
        return arrayList;
    }
    
    public int solution(String str1, String str2) {
        int answer = 65536;
        
        // 두 문자열을 소문자로 통일
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        ArrayList<String> strList1 = changeList(str1);
        ArrayList<String> strList2 = changeList(str2);
        
        // 순차적으로 정렬하기
        Collections.sort(strList1);
        Collections.sort(strList2);
        
        // 합집합과 교집합 구하기
        ArrayList<String> intersection = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();
        
        // 첫번째 리스트부터 탐색
        for (String str : strList1) {
            // 만약 리스트 삭제 가능하다면
            if (strList2.remove(str)) {
                intersection.add(str);
            }
            union.add(str); // 삭제 안된다면 원소 추가
        }
        // 두번째 리스트에 삭제 되지 않는 값들 순회
        for (String str : strList2) {
            union.add(str);
        }
        
        // 결과 구하기
        if (union.size() == 0 && intersection.size() == 0) {
            return answer;
        }else{
            double cal = (double) intersection.size() / union.size();
            return (int)((double) answer * cal);
        }
    }
}