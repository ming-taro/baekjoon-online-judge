import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int row;
    int col;
    int d;
    int mirror;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Node(int row, int col, int d, int mirror) {
        this.row = row;
        this.col = col;
        this.d = d;
        this.mirror = mirror;
    }

    @Override
    public int compareTo(Node node) {
        return this.mirror - node.mirror;
    }
}

class Main {
    private static char[][] board;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new char[N][N];

        Node start = null;
        Node end = null;
        int index = 0;

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == '#') {
                    if (index == 0) {
                        start = new Node(i, j);
                        index++;
                    } else {
                        end = new Node(i, j);
                    }
                }
            }
        }

        System.out.println(bfs(start, end));

    }

    private static int bfs(Node start, Node end) {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[][][] visited = new boolean[4][N][N];
        for (int d = 0; d < 4; d++) {
            queue.offer(new Node(start.row, start.col, d, 0));
        }

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == end.row && current.col == end.col) {
                return current.mirror;
            }
            if (visited[current.d][current.row][current.col]) continue;
            visited[current.d][current.row][current.col] = true;

            int row = current.row + dx[current.d];
            int col = current.col + dy[current.d];
            if (!isValid(row, col) || visited[current.d][row][col] || board[row][col] == '*') continue;

            if (board[row][col] == '!') {
                int left = current.d == 0 ? 3 : current.d - 1;
                int right = current.d == 3 ? 0 : current.d + 1;
                queue.offer(new Node(row, col, left, current.mirror + 1));
                queue.offer(new Node(row, col, right, current.mirror + 1));
            }
            queue.offer(new Node(row, col, current.d, current.mirror));
        }

        return -1;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N && board[row][col] != '*';
    }
}