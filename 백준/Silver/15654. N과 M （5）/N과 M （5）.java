import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int M;
    private static int[] array;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(array);

        visited = new boolean[N];

        StringBuilder answer = new StringBuilder();
        dfs(0, new int[M], answer);
        System.out.println(answer);
    }

    private static void dfs(int current, int[] numbers, StringBuilder answer) {
        if (current == M) {
            for (int num: numbers) {
                answer.append(num).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                numbers[current] = array[i];
                dfs(current + 1, numbers, answer);
                visited[i] = false;
            }
        }
    }
}