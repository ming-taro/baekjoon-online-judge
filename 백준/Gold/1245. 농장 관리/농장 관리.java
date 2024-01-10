import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] board;
    private static boolean[][] visited;
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int count = 0;

        for (int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if (!visited[row][col]) {
                    if (bfs(row, col)){
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static boolean bfs(int startRow, int startCol) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        boolean isMountainPeak = true;

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; //상, 우, 하, 좌
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int row = current[0];
            int col = current[1];

            for (int i = 0; i < 8; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M){
                    continue;
                }

                if (board[row][col] < board[nextRow][nextCol]) {  //주변에 큰 값이 있는 경우 -> 봉우리가 아님
                    isMountainPeak = false;
                }

                if (!visited[nextRow][nextCol]
                    && board[row][col] == board[nextRow][nextCol]) {
                    q.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        return isMountainPeak;
    }
}