import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N = 5;
    private static Map<Integer, int[]> comb = new HashMap<>();
    private static boolean[][] crew;
    private static char[][] board;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        board = new char[N][N];
        crew = new boolean[N][N];
        int number = 0;
        for(int i = 0; i < N; i++) {
            board[i] = reader.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                comb.put(number++, new int[]{ i, j });
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int current, int depth) {
        if (depth == 7) {
            if (check(comb.get(current - 1))) {
                answer++;
            }
            return;
        }

        for (int i = current; i < N * N; i++) {
            crew[comb.get(i)[0]][comb.get(i)[1]] = true;
            dfs(i + 1, depth + 1);
            crew[comb.get(i)[0]][comb.get(i)[1]] = false;
        }
    }

    private static boolean check(int[] start) {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[][] visited = new boolean[N][N];
        visited[start[0]][start[1]] = true;

        int total = 1;
        int sCount = 0;

        if (board[start[0]][start[1]] == 'S') sCount = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int[] next = new int[]{ current[0] + dx[i], current[1] + dy[i] };
                if (isValid(next) && !visited[next[0]][next[1]] && crew[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.offer(next);
                    if (board[next[0]][next[1]] == 'S') sCount++;
                    total++;
                }
            }
        }

        if (total == 7 && sCount >= 4) return true;
        return false;
    }

    private static boolean isValid(int[] node) {
        return node[0] >= 0 && node[0] < N && node[1] >= 0 && node[1] < N;
    }
}