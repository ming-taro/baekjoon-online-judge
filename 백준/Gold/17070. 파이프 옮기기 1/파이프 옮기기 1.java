import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;
    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int DIAGONAL= 2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        if (board[N - 1][N - 1] == 1) {
            System.out.println(0);
            return;
        }

        int[] start = new int[] { 0, 0, 0, 1};

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        int answer = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (current[2] == N - 1 && current[3] == N - 1) {
                answer++;
                continue;
            }

            if (calc(current) == HORIZONTAL || calc(current) == DIAGONAL) {
                int[] next = new int[] { current[2], current[3], current[2], current[3] + 1 }; // →
                if (isValid(next)) queue.offer(next);
            }

            if (calc(current) == VERTICAL || calc(current) == DIAGONAL) {
                int[] next = new int[] { current[2], current[3], current[2] + 1, current[3]}; // ↓
                if (isValid(next)) queue.offer(next);
            }

            int[] next = new int[] { current[2], current[3], current[2] + 1, current[3] + 1};
            if (isValid(next)
                    && board[current[2]][current[3] + 1] == 0
                    && board[current[2] + 1][current[3]] == 0) { // ↘
                queue.offer(next);
            }
        }

        System.out.println(answer);
    }

    private static int calc(int[] node) {
        if (node[0] == node[2] && node[1] + 1 == node[3]) return HORIZONTAL;
        if (node[0] + 1 == node[2] && node[1] == node[3]) return VERTICAL;
        return DIAGONAL;
    }

    private static boolean isValid(int[] node) {
        return node[0] >= 0 && node[0] < N && node[1] >= 0 && node[1] < N
                && node[2] >= 0 && node[2] < N && node[3] >= 0 && node[3] < N
                && board[node[0]][node[1]] == 0 && board[node[2]][node[3]] == 0;
    }
}