import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        int[][] classList = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int number = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            classList[i][0] = start;
            classList[i][1] = end;
        }

        Arrays.sort(classList, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        Queue<Integer> available = new PriorityQueue<>();
        Queue<Integer> candidate = new PriorityQueue<>();
        candidate.offer(classList[0][1]);

        for (int i = 1; i < N; i++) {
            while (!candidate.isEmpty() && candidate.peek() <= classList[i][0]) {
                available.offer(candidate.poll());
            }

            if (!available.isEmpty()) available.poll();
            candidate.offer(classList[i][1]);
        }

        System.out.println(available.size() + candidate.size());
    }
}