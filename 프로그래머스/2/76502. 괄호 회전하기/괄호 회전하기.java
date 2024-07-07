import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        Queue<Character> queue = new ArrayDeque<>();
        Map<Character, Character> items = new HashMap<>();
        
        items.put('(', ')');
        items.put('{', '}');
        items.put('[', ']');
        
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i));
        }
        
        for (int i = 0; i < s.length(); i++) {
            Deque<Character> stack = new ArrayDeque<>();
            for (Character ch: queue) {
                if (stack .isEmpty() || items.containsKey(ch)) {
                    stack .push(ch);
                    continue;
                }
                if (items.get(stack .peek()) == ch) { // 괄호짝이 맞음
                    stack .pop();
                    continue;
                }
                stack .push(ch);
            }
            if (stack .size() == 0) {
                answer++;
            }
            queue.offer(queue.poll());
        }
        
        return answer;
    }
}