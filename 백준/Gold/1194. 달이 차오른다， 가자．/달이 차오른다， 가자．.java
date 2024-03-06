import java.io.*;
import java.util.*;

class Point {
    int row;
    int col;
    int count;
    int key; //key : fedcba = 111111(2^6 = 64개의 키 종류가 존재함)

    public Point(int row, int col, int count, int key) {
        this.row = row;
        this.col = col;
        this.count = count;
        this.key = key;
    }

    public void addKey(char key) {
        int newKey = 1 << key - 'a';
        this.key |= newKey;
    }

    public boolean hasKey(char door) {
        int newKey = 1 << door - 'A';
        return !Integer.toBinaryString(this.key & newKey).equals("0");
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", col=" + col +
                ", count=" + count +
                ", key=" + key +
                '}';
    }
}

public class Main {
    private static int N, M;
    private static char[][] board;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        Point start = new Point(0, 0, 0, 0);

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == '0') {
                    start = new Point(i, j, 0, 0);
                }
            }
        }

        System.out.println(bfs(start));
    }

    private static int bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        int minCount = Integer.MAX_VALUE;

        boolean[][][] visited = new boolean[N][M][64];
        visited[start.row][start.col][start.key] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (board[current.row][current.col] == '1') {       //출구에 도착함
                minCount = Math.min(minCount, current.count);
            }

            for (int i = 0; i < 4; i++) {
                Point next = new Point(current.row + dx[i], current.col + dy[i], current.count + 1, current.key);

                if (!isWithinRange(next)  //범위벗어남 or 벽 or 해당 키를 가지고 이미 방문 했던 곳 or 문을 만났는데 키가 없는 경우
                        || board[next.row][next.col] == '#'
                        || visited[next.row][next.col][next.key]
                        || board[next.row][next.col] >= 'A' && board[next.row][next.col] <= 'F' && !next.hasKey(board[next.row][next.col])) {
                    continue;
                }

                if (board[next.row][next.col] >= 'a' && board[next.row][next.col] <= 'f') {  //키 업데이트
                    next.addKey(board[next.row][next.col]);
                }

                queue.offer(next);
                visited[next.row][next.col][next.key] = true;
            }
        }

        if (minCount == Integer.MAX_VALUE) {
            return -1;
        }
        return minCount;
    }

    private static boolean isWithinRange(Point point) {
        return point.row >= 0 && point.row < N && point.col >= 0 && point.col < M;
    }
}