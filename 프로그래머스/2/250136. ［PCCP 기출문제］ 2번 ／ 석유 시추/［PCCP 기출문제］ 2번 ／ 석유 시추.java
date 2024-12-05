import java.util.*;

class Node {
    int row;
    int col;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private static int n;
    private static int m;
    private static int[] result;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        this.n = land.length;
        this.m = land[0].length;
        result = new int[m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1) {
                    bfs(new Node(i, j), land);
                }
            }
        }
        
        for (int oil: result) {
            answer = Math.max(answer, oil);
        }
        
        return answer;
    }
    
    private static void bfs(Node start, int[][] land) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        land[start.row][start.col] = 0;
        
        Set<Integer> column = new HashSet<>();
        column.add(start.col);
        
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        
        int total = 1;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (isValid(next) && land[next.row][next.col] == 1) {
                    column.add(next.col);
                    queue.offer(next);
                    land[next.row][next.col] = 0;
                    total++;
                }
            }
        }
        
        for (int col: column) {
            result[col] += total;
        }
    }
    
    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < n && node.col >= 0 && node.col < m;
    }
}