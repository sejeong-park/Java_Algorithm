import java.util.*;

/**
두 사람 선물 주고 받은 기록 - 이번 달까지 두 사람 사이에 더 많은 선물 준 사람이 담달에 선물 받음

*/


class Solution {
    
    static int friendSize;
    static Map<String, Integer> friendsMap; // 친구들의 순서가 담긴 맵
    static int[][] giftsHistory; //  주고 받은 선물과 선물 지수
    static int[] presentCnt;
    static int answer;
    

    
    public void giveAndTake(){
        // 두 사람이 선물을 주고 받은 기록이 있다면 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받는다.
        
        presentCnt = new int[friendSize]; // 선물 지수
        
        for (int fromIdx = 0; fromIdx < friendSize; fromIdx ++) {
            int getPresentCnt = 0;
            int sendPresentCnt = 0;
            for (int toIdx = 0; toIdx < friendSize; toIdx ++ ){
                // 준 사람과 받은 사람 계산
                // 무지가 준 선물의 개수
                getPresentCnt += giftsHistory[fromIdx][toIdx];
                sendPresentCnt += giftsHistory[toIdx][fromIdx];
            }
            presentCnt[fromIdx] = getPresentCnt - sendPresentCnt;
        }
    }
    
    public void findMaxPresentCnt() {
        // 최대 선물 지수 구하기
        int [] nextPresentCnt = new int[friendSize];
        boolean[][] visited = new boolean[friendSize][friendSize];
        for (int fromIdx = 0; fromIdx < friendSize; fromIdx++) {
            for (int toIdx = fromIdx; toIdx < friendSize; toIdx++) {
                // 양쪽 비교해서 더 큰 쪽이 갖는다.
                if (visited[toIdx][fromIdx] || visited[fromIdx][toIdx]) continue;
                if (giftsHistory[fromIdx][toIdx] < giftsHistory[toIdx][fromIdx]) {
                    nextPresentCnt[toIdx] += 1;
                }
                else if (giftsHistory[fromIdx][toIdx] > giftsHistory[toIdx][fromIdx]) {
                    nextPresentCnt[fromIdx] += 1;
                }
                else {
                    if (presentCnt[fromIdx] < presentCnt[toIdx]) {
                        nextPresentCnt[toIdx] += 1;
                    }
                    else if (presentCnt[fromIdx] > presentCnt[toIdx]) {
                        nextPresentCnt[fromIdx] += 1;
                    }
                }
                visited[fromIdx][toIdx] = true;
                visited[toIdx][fromIdx] = true;
            
            } // end for jdx
        } // end for idx
        
        for (int present : nextPresentCnt) {
            answer = Math.max(present, answer);
        }
        
    }    
    
    public int solution(String[] friends, String[] gifts) {
        answer = 0;
        
        // 친구들 맵에 넣기
        friendSize = friends.length;
        friendsMap = new HashMap<>();
        for (int idx = 0; idx < friendSize; idx ++) {
            friendsMap.put(friends[idx], idx); // 인덱스
        }
        
        // 주고받은 선물 지수
        giftsHistory = new int[friendSize][friendSize];
        for (int idx = 0; idx < gifts.length; idx ++) {
            String [] line = gifts[idx].split(" "); // 분할
            int fromIdx = friendsMap.get(line[0]); // 준사람
            int toIdx = friendsMap.get(line[1]); // 받은 사람
            giftsHistory[fromIdx][toIdx] += 1;
        }
        giveAndTake();
        findMaxPresentCnt();
        
        return answer;
    }
}