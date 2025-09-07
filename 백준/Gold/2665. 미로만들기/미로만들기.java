import java.io.*;
import java.util.*;

class Room {
    int row;
    int col;
    int count;
    public Room(int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }
}

class Main {
    private static int[][] board;
    private static int[][] rooms;
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];
        rooms = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Room> queue = new PriorityQueue<>((o1, o2) -> o1.count - o2.count);
        queue.offer(new Room(0, 0, 0));

        int[][] count = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(count[i], 50 * 50);
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            Room current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int row = current.row + dx[i];
                int col = current.col + dy[i];
                if (row < 0 || row >= N || col < 0 || col >= N) continue;
                if (board[row][col] == 0 && current.count + 1 < count[row][col])  {
                    count[row][col] = current.count + 1;
                    queue.offer(new Room(row, col, current.count + 1));
                } else if (board[row][col] == 1 && current.count < count[row][col]) {
                    count[row][col] = current.count;
                    queue.offer(new Room(row, col, current.count));
                }
            }
        }

        return count[N - 1][N - 1];
    }
}