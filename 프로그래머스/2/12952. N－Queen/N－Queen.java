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
    
    private static int N;
    private static List<Node>[] nodes;
    private static boolean[][] board;
    private static boolean[] columnList;
    private static int result = 0;
    
    public int solution(int n) {
        this.N = n;
        nodes = new ArrayList[N];
        board = new boolean[N][N];
        columnList = new boolean[N];
        
        checkQueen(0);
        
        return result;
    }
    
    private static void checkQueen(int row) {
        if (row == N) {
            result++;
            return;
        }
        
        for (int col = 0; col < N; col++) {
            Node node = new Node(row, col);
            
            if (!columnList[col] && isDiagonalPossible(node)) { //퀸 -> 같은행, 같은열, 대각선
                board[node.row][node.col] = true;
                columnList[node.col] = true;
                checkQueen(row + 1);
                board[node.row][node.col] = false;
                columnList[node.col] = false;
            }
        }
    }
    
    private static boolean isDiagonalPossible(Node node) {
        //위왼쪽 대각선
        for (int row = node.row - 1, col = node.col - 1; row >= 0 && col >= 0; row--, col--) {  
            if (board[row][col]) {
                return false;
            }
        }
        
        //위오른쪽 대각선
        for (int row = node.row - 1, col = node.col + 1; row >= 0 && col < N; row--, col++) {  
            if (board[row][col]) {
                return false;
            }
        }
        
        //아래왼쪽 대각선
        for (int row = node.row + 1, col = node.col - 1; row < N && col >= 0; row++, col--) {  
            if (board[row][col]) {
                return false;
            }
        }
        
        //아래오른쪽 대각선
        for (int row = node.row + 1, col = node.col + 1; row < N && col < N; row++, col++) {  
            if (board[row][col]) {
                return false;
            }
        }
        
        return true;
    }
}

/*
(0,0) (0, 1) (0, 2) (0, 3)
(1,0) (1, 1) (1, 2) (1, 3)
(2,0) (2, 1) (2, 2) (2, 3)
(3,0) (3, 1) (3, 2) (3, 3)

*/