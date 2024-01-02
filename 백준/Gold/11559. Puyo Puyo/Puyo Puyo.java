import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private final static char[][] board = new char[12][6];
    private static boolean[][] visited;
    private static boolean isBomb = false;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int bombCount = 0;

        for (int i = 0; i < 12; i++) {
            board[i] = reader.readLine().toCharArray();
        }


        while (true) {
            start();
            if (!isBomb) {
                break;
            }

            move();
            bombCount++;
            isBomb = false;
        }

        System.out.println(bombCount);
    }

    private static void start() {
        visited = new boolean[12][6];

        for (int row = 0; row < 12; row++) {
            for (int col = 0; col < 6; col++) {
                if (!visited[row][col] && board[row][col] != '.') {
                    bfs(row, col);
                }
            }
        }

        move();
    }

    private static void bfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> points = new LinkedList<>();

        q.offer(new int[]{row, col});
        points.add(new int[]{row, col});
        visited[row][col] = true;

        int[] dx = {-1, 0, 1, 0}; //상, 우, 하, 좌
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextRow = point[0] + dx[i];
                int nextCol = point[1] + dy[i];

                if (isWithinRange(nextRow, nextCol)
                        && !visited[nextRow][nextCol]
                        && board[nextRow][nextCol] == board[row][col]) {
                    q.offer(new int[]{nextRow, nextCol});
                    points.add(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
        }

        if (points.size() >= 4) {
            isBomb = true;
            bomb(points);
        }
    }

    private static boolean isWithinRange(int row, int col) {
        return row >= 0 && row < 12 && col >= 0 && col < 6;
    }

    private static void bomb(List<int[]> points) {
        for (int[] point: points){
            board[point[0]][point[1]] = '.';
        }
    }

    private static void move() {
        for (int row = 11; row >= 0; row--) {
            for (int col = 0; col < 6; col++) {
                if (board[row][col] == '.') continue;

                int nextRow = row + 1;

                while (nextRow < 12 && board[nextRow][col] == '.') {
                    board[nextRow][col] = board[nextRow - 1][col];
                    board[nextRow++ - 1][col] = '.';
                }
            }
        }
    }
}

/*
1. 필드에 여러 색깔의 뿌요를 놓는다
2. 아래에 바닥 or 다른 뿌요가 나올때까지 down
3. 뿌요를 놓은 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요 없어짐(1연쇄)
4. 뿌요가 연쇄된 후 위에 있는 다른 뿌요는 다시 down
5. 아래로 떨어진 후 또 다시 같은 색 뿌요가 4개 이상 있으면 터짐(1연쇄)
(이때 여러 그룹이 동시에 터지는 경우도 1연쇄로 처리)

return 연쇄 개수
 */