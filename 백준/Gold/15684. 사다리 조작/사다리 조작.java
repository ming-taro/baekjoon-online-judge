import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[][] board;
    private static List<int[]> list = new ArrayList<>();
    private static int N, M, H;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new boolean[H + 2][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = true;
        }

        for (int r = 1; r <= H; r++) {
            for (int c = 1; c < N; c++) {
                if (!board[r][c - 1] && !board[r][c] && !board[r][c + 1]) {
                    list.add(new int[]{r, c});
                }
            }
        }

        dfs(0, 0);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(answer);
    }

    private static void dfs(int current, int depth) {
        if (isValid()) {
            answer = Math.min(answer, depth);
        }
        if (depth == 3) return;

        for (int i = current; i < list.size(); i++) {
            int[] target = list.get(i);
            if (!board[target[0]][target[1] - 1] && !board[target[0]][target[1] + 1]) {
                board[target[0]][target[1]] = true;
                dfs(i + 1, depth + 1);
                board[target[0]][target[1]] = false;
            }
        }
    }

    private static boolean isValid() {
        for (int c = 1; c <= N; c++) {
            int[] current = new int[]{1, c};
            boolean flag = false;
            while (true) {
                if (current[0] == H + 1) {
                    if (current[1] == c) break;
                    else return false;
                }

                if (!flag && board[current[0]][current[1]]) { // 오른쪽 방향 사다리
                    current[1]++;
                    flag = true;
                    continue;
                }
                if (!flag && board[current[0]][current[1] - 1]) { // 왼쪽 사다리
                    current[1]--;
                    flag = true;
                    continue;
                }
                current[0]++;
                flag = false;
            }
        }

        return true;
    }
}