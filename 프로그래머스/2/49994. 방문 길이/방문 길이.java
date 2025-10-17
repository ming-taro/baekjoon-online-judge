import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        
        int row = 5; // 0 ~ 10 좌표
        int col = 5;
        
        boolean[][][] visited = new boolean[4][11][11];
        
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        
        for (int i = 0; i < dirs.length(); i++) {
            int d = calcDirection(dirs.charAt(i));
            int opposite = calcOpposite(d);
            
            int nextRow = row + dx[d];
            int nextCol = col + dy[d];
            
            if (!isValid(nextRow, nextCol)) continue;
            
            if (!visited[d][row][col]) answer++;
            visited[d][row][col] = true;
            visited[opposite][nextRow][nextCol] = true;
            row = nextRow;
            col = nextCol;
        }
        
        return answer;
    }
    
    private static int calcDirection(char d) {
        if (d == 'U') return 0;
        if (d == 'R') return 1;
        if (d == 'D') return 2;
        return 3;
    }
    
    private static int calcOpposite(int d) {
        if (d == 0) return 2;
        if (d == 1) return 3;
        if (d == 2) return 0;
        return 1;
    }
    
    private static boolean isValid(int row, int col) {
        return row >= 0 && row <= 10 && col >= 0 && col <= 10;
    }
}