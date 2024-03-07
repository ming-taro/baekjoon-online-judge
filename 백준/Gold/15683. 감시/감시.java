import java.io.*;
import java.util.*;

class Point {
    int number;
    int row;
    int col;

    public Point(int number, int row, int col) {
        this.number = number;
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Point{" +
                "number=" + number +
                ", row=" + row +
                ", col=" + col +
                '}';
    }
}

class Camera {
    Point point;
    int[] direction;

    public Camera(Point point, int[] direction) {
        this.point = point;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "point=" + point +
                ", direction=" + Arrays.toString(direction) +
                '}';
    }
}

public class Main {
    private static int N, M;
    private static int[][] board;
    private static List<Point> cctv = new ArrayList<>();
    private static Camera[] cameras;
    private static int emptySpace = 0;
    private static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input.nextToken());
                if (board[i][j] == 6) {
                    continue;
                }

                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    cctv.add(new Point(board[i][j], i, j));
                }
                emptySpace++;
            }
        }

        cameras = new Camera[cctv.size()];
        runCCTV(0);
        System.out.println(minCount);
    }

    private static void runCCTV(int startIndex) {
        if (startIndex == cctv.size()) {
            minCount = Math.min(minCount, emptySpace - calcArea(cameras));
            return;
        }

        for (int i = startIndex; i < cctv.size(); i++) {
            switch (cctv.get(i).number) {
                case 1:
                    for (int j = 0; j < 4; j++) {
                        cameras[i] = new Camera(cctv.get(i), new int[]{j});
                        runCCTV(i + 1);
                    }
                    break;
                case 2:
                    cameras[i] = new Camera(cctv.get(i), new int[]{0, 2});
                    runCCTV(i + 1);
                    cameras[i] = new Camera(cctv.get(i), new int[]{1, 3});
                    runCCTV(i + 1);
                    break;
                case 3:
                    for (int j = 0; j < 4; j++) {
                        cameras[i] = new Camera(cctv.get(i), new int[]{j, (j + 1) % 4});
                        runCCTV(i + 1);
                    }
                    break;
                case 4:
                    for (int j = 0; j < 4; j++) {
                        cameras[i] = new Camera(cctv.get(i), new int[]{j, (j + 1) % 4, (j + 2) % 4});
                        runCCTV(i + 1);
                    }
                    break;
                default:
                    cameras[i] = new Camera(cctv.get(i), new int[]{0, 1, 2, 3});
                    runCCTV(i + 1);
            }
        }
    }

    private static int calcArea(Camera[] cameras) {
        int totalCount = 0;
        boolean[][] visited = new boolean[N][M];

        for (Camera camera: cameras) {
            visited[camera.point.row][camera.point.col] = true;
            totalCount++;
            for (int direction: camera.direction) {   //카메라 방향에 따라 카운트
                totalCount += count(camera.point, direction, visited);
            }
        }

        return totalCount;
    }

    private static int count(Point current, int direction, boolean[][] visited) {
        int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        int row = current.row + dx[direction];
        int col = current.col + dy[direction];
        int totalCount = 0;

        while (row >= 0 && row < N && col >= 0 && col < M) {
            if (board[row][col] == 6) {  //벽에 도달하면 카운트 종료
                return totalCount;
            }
            if (!visited[row][col] && board[row][col] == 0) {  //cctv가 존재하는 위치를 제외하고 카운트
                totalCount++;
                visited[row][col] = true;
            }
            row += dx[direction];
            col += dy[direction];
        }

        return totalCount;
    }
}