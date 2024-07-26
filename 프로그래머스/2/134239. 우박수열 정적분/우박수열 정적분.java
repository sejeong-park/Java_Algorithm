import java.util.*;

class Solution {
    
    ArrayList<Integer> lotharCollatz;
    
    public void calculateLotharCollatz(int k) {
        lotharCollatz = new ArrayList<>();
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k *= 3;
                k++;
            }
            lotharCollatz.add(k);
        }
    }
    
    
    public double[] solution(int k, int[][] ranges) {

        // STEP 1 : 로타르 콜라츠 구하기
        calculateLotharCollatz(k); 
        int count = lotharCollatz.size();
        
        // STEP 2 : 그래프 좌표 찾기
        double [] yArray = new double[count + 1];
        yArray[0] = k;
        for (int idx = 1; idx < count + 1; idx ++) {
            yArray[idx] = lotharCollatz.get(idx - 1);
        }
        
        // STEP 3 : 사다리꼴 넓이
        // 사다리꼴 넓이 = ((윗변 + 아랫변) * 높이) / 2 -> (idx - 1의 y축  + idx 의 y축 * 1 (한 간격)) / 2 
        double [] area = new double [count + 1];
        for (int idx = 1; idx < count + 1; idx ++) {
            area[idx] = (yArray[idx - 1] + yArray[idx]) / 2;
        }
        
        // STEP 4 : 누적합
        double [] prefix = new double[count + 1];
        prefix[1] = area[1];
        for (int idx = 2; idx < count + 1; idx ++) {
            prefix[idx] = area[idx] + prefix[idx - 1];
        }
        
        // STEP 5 : range 기준 결과 찾기
        double [] answer = new double[ranges.length];
        for (int idx = 0; idx < ranges.length; idx ++) {
            int start = ranges[idx][0];
            int end = count + ranges[idx][1]; // end 범위는 n - b
            
            // 주어진 구간의 시작 점이 끝보다 커서 유효하지 않은 구간 = -1 로 정의
            if (start <= end) {
                answer[idx] = prefix[end] - prefix[start];
            } else {
                answer[idx] = -1.0;
            }

        }
        
        return answer;
    }
}