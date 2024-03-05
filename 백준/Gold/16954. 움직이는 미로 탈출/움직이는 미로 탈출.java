import java.io.*;
import java.util.*;

class Point {
    int row;
    int col;
    int time;

    public Point(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Point{" +
                "row=" + row +
                ", col=" + col +
                ", time=" + time +
                '}';
    }
}

public class Main {
    private static char[][] board;
    private static List<Point> walls = new ArrayList<>();
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        board = new char[8][8];
        visited = new boolean[8][8];

        for (int i = 0; i < 8; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == '#') {
                    walls.add(new Point(i, j, 0));
                }
            }
        }

        Point current = new Point(7, 0, 0);  //->(0, 7)로 가야 함
        System.out.println(move(current));
    }

    private static int move(Point start) {
        int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}; //그대로, 위, 위오, 오, 아오, 아, 아왼, 왼, 왼위
        int[] dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};

        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        int currentTime = 0;

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            visited[current.row][current.col] = false;
            if (current.row == 0 && current.col == 7) {  //집에 도착 -> 성공
                return 1;
            }
            if (current.time > 0 && current.time > currentTime) { //해당 위치에서 캐릭터를 움직이기 전, 이전 움직임에서 캐릭터가 모든 이동을 완료했다면 벽을 움직임
                moveWall();
                currentTime = current.time;
            }

            if (board[current.row][current.col] == '#') {  //벽이 캐릭터 위치로 오면 종료
                continue;
            }

            for (int i = 0; i < 9; i++) {
                Point next = new Point(current.row + dx[i], current.col + dy[i], current.time + 1);
                if (isWithinRange(next)
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] == '.' ) {
                    queue.offer(next);
                    visited[next.row][next.col] = true;
                }

            }
        }

        return 0;
    }

    private static void moveWall() {
        List<Point> newWall = new ArrayList<>();

        for(Point wall: walls) {
            board[wall.row][wall.col] = '.';
            Point next = new Point(wall.row + 1, wall.col, 0);
            if (isWithinRange(next)) {
                newWall.add(next);
            }
        }

        walls = newWall;

        for(Point wall: walls) {
            board[wall.row][wall.col] = '#';
        }
    }

    private static boolean isWithinRange(Point point) {
        return point.row >= 0 && point.row < 8 && point.col >= 0 && point.col < 8;
    }
}
/*
1초마다 벽이 아래로 한칸씩 내려감 -> 더이상 내려갈 수 없다면 사라짐
캐릭터 -> 1초에 8방향으로 한 칸 이동 or 현재 자리에 머무름
벽이 캐릭터 칸으로 이동 -> 더 이상 움직일 수 X
 */