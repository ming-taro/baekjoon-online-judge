import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new int[N][N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) {
                total += board[i][j];
            }
        }

        int answer = total;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer = Math.min(answer, run(r, c, total));
            }
        }
        System.out.println(answer);
    }

    private static int run(int x, int y, int total) {
        int answer = total;

        for (int d1 = 1; d1 < N; d1++) {
            for (int d2 = 1; d2 < N; d2++) {
                if (!(x + d1 + d2 < N && y >= d1 && y + d2 < N)) {
                    break;
                }
                int[][] check = new int[N][N];

                for (int i = 0; i <= d1; i++) {
                    check[x + i][y - i] = 5;           // 1번 경계선
                    check[x + d2 + i][y + d2 - i] = 5; // 4번 경계선
                }
                for (int i = 0; i <= d2; i++) {
                    check[x + i][y + i] = 5;           // 2번 경계선
                    check[x + d1 + i][y - d1 + i] = 5; // 3번 경계선
                }

                int[] sum = new int[5];

                // 1번 선거구
                for (int r = 0; r < x + d1; r++) {
                    for (int c = 0; c <= y; c++) {
                        if (check[r][c] == 5) break;
                        sum[0] += board[r][c];
                    }
                }

                // 2번 선거구
                for (int r = 0; r <= x + d2; r++) {
                    for (int c = N - 1; c >= y + 1; c--) {
                        if (check[r][c] == 5) break;
                        sum[1] += board[r][c];
                    }
                }

                // 3번 선거구
                for (int r = x + d1; r < N; r++) {
                    for (int c = 0; c < y - d1 + d2; c++) {
                        if (check[r][c] == 5) break;
                        sum[2] += board[r][c];
                    }
                }

                // 4번 선거구
                for (int r = x + d2 + 1; r < N; r++) {
                    for (int c = N - 1; c >= y + d2 - d1; c--) {
                        if (check[r][c] == 5) break;
                        sum[3] += board[r][c];
                    }
                }

                sum[4] = total - (sum[0] + sum[1] + sum[2] + sum[3]);
                answer = Math.min(answer, calcDifference(sum));
            }
        }

        return answer;
    }

    private static int calcDifference(int[] sum) {
        int min = sum[0];
        int max = sum[0];
        for (int num: sum) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return max - min;
    }
}