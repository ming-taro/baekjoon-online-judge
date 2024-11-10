import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Integer>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        for (int[] e: edge) {
            nodes[e[0]].add(e[1]);
            nodes[e[1]].add(e[0]);
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        
        int maxDistance = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next: nodes[current]) {
                if (distance[current] + 1 < distance[next]) {
                    distance[next] = distance[current] + 1;
                    maxDistance = Math.max(maxDistance, distance[next]);
                    queue.offer(next);
                }
            }
        }
        
        for (int d: distance) {
            if (d == maxDistance) answer++;
        }
        
        return answer;
    }
}