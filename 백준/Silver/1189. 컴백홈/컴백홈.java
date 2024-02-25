import java.io.*;
import java.util.*;

public class Main {
    private static boolean[][] board;
    private static int R, C, K;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String map = reader.readLine();
            for (int j = 0; j < C; j++) {
                if (map.charAt(j) == 'T') {  //한수가 갈 수 없는 위치
                    board[i][j] = true;
                }
            }
        }

        board[R - 1][0] = true;
        countWayToGo(R - 1, 0, 1);

        System.out.println(count);
    }

    private static void countWayToGo(int row, int col, int depth) {
        if (row == 0 && col == C -1 && depth == K) {
            count++;
        }
        if (row == 0 && col == C -1 || depth == K) {
            return;
        }


        int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C
                    && !board[nextRow][nextCol]) {
                board[nextRow][nextCol] = true;
                countWayToGo(nextRow, nextCol, depth + 1);
                board[nextRow][nextCol] = false;
            }
        }
    }
}
/*
->R * C크기의 맵
->T지점은 갈 수 없음
->한번 지나간 길은 다시 X
return 한수가 (R - 1, 0)에서 집(0, C - 1)까지 가는 거리가 K인 가짓수

3 4 8
....
.T..
....
=>output: 2
 */