import java.util.*;

/**
카오톡 오픈채팅방 -> 친구가 아닌 사람들과 대화 가능 -> 가상의 닉네임
관리자 창
- 채팅방에서 닉네임을 변경하는 방법
1. 채팅방을 나간 후 새로운 닉네임으로 다시 들어간다.
2. 채팅방에서 닉네임을 변경한다.
*/

class Solution {
    
    public String[] solution(String[] record) {
        
        // init
        Map<String, String> nickNameMap = new HashMap<>(); // 닉네임 저장소
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("Enter", "님이 들어왔습니다.");
        messageMap.put("Leave", "님이 나갔습니다.");
        
        
        // 입력 
        ArrayList<String[]> message = new ArrayList<>();
        for (int idx = 0; idx < record.length; idx++) {
            String [] strList = record[idx].split(" ");
            
            String action = strList[0]; 
            String uuid = strList[1];
            
            if (action.equals("Enter")) {
                String nickname = strList[2]; 
                // 닉네임 넣기
                nickNameMap.put(uuid, nickname);
                message.add(new String[]{uuid, action});
            }
            else if (action.equals("Leave")) {
                message.add(new String[]{uuid, action});
            }
            else if (action.equals("Change")) {
                String nickname = strList[2]; 
                nickNameMap.put(uuid, nickname);
            }            
            
        }
        
        String [] answer = new String[message.size()];
        for (int idx = 0; idx < message.size(); idx ++) {
            String[] line = message.get(idx);
            answer[idx] = nickNameMap.get(line[0]) + messageMap.get(line[1]);
        }
        
        return answer;
    }
}