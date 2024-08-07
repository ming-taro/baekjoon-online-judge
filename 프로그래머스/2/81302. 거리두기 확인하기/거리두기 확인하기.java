import java.util.*;

class Node {
    int row;
    int col;
    
    public Node (int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i = 0; i < 5; i++) {
            answer[i] = checkBoard(places[i], i);
        }
        
        return answer;
    }
    
    private int checkBoard(String[] place, int index) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {               
                if (place[row].charAt(col) == 'P'){
                    if (!bfs(new Node(row, col), place)) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }
    
    private boolean bfs(Node start, String[] place) {
        int[] dx = { -1, 0, 1, 0 }; // 위, 오, 아, 왼
        int[] dy = { 0, 1, 0, -1 };
        
        Queue<Node> queue = new ArrayDeque<>();
        int[][] visited = new int[5][5];
        
        queue.offer(start);
        visited[start.row][start.col] = 1;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (isValid(next)
                    && visited[next.row][next.col] == 0
                    && Math.abs(start.row - next.row) + Math.abs(start.col - next.col) <= 2) {
                    if (place[next.row].charAt(next.col) == 'P') { // 맨해튼 거리 내에 다른 응시자가 존재하는 경우
                        return false;
                    }
                    if (place[next.row].charAt(next.col) == 'O') {
                        queue.offer(next);
                        visited[next.row][next.col] = visited[current.row][current.col] + 1;
                    }
                }
            }
        }
        
        return true;
    }
    
    private boolean isValid(Node node) {
        return node.row >= 0 && node.row < 5 && node.col >= 0 && node.col < 5;
    }
}