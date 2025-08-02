import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        Queue<Long> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            queue.offer(Long.parseLong(reader.readLine()));
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        long answer = 0;
        while (queue.size() > 1) {
            long sum = queue.poll() + queue.poll();
            answer += sum;
            queue.offer(sum);
        }

        System.out.println(answer);
    }
}