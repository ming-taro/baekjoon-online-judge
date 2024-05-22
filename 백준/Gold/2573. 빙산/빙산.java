import java.io.*;
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
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] visited;
    private static int[][] iceVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        board = new int[N][M];
        visited = new int[N][M];
        iceVisited = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int totalTime = 1;

        while (true) {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (visited[row][col] != totalTime
                            && board[row][col] != 0) {
                        bfs(new Node(row, col), totalTime);
                    }
                }
            }

            int totalIce = 0;

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (iceVisited[row][col] != totalTime) {
                        if (board[row][col] == 0) {
                            iceVisited[row][col]++;
                        } else {
                            checkIce(new Node(row, col), totalTime);
                            totalIce++;
                        }
                    }
                }
            }

            if (totalIce > 1) {
                break;
            }
            if (totalIce == 0) {
                System.out.println(0);
                return;
            }
            totalTime++;
        }

        System.out.println(totalTime);
    }

    private static void checkIce(Node start, int totalTime) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);

        iceVisited[start.row][start.col]++;

        int[] dx = {-1, 0, 1, 0}; // 위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int i = 0; i < 4; i++) { // 빙산 주변 바다 개수
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if (board[nextRow][nextCol] == 0) {
                    iceVisited[nextRow][nextCol]++;
                    continue;
                }
                if (isWithinRange(new Node(nextRow, nextCol))
                        && iceVisited[nextRow][nextCol] != totalTime) {
                    queue.offer(new Node(nextRow, nextCol));
                    iceVisited[nextRow][nextCol]++;
                }
            }
        }
    }

    private static void bfs(Node start, int totalTime) {
        Queue<Node> queue = new LinkedList<>();

        queue.offer(start);
        visited[start.row][start.col]++;

        int[] dx = {-1, 0, 1, 0}; // 위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int total = 0;

            for (int i = 0; i < 4; i++) { // 빙산 주변 바다 개수
                int nextRow = current.row + dx[i];
                int nextCol = current.col + dy[i];

                if (isWithinRange(new Node(nextRow, nextCol))
                        && visited[nextRow][nextCol] != totalTime) {
                    if (board[nextRow][nextCol] == 0) {
                        total++;
                    } else {
                        queue.offer(new Node(nextRow, nextCol));
                        visited[nextRow][nextCol]++; // 다음에 방문할 빙산
                    }
                }
            }

            if (board[current.row][current.col] <= total) {
                board[current.row][current.col] = 0;
            } else {
                board[current.row][current.col] -= total;
            }
        }
    }

    private static boolean isWithinRange(Node node) {
        return node.row >= 0 && node.row < N
                && node.col >= 0 && node.col < M;
    }
}
/*
[board]
->빙산의 각 부분별 높이(바다 == 0)
[빙산]
->1년 - 동서남북 위치에 있는 0의 개수 만큼 줄어듦
->빙산이 두 덩어리 이상으로 분리되는 최초의 시간
 */