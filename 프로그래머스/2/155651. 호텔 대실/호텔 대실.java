import java.util.*;

class Node {
    int start;
    int end;
    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public int solution(String[][] book_time) {
        int n = book_time.length;
        
        List<Node> times = new ArrayList<>();
        for (String[] time: book_time) {
            times.add(new Node(convertTime(time[0]), convertTime(time[1]) + 10));
        }
        times.sort((o1, o2) -> o1.start - o2.start);
        
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(times.get(0).end);
        
        for (int i = 1; i < n; i++) {
            int endTime = queue.peek();
            if (times.get(i).start >= endTime) {
                queue.poll();
            } 
            queue.offer(times.get(i).end);
        }
        
        return queue.size();
    }
    
    private static int convertTime(String time) {
        String[] stringTime = time.split(":");
        return Integer.parseInt(stringTime[0])*60 + Integer.parseInt(stringTime[1]);
    }
}