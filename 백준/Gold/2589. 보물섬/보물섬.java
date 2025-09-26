import java.io.*;
import java.util.*;

class Main {
    private static int N, M;
    private static char[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = reader.readLine().toCharArray();
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'L') {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int row, int col) {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(row, col, 0));

        boolean[][] visited = new boolean[N][M];
        visited[row][col] = true;

        int answer = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];
                if (isValid(nextRow, nextCol)
                        && !visited[nextRow][nextCol]
                        && board[nextRow][nextCol] == 'L') {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new Node(nextRow, nextCol, current.count + 1));
                    answer = Math.max(answer, current.count + 1);
                }
            }
        }

        return answer;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}

class Node {
    int row;
    int col;
    int count;
    public Node(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }
}