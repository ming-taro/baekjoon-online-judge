import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];

        dfs(0, new int[M], visited);
    }

    private static void dfs(int current, int[] numbers, boolean[] visited) {
        if (current == M) {
            for (int num: numbers) {
                System.out.print(num + " ");
            } System.out.println();
            return;
        }

        for (int num = 1; num <= N; num++) {
            if (!visited[num]) {
                visited[num] = true;
                numbers[current] = num;
                dfs(current + 1, numbers, visited);
                visited[num] = false;
            }
        }
    }
}