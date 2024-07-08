import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        Deque<Character> queue = new ArrayDeque<>();
        
        answer = new_id.toLowerCase(); // 1단계
        answer = answer.replaceAll("[^a-z0-9_\\-.]", ""); // 2단계
        answer = answer.replaceAll("[\\.]+", "."); // 3단계
        
        // if (answer.length() > 0 && answer.charAt(0) == '.') { // 4단계
        //     answer = answer.substring(1);
        // }
        // if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
        //     answer = answer.substring(0, answer.length() - 1);
        // }
        answer = answer.replaceAll("(^[\\.])|([\\.]$)", ""); // 4단계
        // answer = answer.replaceAll("[\\.]$", ""); // 4단계     

        if (answer.isEmpty()) { // 5단계
            answer = "a";
        }
        
        if (answer.length() >= 16) { // 6단계
            answer = answer.substring(0, 15);
        }
         if (answer.length() > 0 && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }
        
        if (answer.length() <= 2) {
            char target = answer.charAt(answer.length() - 1);
            int repeat = 3 - answer.length();
            
            for (int i = 0; i < repeat; i++) {
                answer += target;
            }
        }
        
        return answer;
    }
}
/*
아이디: 소문자, 숫자, '-', '_', '.'
       ->'.'는 끝에 사용할 수 없다, 연속으로 사용할 수 없다
*/