import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        
        for (int i = 1; i <= s.length(); i++) {    
            Deque<String> stack = new ArrayDeque<>();
            Deque<Integer> count = new ArrayDeque<>();
            stack.push(s.substring(0, i));
            count.push(0);
            int index = 0;
            
            while (index < s.length()) {
                String current = index + i < s.length() ? s.substring(index, index + i) : s.substring(index);
                if (current.equals(stack.peek())) {
                    count.push(count.pop() + 1);
                } else {
                    stack.push(current);
                    count.push(1);
                }
                index += i;
            }
            
            int length = 0;
            while (!stack.isEmpty()) {
                int c = count.pop();
                if (c > 1) {
                    length += String.valueOf(c).length();
                }
                length += stack.pop().length();
            }
            answer = Math.min(answer, length);
        }
        
        return answer;
    }
}