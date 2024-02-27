import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int distance;

    public Node(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "row:" + row + ", col:" + col + " > distance: " + distance;
    }
}

public class Main {
    private static char[][] board;
    private static int N, M;
    private static int maxDistance = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = reader.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'L') {
                    bfs(i, j);
                }
            }
        }

        System.out.println(maxDistance);
    }

    private static void bfs(int row, int col) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(row, col, 0));

        boolean[][] visited = new boolean[N][M];
        visited[row][col] = true;

        int[] dx = {-1, 0, 1, 0};  //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            maxDistance = Math.max(maxDistance, current.distance);

            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i], current.distance + 1);
                if (isWithinRange(next)
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] == 'L') {
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                }
            }
        }
    }

    private static boolean isWithinRange(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < M;
    }
}
/*
->육지(L), 바다(W)
->상하좌우 이웃한 육지만 이동 가능 == 1시간
->같은 곳을 두 번 이상X, 멀리 돌아가서도 X
*/