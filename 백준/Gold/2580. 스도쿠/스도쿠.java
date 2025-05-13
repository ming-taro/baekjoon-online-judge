import java.io.*;
import java.util.*;

class Main {
    private static int N = 9;
    private static int[][] board;
    private static List<int[]> blank = new ArrayList<>();
    private static boolean flag = false;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {
                    blank.add(new int[]{ i, j });
                }
            }
        }

        dfs(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            } System.out.println();
        }
    }

    private static void dfs(int current) {
        if (!flag && current == blank.size()) {
            flag = true;
            return;
        }

        for (int num = 1; num <= N; num++) {
            if (flag) return;
            if (isValid(blank.get(current)[0], blank.get(current)[1], num)) {
                board[blank.get(current)[0]][blank.get(current)[1]] = num;
                dfs(current + 1);
                if (flag) return;
                board[blank.get(current)[0]][blank.get(current)[1]] = 0;
            }
        }
    }

    private static boolean isValid(int row, int col, int num) {
        int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] dy = { 0, -1, 0, 1, 1, 1, 0, -1, -1 };

        int centerRow = 1;
        int centerCol = 1;

        if (row >= 3 && row < 6) centerRow = 4;
        if (row >= 6) centerRow = 7;

        if (col >= 3 && col < 6) centerCol = 4;
        if (col >= 6) centerCol = 7;

        for (int i = 0; i < N; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;

            int nextRow = centerRow + dx[i]; // 사각형 검사
            int nextCol = centerCol + dy[i];
            if (board[nextRow][nextCol] == num) return false;
        }
        return true;
    }
}
/*
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
 */