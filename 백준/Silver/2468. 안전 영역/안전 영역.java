import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int rain = 1;
        int maxSafeArea = 1;
        visited = new boolean[N][N];

        while (true) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (!visited[row][col] && board[row][col] > rain) {
                        calcSafeArea(row, col, rain);
                        count++;
                    }
                }
            }

            if (count == 0) {
                break;
            }

            maxSafeArea = Math.max(maxSafeArea, count);
            rain++;
        }

        System.out.println(maxSafeArea);
    }

    private static void calcSafeArea(int row, int col, int rain) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int[] next = {current[0] + dx[i], current[1] + dy[i]};

                if (next[0] >= 0 && next[0] < N && next[1] >= 0 && next[1] < N
                        && !visited[next[0]][next[1]]
                        && board[next[0]][next[1]] > rain) {
                    queue.offer(next);
                    visited[next[0]][next[1]] = true;
                }
            }
        }
    }
}

/*
지역의 높이 정보 파악 -> 비가 내린 후 물에 잠기지 않은 안전 영역 최대 개수 구하기
 */