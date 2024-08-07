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
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        Node start = null;
        Node end = null;
        Node lever = null;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'S') {
                    start = new Node(i, j);
                }
                if (maps[i].charAt(j) == 'L') {
                    lever = new Node(i, j);
                }
                if (maps[i].charAt(j) == 'E') {
                    end = new Node(i, j);
                }
            }
        }
        
        int toL = bfs(start, lever, maps);
        if (toL == -1) {
            return -1;
        }
        int toE = bfs(lever, end, maps);
        if (toE == -1){
            return -1;
        }
        
        return toL + toE;
    }
    
    private static int bfs(Node start, Node end, String[] maps) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        
        int[][] distance = new int[N][M];
        distance[start.row][start.col] = 1;
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == end.row && current.col == end.col) {
                return distance[current.row][current.col] - 1; // start칸이 1이기 때문에 결과값은 1을 빼줘야 한다
            }
            
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (isValid(next) 
                    && distance[next.row][next.col] == 0
                    && maps[next.row].charAt(next.col) != 'X') {
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
출구는 레버를 당겨서만 열 수 있다
출발 -> 레버 -> 도착
return 최단거리 or -1
*/