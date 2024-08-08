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
    private static int[][] board;
    private static int n;
    private static int answer = 0;
    
    public int solution(int n) {
        this.n = n;
        board = new int[n][n];
        
        dfs(0);
        
        return answer;
    }
    
    private static void dfs(int row) {
        if (row == n) {
            answer++;
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (check(new Node(row, col))) {
                board[row][col] = 1;
                dfs(row + 1);
                board[row][col] = 0;
            }
        }
    }
    
    private static boolean check(Node node) {
        for (int r = 0; r < n; r++) {      // 열 검사
            if (board[r][node.col] == 1) {
                return false;
            }
        }
        
        for (int i = 1; i < n; i++) {
            int topRow = node.row - i;
            int downRow = node.row + i;
            int leftCol = node.col - i;
            int rightCol = node.col + i;
            
            if (isValid(topRow, leftCol) && board[topRow][leftCol] == 1
                || isValid(topRow, rightCol) && board[topRow][rightCol] == 1) {  // 좌우측 상단 대각선
                return false;
            }
            if (isValid(downRow, leftCol) && board[downRow][leftCol] == 1
                || isValid(downRow, rightCol) && board[downRow][rightCol] == 1) {// 좌우측 하단 대각선
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isValid(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }
}