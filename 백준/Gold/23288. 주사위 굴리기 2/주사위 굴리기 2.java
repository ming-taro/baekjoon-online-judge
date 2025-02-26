import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] count;

    private static int[] dx = { -1, 0, 1, 0 }; // 위, 오, 아, 왼
    private static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(new int[]{ i, j }, visited);
                }
            }
        }

        System.out.println(run(K));
    }

    private static void bfs(int[] start, boolean[][] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;

        List<int[]> list = new ArrayList<>();
        int total = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            list.add(current);
            total++;

            for (int i = 0; i < 4; i++) {
                int row = current[0] + dx[i];
                int col = current[1] + dy[i];
                if (row >= 0 && row < N && col >= 0 && col < M
                        && board[row][col] == board[start[0]][start[1]]
                        && !visited[row][col]) {
                    queue.offer(new int[]{ row, col });
                    visited[row][col] = true;
                }
            }
        }

        for (int[] l: list) {
            count[l[0]][l[1]] = total;
        }
    }

    private static int run(int K) {
        int[] dice = { 2, 4, 1, 3, 5, 6 };
        int[] current = { 0, 0, 1 }; // row, col, 오

        int score = 0;
        Map<Integer, Integer> reverse = new HashMap<>();
        reverse.put(0, 2);
        reverse.put(2, 0);
        reverse.put(1, 3);
        reverse.put(3, 1);

        for (int i = 0; i < K; i++) {
            // 1. 보드판상 위치 이동
            int row = current[0] + dx[current[2]];
            int col = current[1] + dy[current[2]];
            if (row < 0 || row >= N || col < 0 || col >= M) { // 보드를 벗어남 -> 반대 방향으로 이동
                current[2] = reverse.get(current[2]);
                row = current[0] + dx[current[2]];
                col = current[1] + dy[current[2]];
            }
            current[0] = row;
            current[1] = col;

            // 2. 주사위 굴리기
            if (current[2] == 0) {        // 위
                int tmp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[4];
                dice[4] = dice[5];
                dice[5] = tmp;
            } else if (current[2] == 1) { // 오
                int tmp = dice[3];
                dice[3] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[5];
                dice[5] = tmp;
            } else if (current[2] == 2) { // 아
                int tmp = dice[5];
                dice[5] = dice[4];
                dice[4] = dice[2];
                dice[2] = dice[0];
                dice[0] = tmp;
            } else {                      // 왼
                int tmp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = dice[5];
                dice[5] = tmp;
            }

            // 3. 점수 기록
            score += board[current[0]][current[1]] * count[current[0]][current[1]];

            // 4. 방향 결정
            int A = 7 - dice[2];
            if (A > board[current[0]][current[1]]) {
                current[2] = (current[2] + 1) % 4;
            } else if (A < board[current[0]][current[1]]) {
                current[2] = current[2] == 0 ? 3 : current[2] - 1;
            }
        }

        return score;
    }
}