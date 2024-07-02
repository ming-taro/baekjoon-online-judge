import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        Queue<Integer> queue = new PriorityQueue<>();
        
        for (int scov: scoville) {
            queue.offer(scov);
        }
        
        while (queue.size() >= 2) {
            int first = queue.poll();
            if (first >= K) {
                break;
            }          
            int second = queue.poll();
            queue.offer(first + second * 2);
            answer++;
        }
        
        if (queue.peek() >= K) {
            return answer;
        }
        
        return -1;
    }
}
/*
->모든 음식의 스코빌 지수를 K이상으로 만들고 싶다
->스코빌 지수가 가장 낮은 두 개 음식 = 첫번째 + 두번째*2
*/