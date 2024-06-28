import java.util.*;

class Solution {
    
    static int [][] copy;
    
    static void copy(int [][] map) {
        copy = new int [map.length][map[0].length];
        
        for (int idx = 0; idx < map.length; idx ++) {
            copy[idx] = map[idx].clone();
        }
    }
    
    int solution(int[][] land) {
        int answer = 0;

        copy(land);
        
        for (int row = 1; row < land.length; row ++) {
            for (int col = 0; col < land[0].length; col ++) {
                int max = 0;
                for (int next = 0; next < land[0].length; next++) {
                    if (col == next) continue;
                    max = Math.max(max, copy[row - 1][next]);
                }
                copy[row][col] += max;
            }
        }
        
        // for (int row = 0; row < land.length; row ++) {
        //     for (int col = 0; col < land[0].length; col ++){
        //         System.out.print(copy[row][col] + " ");
        //     }
        //     System.out.println();
        // }
        
        
        for (int col = 0; col < land[0].length; col ++) {
            answer = Math.max(answer, copy[land.length-1][col]);
        }
        
        return answer;
    }
}