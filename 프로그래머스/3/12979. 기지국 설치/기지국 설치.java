
class Solution {
    public int solution(int n, int[] stations, int w) {
        int now = 1; 
        int stationIdx = 0; 
        int answer = 0; 
        while (now <= n) {
            if(stationIdx >= stations.length || now < stations[stationIdx] - w) {
                answer++;
                now = now + 2 * w + 1;
            }
            else {
                now = stations[stationIdx] + w + 1;
                stationIdx++;
            }
        }
        return answer;
    }
}