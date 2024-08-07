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
    private static int N;
    private static int M;
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
    
        return bfs(maps);
    }
    
    private static int bfs(int[][] maps) {
        Queue<Node> queue = new ArrayDeque<>();
        int[][] distance = new int[N][M];
        
        queue.offer(new Node(0, 0));
        distance[0][0] = 1;
        
        int[] dx = {-1, 0, 1, 0}; // 위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == N - 1 && current.col == M - 1) {
                return distance[current.row][current.col];
            }
            
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (isValid(next)
                    && maps[next.row][next.col] == 1
                    && distance[next.row][next.col] == 0) {
                    distance[next.row][next.col] = distance[current.row][current.col] + 1;
                    queue.offer(next);
                }
            }
        }
        return -1;
    }
    
    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < M;
    }
}
/*
N*M 크기의 맵
상하좌우 이동 -> 최단거리
최단거리 or -1
*/