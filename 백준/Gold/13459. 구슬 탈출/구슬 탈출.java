import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int count;

    public Node (int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }
}

public class Main {
    private static int dx[] = { -1, 0, 1, 0 };  // 위, 오, 아, 왼
    private static int dy[] = { 0, 1, 0, -1 };
    private static int N, M;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken()); // 세로
        M = Integer.parseInt(tokenizer.nextToken()); // 가로

        board = new char[N][M];
        Node red = null;
        Node blue = null;

        for (int i = 0; i < N; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    red = new Node(i, j, 0);
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    blue = new Node(i, j, 0);
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(red, blue));
    }

    private static int bfs(Node red, Node blue) {
        Deque<Node[]> queue = new ArrayDeque<>();
        queue.offer(new Node[]{ red, blue });

        while (!queue.isEmpty()) {
            Node[] node = queue.poll();

            if (board[node[0].row][node[0].col] == 'O') {
                if (board[node[1].row][node[1].col] == 'O') { // 빨강이 파랑이 동시 탈출
                    return 0;
                }
                return 1;
            }

            for (int i = 0; i < 4; i++) {
                Node nextRed = move(node[0], i, node[1]);
                Node nextBlue = move(node[1], i, nextRed);

                if (canMove(nextRed, i)) {
                    nextRed = move(nextRed, i, nextBlue);
                }
                if (canMove(nextBlue, i)) {
                    nextBlue = move(nextBlue, i, nextRed);
                }

                nextRed.count++;

                if (board[nextBlue.row][nextBlue.col] == 'O') { // 파랑이가 먼저 구멍으로 빠지면 실패
                    continue;
                }
                if (nextRed.count <= 10) {
                    queue.offer(new Node[] { nextRed, nextBlue });
                }
            }
        }

        return 0;
    }

    private static boolean canMove(Node node, int d) {
        return board[node.row + dx[d]][node.col + dy[d]] == '.'
                && board[node.row][node.col] != 'O';
    }

    private static Node move(Node node, int d, Node compare) {
        Node next = new Node(node.row, node.col, node.count);

        while (true) {
            int nextRow = next.row + dx[d];
            int nextCol = next.col + dy[d];

            if (board[nextRow][nextCol] == 'O') {
                return new Node(nextRow, nextCol, next.count);
            }
            if (board[nextRow][nextCol] != '.'
                    || compare.row == nextRow && compare.col == nextCol) {
                return next;
            }
            next.row = nextRow;
            next.col = nextCol;
        }
    }
}