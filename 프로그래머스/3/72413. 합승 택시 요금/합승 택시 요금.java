import java.util.*;

class Solution {
    private static List<int[]>[] nodes;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        nodes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        
        for (int[] fare: fares) {
            nodes[fare[0]].add(new int[] {fare[1], fare[2]});
            nodes[fare[1]].add(new int[] {fare[0], fare[2]});
        }
        
        int[] basic = findPath(s, n);
        int[] aPath = findPath(a, n);
        int[] bPath = findPath(b, n);

        for (int i = 1; i <= n; i++) {
            int total = basic[i] + aPath[i] + bPath[i];    // i -> a + i -> b
            answer = Math.min(answer, total);
        }
        
        return answer;
    }
    
    public static int[] findPath(int start, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int[] next: nodes[current]) {
                int value = distance[current] + next[1];
                if (value < distance[next[0]]) {
                    distance[next[0]] = value;
                    queue.offer(next[0]);
                }
            }
        }
        return distance;
    }
}