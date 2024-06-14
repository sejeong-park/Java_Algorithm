class Solution {
    
    static char [] dict = {'A', 'E', 'I', 'O', 'U'};
    static String target;
    static int count;
    static boolean found = false;

    public static void find(String word) {
        
        // 종료조건 = 같은 것 찾기
        if (word.equals(target)) {
            found = true;
            return;
        }
        
        // 범위 지나면 종료
        if (word.length() == 5) {
            return;
        }
        
        for (char c : dict) {
            count ++;
            find(word + c);
            // 이미 찾았으면 종료
            if (found) return;
        }
        
        
    }
    
    public int solution(String word) {
        int answer = 0;
        target = word;
        count = 0;
        
        find("");
        
        return count;
    }
}