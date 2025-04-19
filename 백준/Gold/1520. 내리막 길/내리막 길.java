import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Main {
    private static int M, N;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int[][] board;
    private static boolean[][][] visited;
    private static int[][] active;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new int[M][N]; // M * N
        visited = new boolean[4][M][N];
        active = new int[M][N];
        for (int i = 0; i < M; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        dfs(0, 0);

//        System.out.println("[방문]");
//        for (int t = 0; t < 4; t++) {
//            for (int i = 0; i < M; i++) {
//                System.out.println(Arrays.toString(visited[t][i]));
//            } System.out.println();
//        }
//        System.out.println("[유효]");
//        for (int i = 0; i < M; i++) {
//            System.out.println(Arrays.toString(active[i]));
//        }

        System.out.println(active[0][0]);
    }

    private static int dfs(int row, int col) {
        if (row == M - 1 && col == N - 1) {
            return 1;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            if (isValid(nextRow, nextCol)
                    && board[row][col] > board[nextRow][nextCol]) {
                if (visited[i][nextRow][nextCol]) {
                    continue;
                }

                visited[i][nextRow][nextCol] = true;
                active[row][col] += dfs(nextRow, nextCol);
            }
        }

        return active[row][col];
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < M && col >= 0 && col < N;
    }
}
/*
7 7
100 33 58 59 61 31 30
74 31 55 62 70 70 29
73 98 49 47 11 10 36
62 59 56 45 44 8 7
59 58 54 53 41 7 3
56 32 29 18 40 4 3
34 31 26 40 39 73 1
=>27

4 4
16 9 8 1
15 10 7 2
14 11 6 3
13 12 5 4
=>10
 */