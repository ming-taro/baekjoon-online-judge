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

        Map<Integer, Integer> go = new HashMap<>();
        Map<Integer, Integer> back = new HashMap<>();
        Map<Integer, int[]> nodes = new HashMap<>();

        int number = 1;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                nodes.put(number++, new int[]{r, c});
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            go.put(u, v);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            back.put(u, v);
        }

        int[][] board = new int[10][10];
        for (int i = 0; i < 10; i++) {
            Arrays.fill(board[i], Integer.MAX_VALUE);
        }
        board[0][0] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int[] cNode = nodes.get(current);
            for (int i = 1; i <= 6; i++) {
                int next = current + i;
                if (next == 100) {
                    System.out.println(board[cNode[0]][cNode[1]] + 1);
                    return;
                }
                if (next > 100) continue;

                if (go.containsKey(next)) { // 사다리
                    next = go.get(next);
                }
                if (back.containsKey(next)) { // 뱀
                    next = back.get(next);
                }
                int[] nNode = nodes.get(next);

                if (board[cNode[0]][cNode[1]] + 1 < board[nNode[0]][nNode[1]]) {
                    board[nNode[0]][nNode[1]] = board[cNode[0]][cNode[1]] + 1;
                    queue.offer(next);
                }
            }
        }
    }
}