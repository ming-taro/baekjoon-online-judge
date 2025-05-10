import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int M;
    private static int[][] wood;
    private static boolean[][] visited;
    private static int[][] next = {{ 0, -1, 1, 0 }, { -1, 0, 0, -1 }, { -1, 0, 0, 1}, { 0, 1, 1, 0 }};
    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken()); // N * M 크기 나무
        M = Integer.parseInt(st.nextToken());

        wood = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            wood[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        if (N < 2 && M < 2) {
            System.out.println(0);
        } else {
            dfs(0, 0, 0);
            System.out.println(answer);
        }
    }

    private static void dfs(int total, int row, int col) {
        answer = Math.max(answer, total);

        for (int r = row; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (r == row && c < col) continue;
                if (visited[r][c]) continue;
                for (int i = 0; i < 4; i++) {
                    int r1 = r + next[i][0];
                    int c1 = c + next[i][1];
                    int r2 = r + next[i][2];
                    int c2 = c + next[i][3];
                    if (isValid(r1, c1) && isValid(r2, c2)) {
                        visited[r][c] = true;
                        visited[r1][c1] = true;
                        visited[r2][c2] = true;
                        dfs(total + wood[r][c] * 2 + wood[r1][c1] + wood[r2][c2], r, c);
                        visited[r][c] = false;
                        visited[r1][c1] = false;
                        visited[r2][c2] = false;
                    }
                }
            }
        }
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M && !visited[row][col];
    }
}
/*
나무 N * M -> 강도가 칸마다 다름
부메랑 -> ㄱ자 모양 4가지
부메랑 중심 -> 강도 영향 2배
 */