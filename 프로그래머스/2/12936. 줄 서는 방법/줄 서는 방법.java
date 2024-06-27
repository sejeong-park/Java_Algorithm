import java.util.ArrayList;
import java.util.Arrays;
class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        ArrayList<Integer> list = new ArrayList<>();
        long factorial = 1;
        int idx = 0;

        // 팩토리얼과 사람 리스트 구하기
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            list.add(i);
        }

        // 인덱스를 맞추기 위해 k--
        k--;

        while (n > 0) {
            // 각 자리에 자리에 들어갈 경우의 수
            factorial /= n;
            // 자리 숫자 결정
            int val = (int) (k / factorial);
            // 정답 배열에 숫자 삽입
            answer[idx] = list.get(val);
            list.remove(val);
            
            // 다음 자리수를 구하기 위해 k값 변경
            k %= factorial;
            idx++;
            n--;
        }
        return answer;
    }
}