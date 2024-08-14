import java.util.*;

class Solution {
    
    static int count;
    static int [] array;
    static int target;
    public static void dfs(int index, int result) {
        
        // 종료 조건
        if (index == array.length) {
            if (target == result) {
                count ++;
            }
            return;
        }
        
        dfs(index + 1, result + array[index]); // 더하기
        dfs(index + 1, result - array[index]); // 빼기
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        
        count = 0;
        this.target = target;
        array = numbers.clone();
        
        dfs(0, 0);
        
        return count;
    }
}