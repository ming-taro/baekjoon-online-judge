import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        boolean[][] visited = new boolean[101][101];

        int[] dx = { 0, -1, 0, 1 };  // 오(0), 위(1), 왼(2), 아(3)
        int[] dy = { 1, 0, -1, 0 };
        int[] next = { 1, 2, 3, 0 }; // 오, 위, 왼, 아 다음 방향 : 오 -> 위, 위 -> 왼, 왼 -> 아, 아 -> 오

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            List<Integer> direction = new ArrayList<>();
            direction.add(d);

            int row = y + dx[d];
            int col = x + dy[d];
            visited[y][x] = true;
            if (isValid(row, col)) {
                visited[row][col] = true; // 0세대
            }

            for (int j = 0; j < g; j++) {
                for (int k = direction.size() - 1; k >= 0; k--) { // 다음 세대 = 현재 세대의 모든 위치에 대해 역순으로 순회하며 90도 회전
                    int nextDirection = next[direction.get(k)];
                    direction.add(nextDirection);
                    row += dx[nextDirection];
                    col += dy[nextDirection];
                    if (isValid(row, col)) {
                        visited[row][col] = true;
                    }
                }
            }
        }

        int answer = 0;
        for (int r = 0; r < 100; r++) {
            for (int c = 0; c < 100; c++) {
                if (visited[r][c] && visited[r][c + 1]
                        && visited[r + 1][c] && visited[r + 1][c + 1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row <= 100 && col >= 0 && col <= 100;
    }
}