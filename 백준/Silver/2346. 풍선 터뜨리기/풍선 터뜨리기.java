import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] number = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            queue.offer(new int[]{ i + 1, number[i] });
        }

        StringBuilder answer = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            answer.append(current[0] + " ");
            if (queue.isEmpty()) break;
            if (current[1] < 0) {
                for (int i = current[1]; i < 0; i++) {
                    queue.addFirst(queue.pollLast());
                }
            } else {
                for (int i = 0; i < current[1] - 1; i++) {
                    queue.addLast(queue.pollFirst());
                }
            }
        }

        System.out.println(answer.toString());
    }
}