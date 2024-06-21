import java.util.*;

/**
비내림차순 정렬 수열
1. 기존 수열에서 임의의 두 인덱스의 원소와 그 사이의 원소를 모두 포함하는 부분수열
2. 부분 수열의 합은 k
3. 합이 k 인 부분 수열이 여러 개인 경우 길이가 짧은 수열을 찾는다.
4. 길이가 짧은 수열이 여러 개인 경우 앞쪽에 나오는 수열을 찾는다.
*/

class Solution {
    
    static int min = Integer.MAX_VALUE;
    static int resultStart = -1;
    static int resultEnd = -1;
    static int [] sequence;
    static int target;

    public static void twoPointer() {
        
        int start = 0;
        int end = 1;
        int sum = sequence[0]; // 시작
        
        while(start < end) {
            if (sum == target) {
                int length = end - start;
                if (min > length) {
                    min = length;
                    resultStart = start;
                    resultEnd = end;
                }
                // start 값 이동
                sum -= sequence[start++];
            }
            else if (sum > target) {
                sum -= sequence[start++];
            }
            else if (end < sequence.length) {
                sum += sequence[end++];
            } else {
                break;
            }
        }
    
    } 
    
    public int[] solution(int[] sequence, int k) {
        this.sequence = sequence;
        this.target = k;
        
        twoPointer();

        int [] answer = {resultStart, resultEnd - 1};
        return answer;
    }
}