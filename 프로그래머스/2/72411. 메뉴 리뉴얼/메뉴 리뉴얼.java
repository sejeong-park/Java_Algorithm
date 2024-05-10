import java.util.*;


/**
손님들이 주문 할 때, 단품 메뉴 코스요리에구성
스카피가 추가하고 싶어하는 코스요리를 구성하는 단품 메뉴들의 갯수

*/


class Solution {
    
    // 코스 조합 기준으로 몇개 있는 지 확인
    static Map<String, Integer> courseList;
    // 코스 구성에 따라 최대 가져오기
    static Map<Integer, Integer> maxList;
    
    static ArrayList<String> answer;
    
    // 스트링 만들어주기
    public static void findCourseList(String[] menuList, boolean[] visited) {
        ArrayList<String> menuCourse = new ArrayList<>();
        for (int idx = 0; idx < menuList.length; idx++) {
            if (visited[idx]) {
                menuCourse.add(menuList[idx]);
            }
        }
        Collections.sort(menuCourse);
        String result = String.join("", menuCourse);

        // 코스 존재 여부
        if (courseList.containsKey(result)) {
            courseList.put(result,courseList.get(result) + 1);
        } else{
            courseList.put(result, 1);
        }
        
        // 코스의 길이에 따른 최대값 찾기
        int max = Math.max(maxList.get(result.length()), courseList.get(result));
        maxList.put(result.length(), max);
        
        return;
    }
    
    // 조합 대상
    public static void combination(String[] menuList, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            findCourseList(menuList, visited);
            return;
        }
        
        for (int idx = start; idx < n; idx ++) {
            visited[idx] = true;
            combination(menuList, visited, idx + 1, n, r-1);
            visited[idx] = false;
        }
        
    }
    // 개수 세기 -> 가장 많이 함께 주문된 단품 메뉴 조합
    public static void courseCount(String course, int count) {
        
        // 최대값과 같은 메뉴 목록 찾기
        int maxCourseSize = maxList.get(course.length());
        
        if (count == maxCourseSize) {
            // 단품 두개 이상인지 검사
            String [] courseArray = course.split("");
            if (courseList.get(course) < 2) {
               return; 
            }
            
            // 통과하면
            // System.out.println(course + " " + courseList.get(course));
            answer.add(course);
        }
        return;
    }
    
    public String[] solution(String[] orders, int[] course) {
        

        courseList = new HashMap<>();
        maxList = new HashMap<>();
        
        // 코스 수량을 기준으로 콤비네이션 찾기
        for (int courseSize : course) {
            maxList.put(courseSize, Integer.MIN_VALUE);
            // 주문 
            for (int idx =0; idx < orders.length; idx ++) {
                String [] menu = orders[idx].split("");
                // 조합
                boolean [] visited = new boolean[menu.length];
                combination(menu, visited, 0, menu.length, courseSize);
            
            }
        }
        
        //결과
        answer = new ArrayList<>();
        courseList.forEach((key, value) -> {
            courseCount(key, value);
        });
        
        Collections.sort(answer);
        // System.out.println(answer.toString());
        String[] answerList = new String[answer.size()];
        return answer.toArray(answerList);
    }
}