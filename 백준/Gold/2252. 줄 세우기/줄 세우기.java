import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] value = new int[N + 1];
        List<Integer>[] order = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            order[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(input.nextToken());
            int B = Integer.parseInt(input.nextToken());

            value[B]++;
            order[A].add(B);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (value[i] == 0) {
                queue.offer(i);
            }
        }

        StringJoiner result = new StringJoiner(" ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(String.valueOf(current));

            for (int next: order[current]) {
                value[next]--;
                if (value[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        System.out.println(result.toString());
    }
}