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
    private static int R, C;
    private static Node J;
    private static List<Node> F = new ArrayList<>();
    private static char[][] board;
    private static int[][][] move;
    private static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        move = new int[R][C][2];

        for (int i = 0; i < R; i++) {
            board[i] = reader.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'J') {
                    J = new Node(i, j);
                } else if (board[i][j] == 'F') {
                    F.add(new Node(i, j));
                }
            }
        }

        moveFire();
        moveJ();

        if (minTime == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(minTime);
    }

    private static void moveFire() {
        int index = 0;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        for (Node node : F) {
            queue.offer(node);
            visited[node.row][node.col] = true;
            move[node.row][node.col][index] = 1;
        }

        int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (next.row >= 0 && next.row < R && next.col >= 0 && next.col < C
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] == '.') {
                    move[next.row][next.col][index] = move[current.row][current.col][index] + 1;
                    visited[next.row][next.col] = true;
                    queue.offer(next);
                }
            }
        }
    }

    private static void moveJ() {
        int index = 1;
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        queue.offer(J);
        visited[J.row][J.col] = true;
        move[J.row][J.col][index] = 1;

        int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (isEdge(current)) {
                minTime = Math.min(minTime, move[current.row][current.col][1]);
            }

            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (next.row >= 0 && next.row < R && next.col >= 0 && next.col < C
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] == '.') {
                    if (move[next.row][next.col][0] > 0
                            && move[current.row][current.col][1] + 1 >= move[next.row][next.col][0]) { //불이 지나갈 길 && 불이 먼저 도착 => 지훈이가 갈 수 없는 길
                        continue;
                    }
                    move[next.row][next.col][1] = move[current.row][current.col][1] + 1;
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                }
            }
        }
    }

    private static boolean isEdge(Node node) {
        return (node.row == 0 || node.row == R - 1 || node.col == 0 || node.col == C - 1)
                && (board[node.row][node.col] != '#');
    }
}