import java.util.*;

class Robot {
    int[] left;
    int[] right;
    int count;
    
    public Robot(int[] left, int[] right, int count) {
        this.left = left;
        this.right = right;
        this.count = count;
    }
}

class Solution {
    private static int N; 
    private static int[][] newBoard;
    
    public int solution(int[][] board) {
        N = board.length + 2;
        newBoard = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            newBoard[i][0] = 1;     // 좌
            newBoard[i][N - 1] = 1; // 우
            newBoard[0][i] = 1;     // 상
            newBoard[N - 1][i] = 1; // 하
            if (i > 0 && i < N - 1) {
                for (int j = 1; j < N - 1; j++) {
                    newBoard[i][j] = board[i - 1][j - 1];
                }
            }
        }
        
        Queue<Robot> queue = new ArrayDeque<>();
        queue.offer(new Robot(new int[]{1, 1}, new int[]{1, 2}, 0));
        
        boolean[][][] visited = new boolean[N][N][2];
        visited[1][1][0] = true;
        
        while (!queue.isEmpty()) {
            Robot current = queue.poll();
            System.out.println(current);
            if (current.right[0] == N - 2 && current.right[1] == N - 2) {
                return current.count;
            }
            
            for (Robot next: findNextMove(current)) {
                int direction = 0;
                if (next.left[1] == next.right[1]) {
                    direction = 1;
                }
                
                if (!visited[next.left[0]][next.left[1]][direction]) {
                    queue.offer(next);
                    visited[next.left[0]][next.left[1]][direction] = true;
                    
                }
            }
        }
        
        return -1;
    }
    
    private static List<Robot> findNextMove(Robot current) {
        List<Robot> move = new ArrayList<>();
        
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        
        for (int i = 0; i < 4; i++) {  // 상, 하, 좌, 우
            int[] left = new int[]{current.left[0] + dx[i], current.left[1] + dy[i]};
            int[] right = new int[]{current.right[0] + dx[i], current.right[1] + dy[i]};
            if (newBoard[left[0]][left[1]] == 0 && newBoard[right[0]][right[1]] == 0) {
                move.add(new Robot(left, right, current.count + 1)); 
            }
        }

        if (current.left[0] == current.right[0]) { // 로봇이 가로방향인 경우
            int[] left = new int[]{current.left[0] - 1, current.left[1]};
            int[] right = new int[]{current.right[0] - 1, current.right[1]};
            if (newBoard[left[0]][left[1]] == 0 && newBoard[right[0]][right[1]] == 0) {
                move.add(new Robot(left, current.left, current.count + 1));
                move.add(new Robot(right, current.right, current.count + 1));
            }
            
            left = new int[]{current.left[0] + 1, current.left[1]};
            right = new int[]{current.right[0] + 1, current.right[1]};
            if (newBoard[left[0]][left[1]] == 0 && newBoard[right[0]][right[1]] == 0) {
                move.add(new Robot(current.left, left, current.count + 1));
                move.add(new Robot(current.right, right, current.count + 1));
            }
        } else { // 로봇이 세로방향인 경우
            int[] left = new int[]{current.left[0], current.left[1] + 1};
            int[] right = new int[]{current.right[0], current.right[1] + 1};
            if (newBoard[left[0]][left[1]] == 0 && newBoard[right[0]][right[1]] == 0) {
                move.add(new Robot(current.left, left, current.count + 1));
                move.add(new Robot(current.right, right, current.count + 1));
            }
            
            left = new int[]{current.left[0], current.left[1] - 1};
            right = new int[]{current.right[0], current.right[1] - 1};
            if (newBoard[left[0]][left[1]] == 0 && newBoard[right[0]][right[1]] == 0) {
                move.add(new Robot(left, current.left, current.count + 1));
                move.add(new Robot(right, current.right, current.count + 1));
            }
        }
        
        return move;
    }
}