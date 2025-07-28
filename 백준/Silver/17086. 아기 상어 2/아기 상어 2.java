import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int d;

    public Node(int row, int col, int d) {
        this.row = row;
        this.col = col;
        this.d = d;
    }
}

class Main {
    private static int N;
    private static int M;

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

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] == 0) {
                    answer = Math.max(answer, bfs(r, c, board));
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int row, int col, int[][] board) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node( row, col, 0 ));

        boolean[][] visited = new boolean[N][M];
        visited[row][col] = true;

        int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };   // 위, 위오, 오, 오아, 아, 왼아, 왼, 왼위
        int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (board[current.row][current.col] == 1) {
                return current.d;
            }

            for (int i = 0; i < dx.length; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];
                if (isValid(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new Node(nextRow, nextCol, current.d + 1));
                }
            }
        }

        return 0;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}