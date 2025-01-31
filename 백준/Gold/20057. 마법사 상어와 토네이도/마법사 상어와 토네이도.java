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
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(run());
    }

    private static int run() {
        int[] dx = { 0, 1, 0, -1 }; // 왼, 아, 오, 위
        int[] dy = { -1, 0, 1, 0 };

        int row = N / 2;
        int col = N / 2;
        int count = 1;   // d 방향으로 1칸, 1칸, 2칸, 2칸, 3칸, 3칸....씩 움직임
        int d = 0;       // 왼쪽 방향부터 시작
        int answer = 0;

        while (true) {
            for (int loop = 0; loop < 2; loop++) {
                for (int i = 0; i < count; i++) {     // d 방향으로 count 칸씩 이동
                    row += dx[d];
                    col += dy[d];
                    answer += calcSand(d, row, col);
                    if (row == 0 && col == 0) {       // 토네이도 소멸
                        return answer;
                    }
                }
                d++; // 다음 방향으로 진행
                if (d > 3) d = 0;
            }
            count++;
        }
    }

    private static int calcSand(int d, int row, int col) {
        // 위, 왼, 아, 오 방향 위치값
        int[][] dx = {{ -2, -1, -1, -1 }, { 0, 1, 0, -1 }, { 2, 1, 1, 1 }, { 0, -1, 0, 1 }};
        int[][] dy = {{ 0, -1, 0, 1 }, { -2, -1, -1, -1 }, { 0, 1, 0, -1 }, { 2, 1, 1, 1 }};
        double[] value = { 0.02, 0.1, 0.07, 0.01, 0.02, 0.01, 0.07, 0.1 };

        int[][] direction = {{ 0, 2 }, { 1, 3 }, { 2, 0 }, { 3, 1 }}; // 0도, 90도, 180도, 270도 회전시 방향값
        int[][] left = {{ 0, -2 }, { 2, 0 }, { 0, 2 }, { -2, 0 }};    // 5% 비율 위치
        int[][] alpha = {{ 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 }};   // 알파 위치

        int originSand = board[row][col];
        board[row][col] -= originSand;

        int remain = originSand;
        int answer = 0;
        int vIndex = 0;

        for (int index: direction[d]) {               // 1. 위아래 비율칸으로 이동
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[index][i];
                int nextCol = col + dy[index][i];
                int sand = (int) (originSand * value[vIndex++]);
                remain -= sand;

                if (isValid(nextRow, nextCol)) {
                    board[nextRow][nextCol] += sand;
                } else { // 격자 밖으로 나간 모래
                    answer += sand;
                }
            }
        }

        int leftRow = row + left[d][0];                // 2. 왼쪽 비율칸으로 이동
        int leftCol = col + left[d][1];
        int sand = (int) (originSand * 0.05);
        remain -= sand;
        if (isValid(leftRow, leftCol)) {
            board[leftRow][leftCol] += sand;
        } else { // 격자 밖으로 나간 모래
            answer += sand;
        }

        int aRow = row + alpha[d][0];
        int aCol = col + alpha[d][1];
        if (isValid(aRow, aCol)) {
            board[aRow][aCol] += remain; // 3. 알파 칸으로 남은 모래 이동
        } else {
            answer += remain;
        }

        return answer;
    }

    private static boolean isValid (int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}