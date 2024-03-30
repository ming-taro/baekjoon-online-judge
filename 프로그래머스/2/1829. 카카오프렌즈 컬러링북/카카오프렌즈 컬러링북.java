import java.util.*;

class Solution {
   static class Node {
       int row;
       int col;
       
       public Node(int row, int col) {
           this.row = row;
           this.col = col;
       }
   }
    
    private static boolean[][] visited;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        visited = new boolean[m][n];
        
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (!visited[row][col] && picture[row][col] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(m, n, picture, new Node(row, col)));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private static int bfs(int m, int n, int[][] picture, Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.row][start.col] = true;
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int count = 1;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (next.row >= 0 && next.row < m 
                    && next.col >= 0 && next.col < n
                    && picture[next.row][next.col] == picture[current.row][current.col]
                    && !visited[next.row][next.col]) {
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}