import java.util.*;

class Solution {
    private int n, m;
    private int[] total;
    private boolean[][] visited;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        
        total = new int[m];
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && land[i][j] == 1) {
                    bfs(new Node(i, j), land);
                }
            }
        }
        
        for (int t: total) {
            answer = Math.max(answer, t);
        }
        
        return answer;
    }
    
    private void bfs(Node start, int[][] land) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.row][start.col] = true;
        
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        
        int count = 1;
        Set<Integer> col = new HashSet<>();
        col.add(start.col);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];
                if (isValid(nextRow, nextCol) 
                    && !visited[nextRow][nextCol] 
                    && land[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    count++;
                    col.add(nextCol);
                    queue.offer(new Node(nextRow, nextCol));
                }
            }
        }
        
        for (int c: col) {
            total[c] += count;
        }
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
}

class Node{
    int row;
    int col;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}