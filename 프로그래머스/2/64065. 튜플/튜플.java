
import java.util.*;
/**
셀 수 있는 수량의 순서있는 열거 & 어떤 순서 따르는 요소의 모음 - 튜플
튜플 : n 개의 요소를 가진 튜플 = n- 튜플
[튜플의 특징]
1. 중복된 원소 있을 수 있다.
2. 원소에 정해진 순서가 있으며, 원소의 순서가 다르면 서로 다른 튜플
3. 튜플의 원소 개수는 유한하다.

-> 원소의 개수가 n개이고, 중복되는 원소가 없는 튜플이 주어질 때 집합기호로 표현한다.
주어진 튜플들로 부모 튜플 구하기
*/

class Solution {
    public int[] solution(String s) {
        
        String str = s.substring(2, s.length()-2);
        String [] strTotalTuple = str.split("\\}\\,\\{");
        
        // 길이의 순서 대로 정렬하기 -> 비교하는 것 외우기!
        Arrays.sort(strTotalTuple, Comparator.comparing(String::length));
        // System.out.println(Arrays.toString(strTotalTuple));
        
        ArrayList<Integer> list = new ArrayList<>();
        for (String strTuple : strTotalTuple) {
            String [] strTupleList = strTuple.split("\\,");
            for (String strTupleItem : strTupleList) {
                int item = Integer.parseInt(strTupleItem); // str to int
                if (!list.contains(item)) { // set에 없다면 추가
                    list.add(item);
                }
            }            
        }
        // answer로 변환
        int [] answer = new int[list.size()];
        for (int idx = 0; idx < list.size(); idx ++) {
            answer[idx] = list.get(idx);
        }
        return answer;
    }
}