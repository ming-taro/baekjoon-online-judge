import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int T;
    private static int[][] castle;
    private static int[][][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        T = Integer.parseInt(input[2]);

        castle = new int[N][M];
        board = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            castle[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        moveWarrior(0, 0);

        if (Math.max(board[N - 1][M - 1][0], board[N - 1][M - 1][1]) == 0) {         //모두 도달하지 못하는 경우
            System.out.println("Fail");
            return;
        }

        int result;

        if (Math.min(board[N - 1][M - 1][0], board[N - 1][M - 1][1]) == 0) {         //둘 중 한 차원만 도달한 경우
            result = Math.max(board[N - 1][M - 1][0], board[N - 1][M - 1][1]);
        } else {                                                                     //모든 차원에서 도달이 가능한 경우
            result = Math.min(board[N - 1][M - 1][0], board[N - 1][M - 1][1]);
        }

        if (result <= T) {
            System.out.println(result);
        } else {
            System.out.println("Fail");
        }
    }

    private static void moveWarrior(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];

        visited[row][col][0] = true;

        int[] dx = {-1, 0, 1, 0};   //상, 우, 하, 좌
        int[] dy = {0, 1, 0, -1};

        q.offer(new Point(row, col, false, 0));

        while (!q.isEmpty()) {
            Point currentPoint = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = currentPoint.row + dx[i];
                int nextCol = currentPoint.col + dy[i];

                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M
                    || visited[nextRow][nextCol][currentPoint.dimension]) {
                    continue;
                }

                if ((castle[nextRow][nextCol] == 0
                    || castle[nextRow][nextCol] == 1 && currentPoint.hasSword)) {
                    q.offer(new Point(nextRow, nextCol, currentPoint.hasSword, currentPoint.dimension));
                    board[nextRow][nextCol][currentPoint.dimension] = board[currentPoint.row][currentPoint.col][currentPoint.dimension] + 1;
                    visited[nextRow][nextCol][currentPoint.dimension] = true;
                }
                if (castle[nextRow][nextCol] == 2) {
                    q.offer(new Point(nextRow, nextCol, true, 1));
                    board[nextRow][nextCol][1] = board[currentPoint.row][currentPoint.col][currentPoint.dimension] + 1;
                    visited[nextRow][nextCol][1] = true;
                }
            }
        }
    }

    static class Point {
        private int row;
        private int col;
        private boolean hasSword;
        private int dimension;

        public Point(int row, int col, boolean hasSword, int dimension) {
            this.row = row;
            this.col = col;
            this.hasSword = hasSword;
            this.dimension = dimension;
        }
    }
}