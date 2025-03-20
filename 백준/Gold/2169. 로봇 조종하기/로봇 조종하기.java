import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] dp = new int[N][M];
        dp[0][0] = board[0][0];
        for (int i = 1; i < M; i++) {  // 첫행 초기화(왼 -> 오)
            dp[0][i] += dp[0][i - 1] + board[0][i];
        }

        int[][] prev = new int[2][M];
        for (int i = 1; i < N; i++) {
            // 1. [i][0]-->
            prev[0][0] = dp[i - 1][0] + board[i][0];
            for (int j = 1; j < M; j++) { // 왼쪽에서 온 최댓값 + 현재 위치의 코인 vs 위에서 내려온 최댓값 + 현재 위치의 코인
                prev[0][j] = Math.max(prev[0][j - 1], dp[i - 1][j]) + board[i][j];
            }

            // 2. <--[i][M - 1]
            prev[1][M - 1] = dp[i - 1][M - 1] + board[i][M - 1];
            for (int j = M - 2; j >= 0; j--) { // 오른쪽에서 온 최댓값 + 현재 위치의 코인 vs 위에서 내려온 최댓값 + 현재 위치의 코인
                prev[1][j] = Math.max(prev[1][j + 1], dp[i - 1][j]) + board[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(prev[0][j], prev[1][j]);
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}