import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int d;
    int mirror;
    public Node(int row, int col, int d, int mirror) {
        this.row = row;
        this.col = col;
        this.d = d;
        this.mirror = mirror;
    }
}

public class Main {
    private static int W;
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        char[][] board = new char[H][W];
        int[][] start = new int[2][2];
        int index = 0;

        for (int i = 0; i < H; i++) {
            String input = reader.readLine();
            for (int j = 0; j < W; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'C') {
                    start[index][0] = i;
                    start[index++][1] = j;
                }
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        Queue<Node> queue = new ArrayDeque<>();
        int[][][] visited = new int[4][H][W];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < 4; i++) {
            int row = start[0][0] + dx[i];
            int col = start[0][1] + dy[i];
            if (isValid(row, col) && board[row][col] != '*') { // 초기 검사 시작 지점 -> 시작점으로부터 4방향
                queue.offer(new Node(row, col, i, 0));
                visited[i][row][col] = 0;
            }
            visited[i][start[0][0]][start[0][1]] = 0; // 4방향 시작점도 미리 방문 표시
        }

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == start[1][0] && current.col == start[1][1]) {
                answer = Math.min(answer, current.mirror);
            }

            for (int i = -1; i <= 1; i++) { // 레이저는 뒤로 못쏨
                int d = current.d + i;
                if (d < 0) d = 3;
                if (d > 3) d = 0;

                int mirror = current.mirror;
                if (i != 0) mirror++;

                Node next = new Node(current.row + dx[d], current.col + dy[d], d, mirror);
                if (isValid(next.row, next.col)
                        && board[next.row][next.col] != '*'
                        && next.mirror < visited[d][next.row][next.col]) {
                    visited[d][next.row][next.col] = next.mirror;
                    queue.offer(next);
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < H && col >= 0 && col < W;
    }
}