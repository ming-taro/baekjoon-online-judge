import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int count;
    int d;

    public Node(int row, int col, int count, int d) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.d = d;
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

        st = new StringTokenizer(reader.readLine());
        int[] hong = new int[]{ Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};

        st = new StringTokenizer(reader.readLine());
        int[] exit = new int[]{ Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1};


        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        boolean[][][] visited = new boolean[2][N][M];
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(hong[0], hong[1], 0, 0));
        visited[0][hong[0]][hong[1]] = true;

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        int answer = -1;

        while(!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == exit[0] && current.col == exit[1]) {
                if (answer == -1) answer = current.count;
                else answer = Math.min(answer, current.count);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int row = current.row + dx[i];
                int col = current.col + dy[i];
                if (!isValid(row, col)
                        || current.d == 1 && board[row][col] == 1) continue;

                int d = current.d;
                if (board[row][col] == 1) d = 1;

                if (!visited[d][row][col]) {
                    queue.offer(new Node(row, col, current.count + 1, d));
                    visited[d][row][col] = true;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}