import java.util.*;
import java.awt.*;

class Solution {
    
    /**
    선물하기 기능 -> 축하 선물 : 다음 달에 누가 선물을 많이 받을 지 예측
    
    [조건 1.]
    - 두 사람이 선물 주고 받은 기록 
    - 두 사람 사이에 더 많은 선물을 준 사람이 다음달에 선물 하나 받는다.
    - A -> B 에게 5번  / B가 A에게 3번 -> A -> B에게 하나 받는다.
    [조건 2.]
    -  두사람이 선물 주고 받은 기록이 없거나 같으면, 선물 지수가 더 큰 사람이 선물지수가 더 작은 사람에게 선물을 하나 받는다.
    1. 선물 지수 = 이번달까지 자신의 친구들에게 준 선물의 수 - 받은 선물의 수
    2. 만약 선물지수도 같다면, 다음달에 선물을 주고 받지 않음
    
    [결과]
    선물을 가장 많이 받을 친구가 받을 선물의 수
    */
    
    
    static String[] friends;
    
    static int [][] giftTable;
    static int [][] resultTable;
    static HashMap<String, Integer> friendsIdx; 
    
    // friends index 번호 지정
    public void init() {
        friendsIdx = new HashMap<>();
        for (int idx = 0; idx < friends.length; idx ++) {
            friendsIdx.put(friends[idx], idx);
        }
    }
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        this.friends = friends; 
        init();
        
        // giftTable 만들기
        giftTable = new int[friends.length][friends.length];
        for (int idx = 0; idx < gifts.length; idx ++ ){
            String[] line = gifts[idx].split(" "); 
            int fromIdx = friendsIdx.get(line[0]);
            int toIdx = friendsIdx.get(line[1]);
            giftTable[fromIdx][toIdx] += 1;
        }
        
        // 준선물 / 받은 선물 구하기
        resultTable = new int[friends.length][3];
        for (int fromIdx = 0; fromIdx < friends.length; fromIdx ++) {
            for (int toIdx = 0; toIdx < friends.length; toIdx ++) {
                resultTable[fromIdx][0] += giftTable[fromIdx][toIdx];
                resultTable[fromIdx][1] += giftTable[toIdx][fromIdx];
            }
            resultTable[fromIdx][2] = resultTable[fromIdx][0] - resultTable[fromIdx][1];
        }
        
        
        // 결과 구하기
        int[] nextMonth = new int[friends.length];
        for (int fromIdx = 0; fromIdx < friends.length; fromIdx ++){
            for (int toIdx = 0; toIdx < friends.length; toIdx ++){
                // 너가 나보다 선물을 많이 주면, 나에게 선물 하나를 받는다.
                if (giftTable[fromIdx][toIdx] < giftTable[toIdx][fromIdx]){
                    nextMonth[toIdx] += 1;
                }else if (giftTable[fromIdx][toIdx] > giftTable[toIdx][fromIdx]) {
                    nextMonth[fromIdx] += 1;
                }else {
                    // 만약 선물 기록이 없거나, 같다면, 선물 지수가 크면 선물을 받는다.
                    if (resultTable[fromIdx][2] < resultTable[toIdx][2]) {
                        nextMonth[toIdx] +=1;
                    } else if (resultTable[toIdx][2] > resultTable[fromIdx][2]) {
                        nextMonth[fromIdx] += 1;
                    }   
                }
            }
        }
        
        
        for (int idx = 0; idx < friends.length; idx ++ ){
            System.out.println(nextMonth[idx]);
        }
        
        
        for (int idx =0 ; idx < friends.length; idx++) {
            for (int jdx = 0; jdx < 3; jdx ++) {
                System.out.print(resultTable[idx][jdx] + " ");
            }
            System.out.println();
        }
        
    
        
        
        
        
        return answer;
    }
}