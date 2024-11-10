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
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i].charAt(j) == 'X') {
                    visited[i][j] = true;
                }
            }
        }
        
        List<Integer> result = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    result.add(bfs(new Node(i, j), visited, maps));
                }
            }
        }
        
        if (result.isEmpty()) {
            return new int[]{ -1 };
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        Arrays.sort(answer);
        
        return answer;
    }
    
    private static int bfs(Node start, boolean[][] visited, String[] maps) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.row][start.col] = true;
        
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int total = maps[start.row].charAt(start.col) - '0';
        
        while(!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (isValid(next) && !visited[next.row][next.col]) {
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                    total += maps[next.row].charAt(next.col) - '0';
                }
            }
        }
        
        return total;
    }
    
    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < M;
    }
}
/*
칸 = X(바다) or 1~9(무인도)
->상하좌우 연결 땅들은 하나의 무인도
->각 칸의 총합은 해당 무인도에서 머물 수 있는 일수

return 각 섬에서 최대로 머물 수 있는 일수
*/