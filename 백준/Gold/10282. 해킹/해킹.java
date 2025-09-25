import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호

            List<Node>[] nodes = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 0 ; i < d; i++) {
                st = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(st.nextToken()); // b -> a 감염 (s초 후)
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                nodes[b].add(new Node(a, s));
            }

            Queue<Node> queue = new PriorityQueue<>(((o1, o2) -> o1.time - o2.time));
            queue.offer(new Node(c, 0));

            int[] time = new int[n + 1];
            Arrays.fill(time, Integer.MAX_VALUE);
            time[c] = 0;

            while (!queue.isEmpty()) {
                Node current = queue.poll();
                for (Node next: nodes[current.number]) {
                    int newTime = current.time + next.time;
                    if (newTime < time[next.number]) {
                        time[next.number] = newTime;
                        queue.offer(new Node(next.number, newTime));
                    }
                }
            }

            int totalCount = 0;
            int maxTime = 0;
            for (int i = 1; i <= n; i++) {
                if (time[i] != Integer.MAX_VALUE) {
                    totalCount++;
                    maxTime = Math.max(maxTime, time[i]);
                }
            }

            answer.append(totalCount + " " + maxTime + "\n");
        }

        System.out.println(answer);
    }
}

class Node {
    int number;
    int time;
    public Node(int number, int time) {
        this.number = number;
        this.time = time;
    }
}