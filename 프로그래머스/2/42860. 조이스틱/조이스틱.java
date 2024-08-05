class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int length = name.length();
        
        int index; // 다음 값을 확인할 때 사용
        int move = length - 1; // 좌우 움직임 수 체크
        
        for (int idx = 0; idx < name.length(); idx ++) {
            answer += Math.min(name.charAt(idx) - 'A', 'Z' - name.charAt(idx) + 1);
            
            index = idx + 1;
            
            while(index < length && name.charAt(index) == 'A') {
                index ++;
            }
            
            move = Math.min(move, idx * 2 + length - index);
            move = Math.min(move, (length - index) * 2 + idx);
            
        }
        
        
        return answer + move;
    }
}