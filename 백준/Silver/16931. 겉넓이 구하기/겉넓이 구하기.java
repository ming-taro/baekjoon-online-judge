import java.io.*;
import java.util.*;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int sideArea = 0;

        for (int row = 0; row < N; row++) {
            sideArea += board[row][0] + board[row][M - 1];  //가장 왼쪽, 오른쪽 면
            for (int col = 0; col < M; col++) {
                if (col < M - 1 && board[row][col] > board[row][col + 1]) { //해당칸 오른쪽 면적
                    sideArea += board[row][col] - board[row][col + 1];
                }
                if (col > 0 && board[row][col] > board[row][col - 1]) {    //해당칸 왼쪽 면적
                    sideArea += board[row][col] - board[row][col - 1];
                }
            }
        }

        for (int col = 0; col < M; col++) {
            sideArea += board[0][col] + board[N - 1][col];  //가장 앞쪽, 뒷쪽면
            for (int row = 0; row < N; row++) {
                if (row < N - 1 && board[row][col] > board[row + 1][col]) {
                    sideArea += board[row][col] - board[row + 1][col];
                }
                if (row > 0 && board[row][col] > board[row - 1][col]) {
                    sideArea += board[row][col] - board[row - 1][col];
                }
            }
        }

        System.out.println(sideArea + N * M * 2);
    }
}