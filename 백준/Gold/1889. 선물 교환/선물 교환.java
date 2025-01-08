import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] in = new int[N + 1];
        int[][] nodes = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            in[x]++;
            in[y]++;
            nodes[i][0] = x;
            nodes[i][1] = y;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (in[i] < 2) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next: nodes[current]) {
                in[next]--;
                if (!visited[next] && in[next] < 2) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                result.add(i);
            }
        }
        Collections.sort(result);

        System.out.println(result.size());
        for (int r: result) {
            System.out.print(r + " ");
        }
    }
}