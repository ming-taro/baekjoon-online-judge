import java.io.*;
import java.util.*;

class Point {
    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    private static int[][] board;
    private static int lastCheese = 0;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }

        int result = 0;
        int time = 0;

        while(true) {
            lastCheese = 0;
            findEdge();  //테두리를 한 층 녹일때마다 다시 탐색해주어야 치즈 안의 공기층을 외부로 인식 하지 않음

            if (lastCheese == 0) {  //치즈가 모두 녹음
                break;
            }
            
            result = lastCheese;
            time++;
        }

        System.out.println(time);
        System.out.println(result);
    }

    private static void findEdge() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        int[] dx = {-1, 0, 1, 0};  //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                Point next = new Point(current.row + dx[i], current.col + dy[i]);
                if (next.row >= 0 && next.row < N
                        && next.col >= 0 && next.col < M
                        && !visited[next.row][next.col]) {
                    if (board[next.row][next.col] == 1) {  //치즈의 테두리 -> 0으로 바꾸고 방문 표시를 하면 이번 탐색에서는 더 이상 치즈 안쪽까지 탐색하지 X
                        board[next.row][next.col] = 0;
                        lastCheese++;  //치즈의 테두리 개수 카운트
                    } else {           //0인 경우는 계속 탐색, 1인 경우는 치즈의 테두리 부분으로 해당 경로의 탐색을 종료하기 때문에 큐에 넣지 X
                        queue.offer(next);
                    }
                    visited[next.row][next.col] = true;
                }
            }
        }
    }
}
/*
->정사각형 모양의 판
->가장자리 X표시는 치즈 X, 치즈에는 하나 이상의 구멍이 있을 수 O
->치즈의 가장자리는 1시간마다 녹음
return 치즈가 녹아 없어지는 총 시간 && 마지막 치즈칸의 개수
*/