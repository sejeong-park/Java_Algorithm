import java.util.*;
import java.awt.*;

class Solution {
    
    /**
    뉴스 클러스터링
    
    비슷비슷한 제목의 기사 -> 정작 피요한 기사 찾기 어려움
    블라인드 전형에 주목하는 기사 & 코딩테스트에 주목하는 기사로 나뉜다. -> 각각 보여주면, 공채 관련 기사를 찾아보는 사용자들에게 유용하다.
    
    자카드 유사도 J(A, B)는 두 집합의 교집합를 두 집합의 합집합 크기로 나눈 값으로 정의
    A = 1,2,3
    B = 2,3,4
    A n B = 2,3
    A u B = 1,2,3,4
    J(A, B) = 2 / 4 = 0.5
    집합 A, B 사이의 자카드 유사도 -> J(A, B) = 1
    */
    static ArrayList<String> strList1;
    static ArrayList<String> strList2;
    
    static ArrayList<String> union;
    static ArrayList<String> intersection;
    
    // 묶음 만들기
    public void makeMultiList(String str, ArrayList<String> list) {
        
        str = str.toLowerCase();
        
        for (int idx = 0; idx < str.length() - 1; idx ++) {
            char first = str.charAt(idx);
            char second = str.charAt(idx + 1); 
            
            // 문자만 포함한다.
            if ('a' <= first && first <= 'z' && 
               'a' <= second && second <= 'z') {
                list.add(first + "" + second);
            }            
        }
    }
    
    // intersection과 union 만들기
    public void makeSection() {
        
        // 순차 정렬
        Collections.sort(strList1);
        Collections.sort(strList2);
        
        // 교집합 
        for (String str : strList1) {
            if (strList2.remove(str)) {
                // 둘 다 가지고 있다면,
                intersection.add(str);
            }
            // 그렇지 않다면 합집합
            union.add(str);
        }
        
        for (String str : strList2) {
            union.add(str);
        }
    }
    
    // 계산하기
    public double calculate() {
        double intersectionPer = intersection.size();
        double unionPer = union.size();
    
        if (unionPer == 0) {
            return 1;            
        } else {
            return intersectionPer / unionPer;
        }
    }
    
    public int solution(String str1, String str2) {
        int answer = 0;
    
        // 초기화
        strList1 = new ArrayList<>();
        strList2 = new ArrayList<>();
        union = new ArrayList<>();
        intersection = new ArrayList<>();
        
        // 리스트로 만들어주기
        makeMultiList(str1, strList1);
        makeMultiList(str2, strList2);
        
        // 교집합, 합집합 구하기
        makeSection();   
        
        // 지수 구하기
        double result =  calculate();
        
        return (int) (result * 65536);
    }
}