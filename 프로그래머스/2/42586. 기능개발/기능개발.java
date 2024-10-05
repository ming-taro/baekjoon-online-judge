import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        Queue<Integer> result = new ArrayDeque<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            int period = (100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i] : (100 - progresses[i]) / speeds[i] + 1;
            if (queue.isEmpty()) {
                queue.offer(period);
                count = 1;
                continue;
            }
            if (queue.peek() >= period) {
                count++;
                continue;
            }
            result.offer(count);
            count = 1;
            queue.poll(); 
            queue.offer(period);
        }
        result.offer(count);
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.poll();
        }
        return answer;
    }
}
/*
기능 -> 진도 100%일 때 반영
progresses = 배포순 작업진도
speeds = 각 작업 개발 속도

93% 작업 -> 7% / 1% = 7일 걸림
30% 작업 -> 70% / 30% = 3일 걸림
55% 작업 -> 45% / 5% = 9일 걸림

1번 + 2번 배포 (7일) - 3번 배포 (9일)
=> [2, 1]

[9]
cnt = 1
result = [2, 1]
*/