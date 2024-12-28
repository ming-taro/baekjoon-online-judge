import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int count;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public Node(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }
}

public class Main {
    private static int N, M;
    private static int[][] board;
    private static boolean[][] air;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    nodes.add(new Node(i, j));
                }
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int answer = 0;

        while (!nodes.isEmpty()) {
            checkAir();
            List<Integer> target = new ArrayList<>();
            List<Node> newNodes = new ArrayList<>();

            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                int count = 0;
                for (int j = 0; j < 4; j++) {
                    Node next = new Node(node.row + dx[j], node.col + dy[j]);
                    if (board[next.row][next.col] == 0 && air[next.row][next.col]) {
                        count++;
                    }
                }
                if (count >= 2) {
                    target.add(i);
                } else {
                    newNodes.add(nodes.get(i));
                }
            }

            for (int index: target) {
                board[nodes.get(index).row][nodes.get(index).col] = 0;
            }

            nodes = newNodes;
            answer++;
        }

        System.out.println(answer);
    }

    private static void checkAir() {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0));

        air = new boolean[N][M];
        air[0][0] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i]);
                if (isValid(next) && !air[next.row][next.col]&& board[next.row][next.col] == 0) {
                    air[next.row][next.col] = true;
                    queue.offer(next);
                }
            }
        }
    }

    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < M;
    }
}