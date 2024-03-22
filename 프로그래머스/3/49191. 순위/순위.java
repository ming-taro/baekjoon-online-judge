import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] board = new int[n][n];
        
        for (int[] result: results) {
            board[result[0] - 1][result[1] - 1] = 1;
        }
        
        //플로이드 워셜 알고리즘
        for (int i = 0; i < n; i++) {   //j와 k노드 사이에 거쳐갈 i노드
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (board[j][i] == 1 && board[i][k] == 1) {  //j가 i를 이기고, i가 k를 이겼다면 j는 k를 이김
                        board[j][k] = 1;
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            int total = 0;
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 1 || board[i][j] == 1) {  //지는 경우 or 이기는 경우
                    total++;
                }
            }
            if (total == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}