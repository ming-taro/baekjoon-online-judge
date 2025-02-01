import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int wall;
    int count;
    public Node(int row, int col, int wall, int count) {
        this.row = row;
        this.col = col;
        this.wall = wall;
        this.count = count;
    }

    @Override
    public String toString() {
        return "row = " + row + ", col = " + col + ", wall = " + wall;
    }
}

public class Main {
    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        boolean[][][] visited = new boolean[11][N][M];
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        Queue<Node> queue = new ArrayDeque<>();

        visited[0][0][0] = true;
        queue.offer(new Node(0, 0, 0, 1));

        int answer = -1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == N - 1 && current.col == M - 1) {
                if (answer == -1) {
                    answer = current.count;
                } else {
                    answer = Math.min(answer, current.count);
                }
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int row = current.row + dx[i];
                int col = current.col + dy[i];
                if (!isValid(row, col)) continue;
                if (board[row][col] == 1
                        && current.wall < K
                        && !visited[current.wall + 1][row][col]) {
                    queue.offer(new Node(row, col, current.wall + 1, current.count + 1));
                    visited[current.wall + 1][row][col] = true;
                    continue;
                }
                if (board[row][col] == 0
                        && !visited[current.wall][row][col]) {
                    queue.offer(new Node(row, col, current.wall, current.count + 1));
                    visited[current.wall][row][col] = true;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}