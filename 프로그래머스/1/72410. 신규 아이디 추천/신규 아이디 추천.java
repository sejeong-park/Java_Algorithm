class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        // step 1
        String step1 = new_id.toLowerCase();
        
        
        // step 2
        char [] arrayList = step1.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char str : arrayList) {
            if (('a' <= str && str <= 'z') || ('0' <= str && str <= '9') || str == '-' || str == '_' || str == '.') {
                sb.append(str);
            }
        }
        
        String step2 = sb.toString();
        
        // step 3
        String step3 = step2.toString().replace("..", ".");
        while (step3.contains("..")) {
            step3 = step3.replace("..",".");
        }
        
        // step 4
        String step4 = step3;
        if (step4.length() > 0 && step4.charAt(0) == '.') {
            step4 = step4.substring(1, step4.length());
        }
        
        if (step4.length() > 0 && step4.charAt(step4.length() - 1) == '.') {
            step4 = step4.substring(0, step4.length() - 1);
        }
        
        // step 5
        String step5 = step4;
        if (step5.equals("")) {
            step5 = "a";
        }
        
        // step 6
        String step6 = step5;
        if (step6.length() >= 16) {
            step6 = step6.substring(0, 15);
            
            if (step6.charAt(step6.length() - 1) == '.') {
                step6 = step6.substring(0, step6.length() - 1);
            }
        }
        
        
        // step 7
        StringBuilder step7 = new StringBuilder(step6);
        if (step7.length() <= 2) {
            char str = step7.charAt(step7.length() - 1);
            
            while (step7.length() < 3) {
                step7.append(str);
            }
        }
        
        answer = String.valueOf(step7);
        
        return answer;
    }
}