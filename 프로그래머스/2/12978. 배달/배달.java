import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        List<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<Node>();
        }
        
        for (int[] r: road) {
            nodes[r[0]].add(new Node(r[1], r[2]));
            nodes[r[1]].add(new Node(r[0], r[2]));
        }
        
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
        queue.offer(new Node(1, 0));
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Node next: nodes[current.num]) {
                int newTime = distance[current.num] + next.time;
                if (newTime < distance[next.num]) {
                    distance[next.num] = newTime;
                    queue.offer(new Node(next.num, newTime));
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= K) answer++;
        }
        
        return answer;
    }
}

class Node {
    int num;
    int time;
    public Node(int num, int time) {
        this.num = num;
        this.time = time;
    }
}