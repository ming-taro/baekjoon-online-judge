import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cropCount = 0;

        int[][] board = new int[N + 2][M + 2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) cropCount++;
            }
        }

        if (cropCount == 2 * K) { // 예외1) 2 * K개의 씨앗이 심어져있다면 -> 겹치는 영역X
            System.out.println(0);
            return;
        }
        if (cropCount == K) { // 예외2) K개의 씨앗이 심어져 있다면 -> 완전히 같은 영역
            System.out.println(K);
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= M; c++) {
                    if (board[r][c] == 1) {
                        System.out.println(r + " " + c);
                    }
                }
            }
            return;
        }

        int[] dx = { 0, 1 }; // 오, 아
        int[] dy = { 1, 0 };
        int[][] check = new int[N + 2][M + 2];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (board[r][c] == 0) continue;

                int hCount = 0;
                int vCount = 0;

                for (int k = 0; k < K; k++) {
                    if (c + k <= M && board[r][c + k] == 1) { // 가로
                        hCount++;
                    }
                    if (r + k <= N && board[r + k][c] == 1) { // 세로
                        vCount++;
                    }
                }

                // 가로로 심었다 -> 처음 칸의 왼쪽 or 마지막 칸의 오른쪽이 0이어야 함
                if (hCount == K
                        && (board[r][c - 1] == 0 || board[r][c + K] == 0)) {
                    for (int k = 0; k < K; k++) {
                        check[r][c + k]++;
                    }
                }
                // 세로로 심었다 -> 처음 칸의 위쪽 or 마지막 칸의 아래쪽이 0이이어야 함
                if (vCount == K
                        && (board[r - 1][c] == 0 || board[r + K][c] == 0)) {
                    for (int k = 0; k < K; k++) {
                        check[r + k][c]++;
                    }
                }
            }
        }

        List<int[]> result = new ArrayList<>();
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                if (check[r][c] == 2) {
                    result.add(new int[]{ r, c });
                }
            }
        }

        System.out.println(result.size());
        for (int[] r: result) {
            System.out.println(r[0] + " " + r[1]);
        }
    }
}
/*
1 7 3
0 1 1 1 1 1 0

1 7 4
0 1 1 1 1 1 0

1 7 5
0 1 1 1 1 1 0

2 2 2
1 1
1 0
*/