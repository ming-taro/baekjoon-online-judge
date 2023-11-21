import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = addQueue(queue1);
        Queue<Integer> q2 = addQueue(queue2);
        
        long q1Sum = Arrays.stream(queue1).sum();
        long q2Sum = Arrays.stream(queue2).sum();
        
        int q1PollCount = 0;
        int q2PollCount = 0;
        
        if((q1Sum + q2Sum) % 2 == 1) {
            return -1;
        }
        
        while(true) {
            if(q1Sum == q2Sum) {
                break;
            } else if(q1PollCount == queue1.length + queue2.length 
                      || q2PollCount == queue1.length + queue2.length) {
                return -1;
            }
            
            if(q1Sum < q2Sum) {
                int value = q2.poll();
                q1Sum += value;
                q2Sum -= value;
                q1.offer(value);
                q2PollCount += 1;
            } else {
                int value = q1.poll();
                q1Sum -= value;
                q2Sum += value;
                q2.offer(value);
                q1PollCount += 1;
            }
            
            answer += 1;
        }
        

        return answer;
    }
    
    public Queue<Integer> addQueue(int[] items) {
        Queue<Integer> q = new LinkedList<>();
        
        for(int item: items) {
            q.add(item);
        }
        
        return q;
    }
}