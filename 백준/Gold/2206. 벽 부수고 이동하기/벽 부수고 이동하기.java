import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int N;
    private static int M;
    private static int[][] board;
    private static int visit[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        board = new int[N][M];
        visit = new int[N][M][2]; //[0]: 벽을 부순 후 이어서 저장, [1]: 벽을 부수지 않은 상태에서 저장

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();

        q.add(new Point(0, 0, 1));
        visit[0][0][1] = 1;

        int[] dx = {-1, 0, 1, 0};  //상, 우, 하, 좌
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Point currentPoint = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = currentPoint.row + dx[i];
                int nextCol = currentPoint.col + dy[i];

                if (!isWithinRange(nextRow, nextCol)) continue;

                //다음 칸이 벽인 경우 -> 해당 경로에서 벽을 부순 적이 없을 때(brick이 1인 경우)만 이동할 수 있음
                if (board[nextRow][nextCol] == 1
                        && currentPoint.brick == 1) {
                    q.offer(new Point(nextRow, nextCol, 0));   //새로운 경로 추가(현재 벽을 부수게 되면서 차원이 1에서 0으로 넘어감)
                    visit[nextRow][nextCol][0] = visit[currentPoint.row][currentPoint.col][1] + 1;  //현재 벽을 부수게 되면서 벽을 부순적이 없는[1]에서 벽을 한번이라도 부순 경로를 저장하는[0]으로 차원을 이동해서 저장함
                    continue;
                }

                //다음에 방문 하려는 칸이 첫 방문인 경우(즉 차원 이동X)
                if (board[nextRow][nextCol] == 0
                        && visit[nextRow][nextCol][currentPoint.brick] == 0) {
                    visit[nextRow][nextCol][currentPoint.brick] = visit[currentPoint.row][currentPoint.col][currentPoint.brick] + 1;
                    q.offer(new Point(nextRow, nextCol, currentPoint.brick));
                }
            }
        }

        if (Math.max(visit[N - 1][M - 1][0], visit[N - 1][M - 1][1]) == 0) {  //두 차원 모두 0 -> 마지막에 도달하지 못함
            return -1;
        }
        if (Math.min(visit[N - 1][M - 1][0], visit[N - 1][M - 1][1]) == 0) {  //한 차원만 도달한 경우
            return Math.max(visit[N - 1][M - 1][0], visit[N - 1][M - 1][1]);
        }
        return Math.min(visit[N - 1][M - 1][0], visit[N - 1][M - 1][1]);      //두 차원 중 path가 적은 값
    }

    private static boolean isWithinRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }

    static class Point {
        int row;
        int col;
        int brick;

        public Point(int row, int col, int brick) {
            this.row = row;
            this.col = col;
            this.brick = brick;
        }
    }
}