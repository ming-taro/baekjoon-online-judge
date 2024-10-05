import java.util.*;

class Solution {
    private static int answer;
    private static int[] info;
    private static int[][] edges;
    private static boolean[] visited;
    private static int n;
    
    public int solution(int[] info, int[][] edges) {
        this.n = info.length;
        this.info = info;
        this.edges = edges;
        
        visited = new boolean[n];
        visited[0] = true;
        
        return dfs(1, 0);
    }
    
    private static int dfs(int sheep, int wolf) {
        if (sheep == wolf) {
            return sheep;
        }
        
        int maxSheep = sheep;
        
        for (int[] e: edges) {
            int parent = e[0];
            int child = e[1];
            
            if (visited[parent] && !visited[child]) {
                visited[child] = true;
                if (info[child] == 0) {
                    maxSheep = Math.max(maxSheep, dfs(sheep + 1, wolf));
                } else {
                    maxSheep = Math.max(maxSheep, dfs(sheep, wolf + 1));
                }
                visited[child] = false;
            }
        }
        
        return maxSheep;
    }
}