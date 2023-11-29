import java.util.*;
import java.awt.*;

class Solution {
    /*
    시추관을 수직으로 단 하나만 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 시추관의 위치
    */
    
    static int rowSize;
    static int colSize;
    
    // delta 
    static int[] deltaRow = {-1, 0, 1, 0};
    static int[] deltaCol = {0, -1, 0, 1};
    
    
    static Map<Integer, Integer> totalColList = new HashMap<>(); // 조각별 size
    static Set<Integer> colList = new HashSet<>(); // column 기준으로 중복 확인하기 위해
    static int[][] landMap;
    static boolean[][] visited;
    
    public static boolean inMap(int rowIdx, int colIdx){
        // 만약 맵 밖으로 나가는 지 안나가는지
        if (rowIdx >= rowSize || rowIdx < 0  || colIdx >= colSize || colIdx < 0){
            return false;
        }
        return true;
    }
    
    // 한개의 석유 덩어리
    public static int bfs(int startRowIdx, int startColIdx){
        
        visited[startRowIdx][startColIdx] = true;
        
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startRowIdx, startColIdx)); 
        
        colList = new HashSet<>(); // colum 저장하는 set (중복 미포함)
        
        int size = 0; // 석유 시추 탐색하는 것의 사이즈
        
        while (!queue.isEmpty()){
            Point currentPoint = queue.poll();
            colList.add(currentPoint.y); // 열 저장
            size++;
            
            for (int direction = 0; direction < 4; direction ++) {
                int nextRow = currentPoint.x + deltaRow[direction];
                int nextCol = currentPoint.y + deltaCol[direction];
                
                if (!inMap(nextRow, nextCol)) continue; // 범위 밖이면
                if (visited[nextRow][nextCol]) continue; // 이미 방문했다면
                if (landMap[nextRow][nextCol] == 0) continue; // 석유가 없다면
                
                // 탐색 가능한 범위
                visited[nextRow][nextCol] = true; // 방문함
                queue.add(new Point(nextRow, nextCol)); // 다음 큐에 넣기   
            }
        }
        
        // 전체 Col에 저장하기
        for (int colIdx : colList) {
            totalColList.put(colIdx, totalColList.get(colIdx) + size); // 현재 사이즈를 더해서 저장
        }
        
        return size;
    }
    
    
    public int solution(int[][] land) {
        int answer = 0;
        
        // 사이즈 구하기
        rowSize = land.length;
        colSize = land[0].length;
        
        landMap = land; // land 전역으로 넣기
        visited = new boolean[rowSize][colSize]; //  방문 여부
        
        // 전체 index를 초기화할 hashMap
        for (int colIdx = 0; colIdx < colSize; colIdx++) {
            totalColList.put(colIdx, 0); // 우선 0으로 초기화
        }
        
        // 시추 찾기
        for (int rowIdx = 0; rowIdx < rowSize ; rowIdx++){
            for (int colIdx = 0; colIdx < colSize; colIdx ++){                
                // 처음 방문하는 석유와를 탐색한다.
                if (landMap[rowIdx][colIdx] == 1 && !visited[rowIdx][colIdx]){
                    bfs(rowIdx, colIdx);
                }
            }
        }
        
        // 최대값 찾기
        answer = Integer.MIN_VALUE; // 최소 값
        for (int colIdx = 0; colIdx < colSize ;colIdx ++){
            answer = Math.max(answer, totalColList.get(colIdx));
        }
        
        return answer;
    }
}