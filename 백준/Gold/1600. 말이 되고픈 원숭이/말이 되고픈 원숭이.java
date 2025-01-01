import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int kCount;
    int total;
    public Node(int row, int col, int kCount, int total) {
        this.row = row;
        this.col = col;
        this.kCount = kCount;
        this.total = total;
    }
}

public class Main {
    private static int[] dx1 = { -1, 0, 1, 0 };
    private static int[] dy1 = { 0, 1, 0, -1 };
    private static int[] dx2 = { -1, -2, -2, -1, 1, 2, 2, 1 };
    private static int[] dy2 = { -2, -1, 1, 2, 2, 1, -1, -2 };
    private static int[][] board;
    private static int K, W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];
        for (int i = 0; i < H; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, 0, 0));
        int[][][] visited = new int[K + 1][H][W];

        for (int k = 0; k <= K; k++) {
            for (int r = 0; r < H; r++) {
                Arrays.fill(visited[k][r], Integer.MAX_VALUE);
            }
            visited[k][0][0] = 0;
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == H - 1 && current.col == W - 1) {
                return current.total;
            }

            for (int i = 0; i < 8; i++) {
                Node next = new Node(current.row + dx2[i], current.col + dy2[i], current.kCount + 1, current.total + 1);
                if (isValid(next)
                        && next.kCount <= K
                        && board[next.row][next.col] == 0
                        && next.total < visited[next.kCount][next.row][next.col]) {
                    visited[next.kCount][next.row][next.col] = next.total;
                    queue.offer(next);
                }
            }

            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx1[i], current.col + dy1[i], current.kCount, current.total + 1);
                if (isValid(next)
                        && board[next.row][next.col] == 0
                        && next.total < visited[next.kCount][next.row][next.col]) {
                    visited[next.kCount][next.row][next.col] = next.total;
                    queue.offer(next);
                }
            }
        }

        return -1;
    }

    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < H && node.col >= 0 && node.col < W;
    }
}