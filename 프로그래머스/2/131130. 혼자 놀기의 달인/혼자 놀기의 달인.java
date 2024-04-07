import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        boolean[] visited = new boolean[cards.length];
        Queue<Integer> result = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < cards.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            int count = 1;
            int current = cards[i];
            visited[current - 1] = true;
            
            while (true) {
                int next = cards[current - 1];
                if (visited[next - 1]) {
                    break;
                }
                visited[next - 1] = true;
                current = next;
                count++;
            }
            
            result.add(count);
        }
        
        if (result.size() == 1) { //1번 상자 그룹을 제외하고 남는 상자X
            return 0;
        }
        
        return result.poll() * result.poll();
    }
}