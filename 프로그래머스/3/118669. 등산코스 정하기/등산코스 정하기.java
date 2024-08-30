import java.util.*;

class Solution {
    private static final int SUMMIT = 1;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = { 0, Integer.MAX_VALUE };
        int[] info = new int[n + 1];
        
        for (int s: summits) { // 봉우리
            info[s] = SUMMIT;
        }
        
        List<int[]>[] nodes = new ArrayList[n + 1]; // 통행로
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] path: paths) {
            nodes[path[0]].add(new int[] {path[1], path[2]});
            nodes[path[1]].add(new int[] {path[0], path[2]});
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        for (int g: gates) { // 모든 시작점은 gates
            queue.offer(new int[]{ g, Integer.MAX_VALUE });
            distance[g] = 0;
        }
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] next: nodes[current[0]]) {
                int value = Math.max(distance[current[0]], next[1]);
                if (value < distance[next[0]]) {
                    distance[next[0]] = value;
                    if (info[next[0]] != SUMMIT){
                        queue.offer(new int[]{next[0], value});
                        continue;
                    }
                    if (distance[next[0]] < answer[1]
                        || answer[1] == distance[next[0]] && next[0] < answer[0]) {
                        answer[0] = next[0];
                        answer[1] = distance[next[0]];
                    }
                }
            }
        }
        
        return answer;
    }
}
/*
n개의 지점 (1~n)
양방향
쉼터 or 산봉우리 방문시 휴식 가능
휴식 없이 이동하는 가장 긴 시간 == intensity

출입구 -> 하나의 산봉우리만 방문 -> 출입구
intensity 최소

출구[] -> 각 봉우리까지 거리 (봉우리 만나면 큐에 안담고)
각 봉우리 (봉우리 만나면 큐에 안담고) -> 출구[]
*/