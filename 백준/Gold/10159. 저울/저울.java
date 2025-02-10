import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        boolean[][] board = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1; // x > y
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[y][x] = true;
        }

        for (int i = 0; i < N; i++) { // 거쳐가는 노드
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (board[j][i] && board[i][k]) {
                        board[j][k] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (i != j && (!board[i][j] && !board[j][i])) count++;
            }
            System.out.println(count);
        }
    }
}