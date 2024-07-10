import java.util.*;
/**
광물 캐기
다이아몬드 곡괭이, 철 곡괭이, 돌 곡괭이를 0 ~ 5개 가지고 있음
곡괭이 광물 캘 때 피로도 소모

최소한의 피로도 광물을 캐려고 한다.
1. 사용할 수 있는 곡괭이 중 아무거나 하나를 선택해 광물을 캔다.
2. 한번 사용한 곡괭이는 사용할 수 없을 때까지 사용
3. 광물은 주어진 순서대로만 캘 수 있다.
4. 광산에 모든 광물을 캐거나 더 사용할 곡괭이가 없을 때까지 광물을 캔다.

곡괭이 하나를 선택해 광물 5개를 연속으로 캐고, 다음 곡괭이를 선택해 광물 5개를 연속으로 캐는 과정 반복
더 사용할 곡괭이가 없거나 광산에 있는 모든 광물 캘때까지 과정 반복
*/

class Solution {
    
    // 곡괭이 개수
    static int [] picks;
    
    // 광물의 피로드
    static int [][] stress = {{1,1,1}, {5,1,1}, {25,5,1}};
    
    static int minStress;
    
    static final int DIA = 0;
    static final int IRON = 1;
    static final int STONE = 2;
    
    static ArrayList<int []> array = new ArrayList<>();
    
    boolean check() {
        int cnt = 3;
        for (int idx = 0; idx < 3; idx ++) {
            if (picks[idx] == 0) {
                cnt -=1;
            }
        }
        if (cnt == 0) {
            return false;
        }else {
            return true;
        }
    }
    
    void dfs(int depth, int totalStress) {
        
        // 모든 탐색 시 종료
        if (depth == array.size()) {
            minStress = Math.min(minStress, totalStress);
            return;
        }
        
        // 곡괭이를 모두 소진하면 종료
        if (!check()) {
            minStress = Math.min(minStress, totalStress);
            return;
        }
        
        // 곡괭이 별 탐색
        for (int idx = 0; idx < 3; idx ++) {
            int [] stressCnt = array.get(depth); // 곡괭이 별 스트레스 정도
            // 만약 곡괭이가 남아있다면
            if (picks[idx] > 0) {
                picks[idx] -= 1;
                totalStress += stressCnt[idx];
                dfs(depth + 1, totalStress);
                totalStress -= stressCnt[idx];
                picks[idx] += 1;
            }
        }
        return;
    }
    
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        this.picks = picks;
        minStress = Integer.MAX_VALUE;
        // 곡괭이는 5개기준으로 소요된다.
        for (int idx = 0; idx < minerals.length; idx += 5) {
            int diaStress = 0, ironStress = 0, stoneStress = 0; 
            for (int mineralIdx = idx; mineralIdx < idx + 5; mineralIdx ++) {
                
                if (mineralIdx == minerals.length) {
                    break;
                }
                
                // 광물 확인
                int mineral = -1; 
                if (minerals[mineralIdx].equals("diamond")) {
                    mineral = 0;
                } else if (minerals[mineralIdx].equals("iron")) {
                    mineral = 1;
                } else {
                    mineral = 2;
                }
                
                // 광물에 스트레스 지수 추가
                diaStress += stress[DIA][mineral];
                ironStress += stress[IRON][mineral];
                stoneStress += stress[STONE][mineral];  
            }
            // System.out.println(diaStress + " " + ironStress + " " + stoneStress);
            array.add(new int [] {diaStress, ironStress, stoneStress});
        }
        
        System.out.println("size :: " + array.size());

        dfs(0,0);
        
        return minStress;
    }
}