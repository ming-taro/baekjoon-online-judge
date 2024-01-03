import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int M;
    private static int N;
    private static int[][] box;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        box = new int[N][M];
        visited = new boolean[N][M];
        Queue<int[]> tomatos = new LinkedList<>();

        for (int row = 0; row < N; row++) {
            box[row] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int col = 0; col < M; col++) {
                if (box[row][col] == 1) {   //먼저 익어 있는 토마토들 위치
                    tomatos.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }

        if (tomatos.size() == N * M) {      //모든 토마토가 익은 경우
            System.out.println(0);
            return;
        }

        bfs(tomatos);
        int period = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (box[row][col] == 0) {  //안 익은 토마토가 있는 경우
                    System.out.println(-1);
                    return;
                }
                period = Math.max(period, box[row][col]);
            }
        }

        System.out.println(period - 1);
    }

    private static void bfs(Queue<int[]> tomatos) {
        int[] dx = {-1, 0, 1, 0};  //상, 우, 하, 좌
        int[] dy = {0, 1, 0, -1};

        while (!tomatos.isEmpty()) {
            int[] point = tomatos.poll();  //익은 토마토
            int row = point[0];
            int col = point[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if (isWithinRange(nextRow, nextCol)
                        && box[nextRow][nextCol] == 0
                        && !visited[nextRow][nextCol]) {       //인접 칸의 토마토가 익음
                    box[nextRow][nextCol] = box[row][col] + 1; //익은 날짜를 기록해줌(ex 1일차 토마토 인근 -> 2 저장)
                    visited[nextRow][nextCol] = true;
                    tomatos.offer(new int[]{nextRow, nextCol});
                }
            }
        }
    }

    private static boolean isWithinRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}