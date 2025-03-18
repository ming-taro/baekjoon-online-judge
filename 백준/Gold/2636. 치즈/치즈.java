import java.io.*;
import java.util.*;

class Main {
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int time = 0;
        int answer = 0;
        while (true) {
            int cheese = bfs(board);
            if (cheese == 0) break;
            time++;
            answer = cheese;
        }

        System.out.println(time);
        System.out.println(answer);
    }

    private static int bfs(int[][] board) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{ 0, 0 });
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int cheeese = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current[0] + dx[i];
                int nextCol = current[1] + dy[i];
                if (isValid(nextRow, nextCol)
                        && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    if (board[nextRow][nextCol] == 1) { // 치즈를 만나면 더 이상X
                        board[nextRow][nextCol] = 0;
                        cheeese++;
                    } else {
                        queue.offer(new int[]{ nextRow, nextCol });
                    }
                }
            }
        }

        return cheeese;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}