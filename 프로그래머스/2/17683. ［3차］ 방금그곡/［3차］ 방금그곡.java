class Solution {
    public String solution(String m, String[] musicinfos) {
    	// 기본값 설정
        String answer = "(None)";
        int maxTime = -1;
        // #이 붙은 부분을 소문자로 대체
        m = m.replace("C#", "c")
            .replace("D#", "d")
            .replace("F#", "f")
            .replace("G#", "g")
            .replace("A#", "a");
        
        for(int i = 0; i < musicinfos.length; i++) {
        	// musicinfos[i]의 값을 , 을 기준으로 나눠줌
            String[] temp = musicinfos[i].split(",");
            
            // 시간을 계산하기 위한 배열
            String[] time = temp[0].split(":");
            // 시작시간을 분단위로 변환
            int start = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);
            
            time = temp[1].split(":");
            // 종료시간을 분단위로 변환
            int end = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);
            
            // 총 재생시간을 구해줌
            int play = end - start;
            
            // m과 마찬가지로 #이 붙은 부분을 소문자로 대체
            temp[3] = temp[3].replace("C#", "c")
                            .replace("D#", "d")
                            .replace("F#", "f")
                            .replace("G#", "g")
                            .replace("A#", "a");
            
            // 대체된 악보를 저장
            String melody = temp[3];

			// 악보의 길이보다 총 재생시간이 작거나 같다면
            if(play <= temp[3].length()) {
            	// 해당 길이만큼 악보를 나눠줌
                melody = temp[3].substring(0, play);
            }
            // 악보의 길이보다 총 재생시간이 길다면
            else {
            	// 반복되는 횟수를 구해서 더해주고
                for(int j = 0; j < play / temp[3].length(); j++) {
                    melody += temp[3];
                }
                // 전체 반복이 아닌 끊어지는 부분까지 계산
                melody += temp[3].substring(0, play % temp[3].length());
            }
            
            // 완성된 악보에 m이 포함이 되며, 재생시간이 최대 재생시간보다 길 경우
            if(melody.contains(m) && play > maxTime) {
                // 최대 재생시간을 바꿔주고
                maxTime = play;
                // 결과값을 저장
                answer = temp[2];
            }
        }
        
        return answer;
    }
}