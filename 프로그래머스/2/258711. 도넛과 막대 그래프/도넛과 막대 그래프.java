import java.util.*;

class Solution {
    private static List<Integer>[] nodes;
    private static int N;
    
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};
        
        int N = 0;
        for (int[] edge: edges) {
            N = Math.max(N, Math.max(edge[0], edge[1]));
        }
        
        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        
        int[] inCount = new int[N + 1];
        int[] outCount = new int[N + 1];
        
        for (int[] edge: edges) {
            nodes[edge[0]].add(edge[1]);
            outCount[edge[0]]++;
            inCount[edge[1]]++;
        }
        
        for (int i = 1; i <= N; i++) {
            if (inCount[i] == 0 && outCount[i] >= 2) {
                answer[0] = i;
                break;
            }
        }
        
        boolean[] visited = new boolean[N + 1];
        
        for (int node: nodes[answer[0]]) {
            int start = node;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            
            while (!queue.isEmpty()) {
                int current = queue.poll();
                
                if (visited[current] && current == start) {  //1. 도넛 모양 그래프
                    answer[1]++;
                    break;
                }
                
                if (outCount[current] == 0) {  //2. 막대 모양 그래프
                    answer[2]++;
                    break;
                }
                if (inCount[current] >= 2 && outCount[current] >= 2) { //3. 8자 모양 그래프
                    answer[3]++;
                    break;
                }
                
                for (int next: nodes[current]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }
        
        return answer;
    }
}
/*
도넛: 1입 1출 + 싸이클
막대: 1입 1출
8자: n입 n출
정점: 무조건 2출 이상
*/