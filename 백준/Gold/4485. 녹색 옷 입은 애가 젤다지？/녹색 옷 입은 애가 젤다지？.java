import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int test = 1;
        while (true) {
            int N = Integer.parseInt(reader.readLine());
            if (N == 0) break;

            int[][] board = new int[N][N];
            for (int i = 0; i < N; i++) {
                board[i] = Arrays.stream(reader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
            System.out.println("Problem " + test++ + ": " + bfs(board, N));
        }
    }

    private static int bfs(int[][] board, int N) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));

        int[][] cost = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        cost[0][0] = board[0][0];
        visited[0][0] = true;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (!isValid(next, N)) continue;
                int newCost = cost[current.row][current.col] + board[next.row][next.col];
                if (!visited[next.row][next.col] || newCost < cost[next.row][next.col]) {
                    cost[next.row][next.col] = newCost;
                    visited[next.row][next.col] = true;
                    queue.offer(next);
                }
            }
        }

        return cost[N - 1][N - 1];
    }

    private static boolean isValid(Node node, int N) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < N;
    }
}