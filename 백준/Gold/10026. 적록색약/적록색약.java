import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    public Node (int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    private static int N;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = reader.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[N][N];
        int count = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    bfs(new Node(r, c), visited, 0);
                    count++;
                }
            }
        }
        System.out.print(count + " ");

        visited = new boolean[N][N];
        count = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    bfs(new Node(r, c), visited, 1);
                    count++;
                }
            }
        }
        System.out.print(count);
    }

    private static void bfs(Node start, boolean[][] visited, int flag) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.row][start.col] = true;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (!isValid(next) || visited[next.row][next.col]) continue;

                if (board[next.row][next.col] == board[current.row][current.col]
                    || flag == 1
                        && (board[next.row][next.col] == 'R' && board[current.row][current.col] == 'G'
                            || board[next.row][next.col] == 'G' && board[current.row][current.col] == 'R')) {
                    visited[next.row][next.col] = true;
                    queue.offer(next);
                }
            }
        }
    }

    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < N;
    }
}