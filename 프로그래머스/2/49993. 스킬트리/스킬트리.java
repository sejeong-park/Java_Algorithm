import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        List<Character> skillList = new ArrayList<>();
        for (char ch : skill.toCharArray()) {
            skillList.add(ch);
        }

        for (String target : skill_trees) {
            List<Character> check = new ArrayList<>();
            for (char ch : target.toCharArray()) {
                if (skillList.contains(ch)) {
                    check.add(ch);
                }
            }

            boolean isValid = true;
            for (int i = 0; i < check.size(); i++) {
                if (check.get(i) != skillList.get(i)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                answer++;
            }
        }

        return answer;
    }
}