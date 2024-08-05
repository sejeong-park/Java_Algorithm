import java.util.*;
/**
일반 DFS 에서는 효율성에서 걸림 
-> HashMap을 이용해서 이분탐색을 이용해야함 (기본기가 중요한 문제)
효율성 몰라서 다음 풀이 참고
https://jisunshine.tistory.com/153
1. info가 포함될 수 있는 모든 경우의 수를 map에 key에 넣고, 점수를 value로 넣어준다.
2. query가 key로 하는 value를 가져와서 이분탐색한다.
*/
class Solution {
    
    static HashMap<String, List<Integer>> map;
    
    public static void makeSentence(String [] condition, String str, int cnt) {
        // 종료 조건 : 5개 탐색 다 했을 경우
        if (cnt == 4) {
            // 만약 key가 없으면 생성 
            if (!map.containsKey(str)) {
                List<Integer> list = new ArrayList<Integer>();
                map.put(str, list);
            }
            map.get(str).add(Integer.parseInt(condition[4])); // String to Integer
            return;
        }
        
        makeSentence(condition, str + "-", cnt + 1); // 다음 케이스에 없을 때
        makeSentence(condition, str + condition[cnt], cnt + 1); // 다음을 셀 때 
        
    }
    
    /**
    hashMap을 이분탐색 한다.
    */
    public static int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);
        int start = 0;
        int end = list.size() - 1; 
        
        while(start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        // 몇점 이상인 점수의 개수
        return list.size() - start; // start index 이상의 개수
    }
    
    
    public int[] solution(String[] info, String[] query) {
        int [] answer = new int[query.length];
    
        // 초기화
        map = new HashMap<String, List<Integer>>();
 
        for (int idx = 0; idx < info.length; idx ++) {
            String [] condition = info[idx].split(" ");
            makeSentence(condition, "", 0);
        }
        
        // 이분탐색을 위해 전체 List (코테 점수) 를 정렬한다.
        for (String key : map.keySet()) {
            Collections.sort(map.get(key)); // value 코테 점수 조정
        }
        
        // query 문 탐색
        for (int idx = 0; idx < query.length; idx ++) {
            query[idx] = query[idx].replaceAll(" and ", ""); // 조건은 그냥 String
            String[] q = query[idx].split(" "); // query와 코테점수 조건
            // 만약 map에 조건이 있다면
            if (map.containsKey(q[0])) {
                answer[idx] = binarySearch(q[0], Integer.parseInt(q[1]));
            } else {
                answer[idx] = 0;
            }
        }
        
        
        return answer;
    }
}