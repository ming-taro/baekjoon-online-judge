import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] board;
    private static boolean[][][] visited;
    private static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new boolean[2][N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int answer = 0;

        for (int i = 0; i < N; i++) { // [x, y] 두 칸씩 비교해서 크거나 작은 경우 경사로 가능성 판단하기
            boolean flag = false;
            for (int j = 1; j < N; j++) { // 행 단위 검사
                if (board[i][j] == board[i][j - 1]) continue; // 1. 같은 층 -> 통과
                if (j + L - 1 < N
                        && board[i][j - 1] - 1 == board[i][j]
                        && isValidRow(i, j)) { // 2. 왼쪽 -> 오른쪽 경사로 : ex) 2 1 1 1 1 ...
                    continue;
                }
                if (j - L >= 0
                        && board[i][j - 1] == board[i][j] - 1
                        && isValidRow(i, j - L)) { // 3. 왼쪽 <- 오른쪽 경사로 : ex) ... 1 1 1 1 2
                    continue;
                }
                flag = true;
                break;
            }
            if (!flag) answer++;

            flag = false;
            for (int j = 1; j < N; j++) { // 열 단위 검사
                if (board[j - 1][i] == board[j][i]) continue; // 1. 같은 층 -> 통과
                if (j + L - 1 < N
                        && board[j - 1][i] - 1 == board[j][i]
                        && isValidCol(j, i)) { // 2. 위 -> 아래 경사로 : ex) 2 1 1 1 1 ...
                    continue;
                }
                if (j - L >= 0
                        && board[j - 1][i] == board[j][i] - 1
                        && isValidCol(j - L, i)) { // 3. 아래 -> 위 경사로 : ex) 1 1 1 1 2 ...
                    continue;
                }
                flag = true;
                break;
            }
            if (!flag) answer++;
        }

        System.out.println(answer);
    }

    private static boolean isValidRow(int row, int col) { // 가로 바닥층이 L개인지 검사, (row, col)은 가장 왼쪽 포인트
        for (int k = 0; k < L; k++) { // L개의 바닥층 검사
            if (board[row][col] != board[row][col + k] || visited[0][row][col + k]) {
                return false;
            }
            visited[0][row][col + k] = true;
        }
        return true;
    }

    private static boolean isValidCol(int row, int col) { // 세로 바닥층이 L개인지 검사, (row, col)은 가장 위쪽 포인트
        for (int k = 0; k < L; k++) {
            if (board[row][col] != board[row + k][col] || visited[1][row + k][col]) {
                return false;
            }
            visited[1][row + k][col] = true;
        }
        return true;
    }
}