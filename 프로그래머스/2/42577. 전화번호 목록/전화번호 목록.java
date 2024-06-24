/**
전화번호부에 적힌 전화번호 -> 한 번호가 다른 번호의 접두어인 경우가 있는 지 확인
phone_book이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우 -> false / 그렇지 않으면 true
문제 풀이
1. phone_book을 string 순서대로 정렬한다.
2. startswith가 동일하다면, false 이다.
*/
import java.util.*;
import java.io.*;

class Solution {
    
    static String[] phone_book;
    
    public boolean isValid(){
        for (int idx = 0; idx < phone_book.length -1 ; idx++){
            if (phone_book[idx + 1].startsWith(phone_book[idx])) //  다음것과 비교했을 때 중복 여부 확인
                return false;
        } 
        return true;
    }
    
    
    public boolean solution(String[] phone_book) {
        this.phone_book = phone_book; // 매개변수에 넣는다.        
        Arrays.sort(phone_book); // phone_book을 순차적으로 정렬한다.
        return isValid();
    }
}