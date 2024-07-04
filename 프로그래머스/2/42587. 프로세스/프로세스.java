import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        List<Integer> nodes = new ArrayList<>();
        
        Deque<Integer> deque = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++) {
            deque.offer(i);
            nodes.add(priorities[i]);
        }
        
        nodes.sort((o1, o2) -> o2 - o1);
        
        while (!deque.isEmpty()) {
            int current = deque.poll();
            if (nodes.get(answer) <= priorities[current]) { // 우선순위가 가장 높은 큐
                answer++;
            } else {
                deque.offer(current);
                continue;
            }
            
            if (current == location) {
                return answer;
            }
        }
        
        return answer;
    }
}
/*
return 특정 프로세스가 몇 번째로 실행될까?

실행큐 []
우선순위큐 []

실행큐 peek -> 우선순위가 제일 높으면 꺼냄
           -> 나보다 높은 프로세스가 있다면 뒤로가기

*/