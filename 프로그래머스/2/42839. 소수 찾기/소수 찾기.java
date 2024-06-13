import java.util.*;

/**
한자리 숫자가 적힌 조각 흩어짐 -> 흩어진 종이 조각 붙여 소수 몇 개 만들 수 잇나?
*/

class Solution {
    
    static char [] numberList;
    static boolean [] visited;
    static HashSet<Integer> set = new HashSet<>(); // 중복 ㄱ밧 제거
    
    
    public static boolean isPrime(int num) {
        if (num == 1 || num == 0) {
            return false;
        }
        // 곱 확인
        for (int idx = 2; idx * idx <= num; idx ++) {
            if (num % idx == 0){
                // num이 idx의 배수이면, 소수가 아니다.
                return false;
            }
        }
        return true;
    }
    
    // str 합친거 / index visited 깊이
    public static void permutation(String str, int depth) {
        
        
        // 1.기저 조건
        if (str != "") {
            int num = Integer.parseInt(str); // 숫자로 변환
            // 소수 찾기
            if (isPrime(num)) {
                set.add(num);
            }
            
        }
        
        // 모두 탐색함
        if (depth == numberList.length) {
            return;
        }
        
        for (int idx = 0; idx < numberList.length; idx ++) {
            if (visited[idx]) continue;
            visited[idx] = true;
            permutation(str + numberList[idx], depth + 1); // 다음거부터
            visited[idx] = false;
        }
        
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        numberList = new char [numbers.length()];
        visited = new boolean[numbers.length()];
        for (int idx = 0; idx < numbers.length(); idx ++) {
            numberList[idx] = numbers.charAt(idx);
        }
        
        set = new HashSet<>();
        permutation("", 0); // 순열
        System.out.println(set.toString());
        return set.size();
    }
}