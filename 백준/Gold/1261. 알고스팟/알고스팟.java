import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] count = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(count[i], Integer.MAX_VALUE);
        }
        count[0][0] = 0;

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> count[o1[0]][o1[1]] - count[o2[0]][o2[1]]);
        queue.offer(new int[]{ 0, 0 });

        int[] dx = { -1, 0, 1, 0 }; // 위, 오, 아, 왼
        int[] dy = { 0, 1, 0, -1 };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = current[0] + dx[i];
                int nextCol = current[1] + dy[i];
                if (!(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M)) continue;

                int newCount = count[current[0]][current[1]];
                if (board[nextRow][nextCol] == 1) newCount++;
                if (count[nextRow][nextCol] > newCount) {
                    count[nextRow][nextCol] = newCount;
                    queue.offer(new int[]{ nextRow, nextCol });
                }
            }
        }

        System.out.println(count[N - 1][M - 1]);
    }
}