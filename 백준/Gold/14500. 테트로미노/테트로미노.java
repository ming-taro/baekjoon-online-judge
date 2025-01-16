import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static List<int[]> nodes = new ArrayList<>();
    private static boolean[][] visited;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        nodes.add(new int[]{ 0, -1, 0, 0, 0, 1, 1, 0 }); // ㅜ
        nodes.add(new int[]{ 0, -1, 0, 0, -1, 0, 1, 0 }); // ㅓ
        nodes.add(new int[]{ 0, -1, 0, 0, 0, 1, -1, 0 }); // ㅗ
        nodes.add(new int[]{ -1, 0, 0, 0, 1, 0, 0, 1 }); // ㅏ

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, board[i][j]);
                visited[i][j] = false;
                answer = Math.max(answer, calcMaxSum(i, j));
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int col, int count, int total) {
        if (count == 4) {
            answer = Math.max(answer, total);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            if (isValid(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, count + 1, total + board[nextRow][nextCol]);
                visited[nextRow][nextCol] = false;
            }
        }
    }

    private static int calcMaxSum(int row, int col) {
        int maxSum = 0;

        for (int[] node: nodes) {
            int sum = 0;
            boolean flag = false;
            for (int n = 0; n < node.length; n += 2) {
                int nextRow = row + node[n];
                int nextCol = col + node[n + 1];
                if (!isValid(nextRow, nextCol)) {
                    flag = true;
                    break;
                }
                sum += board[nextRow][nextCol];
            }
            if (!flag) maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
