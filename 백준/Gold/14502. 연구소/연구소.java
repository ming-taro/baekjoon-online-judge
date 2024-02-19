import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] board;
    private static List<int[]> empty = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    empty.add(new int[]{i, j});
                }
            }
        }

        int emptySize = empty.size();
        int maxSafeSpace = 0;

        for (int i = 0; i < emptySize; i++) {
            for (int j = i + 1; j < emptySize; j++) {
                for (int k = j + 1; k < emptySize; k++) {
                    if (i != j && j != k && k != i) {
                        maxSafeSpace = Math.max(maxSafeSpace, bfs(emptySize, empty.get(i), empty.get(j), empty.get(k)));
                    }
                }
            }
        }

        System.out.println(maxSafeSpace);
    }

    private static int bfs(int safeSpace, int[] first, int[] second, int[] third) {
        boolean[][] visited = new boolean[N][M];

        board[first[0]][first[1]] = 1;
        board[second[0]][second[1]] = 1;
        board[third[0]][third[1]] = 1;

        safeSpace -= 3;  //안전 공간

        int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || board[i][j] != 2) {
                    continue;
                }

                visited[i][j] = true;

                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j});

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();

                    for (int k = 0; k < 4; k++) {
                        int nextRow = current[0] + dx[k];
                        int nextCol = current[1] + dy[k];

                        if (isWithinRange(nextRow, nextCol)
                                && !visited[nextRow][nextCol]
                                && board[nextRow][nextCol] == 0) {
                            safeSpace--;
                            visited[nextRow][nextCol] = true;
                            queue.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
        }

        board[first[0]][first[1]] = 0;
        board[second[0]][second[1]] = 0;
        board[third[0]][third[1]] = 0;

        return safeSpace;
    }

    private static boolean isWithinRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}