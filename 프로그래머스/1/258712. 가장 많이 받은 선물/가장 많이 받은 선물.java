import java.util.*;
import java.awt.*;

class Solution {
    
    /**
    이번 달 까지 선물을 주고 받은 기록
    결론 : 다음 달 누가 선물을 많이 받을 지 예측해야한다.
    
    
    [다음 달 선물을 받는 조건]
    1. 이번달까지 두사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 더 받는다.
    2. 만약 선물을 주고 받은 기록이 하나도 없거나, 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받는다. 
    
    */
    
    static String[] friends;
    static String[] gifts;
    
    
    static int friendsCnt;
    static HashMap<String, Integer> friendsIdx;
    static int[][] giftTable; // 이번달까지 선물을 주고 받은 내역
    static int[] giftPoint; // 선물 지수구하기
    static int[] nextMonthGift;
    
    
    public void init() {
        
        // STEP 1. friends Index 지정하기
        friendsIdx = new HashMap<>();
        for (int idx = 0; idx < friendsCnt; idx++) {
            friendsIdx.put(friends[idx], idx);
        }
        
        // STEP 2. gift Table 만들기
        giftTable = new int[friendsCnt][friendsCnt];
        for (int idx = 0; idx < gifts.length; idx ++) {
            String [] line = gifts[idx].split(" ");
            int fromIdx = friendsIdx.get(line[0]);
            int toIdx = friendsIdx.get(line[1]);
            giftTable[fromIdx][toIdx] += 1;
        }
    }
    
    public void getGiftPoint(){
        
        // 선물 지수를 구하자.
        // 선물 지수 = 이번달까지 자신이 친구들에게 준 선물 수 - 받은 선물 수
        giftPoint = new int[friendsCnt];
        for (int fromIdx = 0; fromIdx < friendsCnt; fromIdx ++){
            int givePoint = 0;
            int getPoint = 0;
            for (int toIdx = 0; toIdx < friendsCnt; toIdx ++){
                givePoint += giftTable[fromIdx][toIdx]; // 준 선물
                getPoint += giftTable[toIdx][fromIdx]; // 받은 선물
            }
            // 선물 지수
            giftPoint[fromIdx] = givePoint - getPoint;
        }
    }
    
    public void getNextMonthGift(){
        // 다음달 받을 선물 예측하기 -> 양방향으로 하면, 중복이 되므로, 단방향으로 한번만 고려한다.
        // 조건 1. 두 사람이 선물을 주고 받았다면, 이번달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음달에 선물을 하나 받는다.
        // 조건 2. 선물을 주고 받은 기록이 하나도 없거나, 주고 받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 더 선물 받는다.
        
        nextMonthGift = new int[friendsCnt];
        for (int fromIdx =0 ; fromIdx < friendsCnt; fromIdx++) {
            for (int toIdx = 0; toIdx < friendsCnt; toIdx++) {
                if (giftTable[fromIdx][toIdx] == giftTable[toIdx][fromIdx]) {
                    // 선물을 주고 받은 기록이 없거나, 주고 받은 수가 같다면
                // 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물 하나 준다.
                    if (giftPoint[fromIdx] < giftPoint[toIdx]) {
                        nextMonthGift[toIdx] += 1;
                    }
                    
                }
                else if (giftTable[fromIdx][toIdx] < giftTable[toIdx][fromIdx]) {
                    nextMonthGift[toIdx] += 1;
                }
            }
            
        }
        
    }
    
    
    
    public int solution(String[] friends, String[] gifts) {
        this.friends = friends;
        this.gifts = gifts;
        this.friendsCnt = friends.length; // 친구들 몇명인지
        
        // 조건에 맞게 전역 테이블 초기화하기
        init();
        
        // 선물 지수 구하기 
        getGiftPoint();
        
        // 다음 달 선물 받을 것 예측하기
        getNextMonthGift(); 
        
        // 순서대로 정렬
        Arrays.sort(nextMonthGift);        
        return nextMonthGift[friendsCnt - 1]; // 최대값 구하기
    }
}