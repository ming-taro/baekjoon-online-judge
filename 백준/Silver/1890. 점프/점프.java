import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        long[][] check = new long[N][N];
        if (board[0][0] < N) {
            check[0][board[0][0]] = 1;
            check[board[0][0]][0] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 && j == 0 || board[i][j] == 0 || check[i][j] == 0) continue;
                int nextCol = j + board[i][j];
                int nextRow = i + board[i][j];
                if (nextCol < N) check[i][nextCol] += check[i][j];
                if (nextRow < N) check[nextRow][j] += check[i][j];
            }
        }

        System.out.println(check[N - 1][N - 1]);
    }
}