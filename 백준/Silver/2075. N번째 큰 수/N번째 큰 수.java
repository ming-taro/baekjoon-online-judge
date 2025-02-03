import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> board[o2[0]][o2[1]] - board[o1[0]][o1[1]]);
        for (int i = 0; i < N; i++) {
            queue.offer(new int[]{ N - 1, i });
        }

        for (int i = 0; i < N - 1; i++) {
            int[] current = queue.poll();
            if (current[0] - 1 >= 0) {
                queue.offer(new int[]{ current[0] - 1, current[1] });
            }
        }

        int[] current = queue.poll();
        System.out.println(board[current[0]][current[1]]);
    }
}