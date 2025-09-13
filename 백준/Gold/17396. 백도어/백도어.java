import java.io.*;
import java.util.*;

class Node {
    int end;
    long value;
    public Node(int end, long value) {
        this.end = end;
        this.value = value;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] array = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray(); // 0 : 상대 시야X, 1 : 상대 시야O
        array[N - 1] = 0;

        List<Node>[] nodes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()); // a -> b : t시간 소요
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes[a].add(new Node(b, c));
            nodes[b].add(new Node(a, c));
        }

        Queue<Node> queue = new PriorityQueue<>(((o1, o2) -> (int)(o1.value - o2.value)));
        queue.add(new Node(0, 0));

        long[] distance = new long[N];

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (distance[current.end] < current.value) continue;

            for (Node next: nodes[current.end]) {
                if (array[next.end] == 1 || next.end == 0) continue;
                if (distance[next.end] == 0 || distance[current.end] + next.value < distance[next.end]) {
                    distance[next.end] = distance[current.end] + next.value;
                    queue.offer(new Node(next.end, distance[next.end]));
                }
            }
        }

        if (distance[N - 1] == 0) System.out.println(-1);
        else System.out.println(distance[N - 1]);
    }
}