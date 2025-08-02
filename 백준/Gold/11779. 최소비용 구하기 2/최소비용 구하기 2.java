import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        List<Node>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        int[][] d = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            d[start][end] = Math.min(d[start][end], value);
        }

        for (int start = 1; start <= n; start++) {
            for (int end = 1; end <= n; end++) {
                if (d[start][end] != Integer.MAX_VALUE) nodes[start].add(new Node(end, d[start][end]));
            }
        }

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        long[] distance = new long[n + 1];
        Arrays.fill(distance, -1);
        distance[A] = 0;

        int[] path = new int[n + 1];
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> (int)(distance[o1] - distance[o2]));
        queue.offer(A);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Node node: nodes[current]) {
                long newValue = distance[current] + node.value;
                if (distance[node.next] == -1 || newValue < distance[node.next]) {
                    distance[node.next] = newValue;
                    path[node.next] = current;
                    queue.offer(node.next);
                }
            }
        }

        System.out.println(distance[B]);

        Stack<Integer> pathResult = new Stack<>();
        pathResult.push(B);
        int current = B;
        while (current != A) {
            pathResult.push(path[current]);
            current = path[current];
        }

        if (pathResult.size() == 1) {
            System.out.println(1);
            System.out.println(A);
            return;
        }

        System.out.println(pathResult.size());
        while (!pathResult.isEmpty()) {
            System.out.print(pathResult.pop() + " ");
        }
    }
}

class Node {
    int next;
    int value;

    public Node(int next, int value) {
        this.next = next;
        this.value = value;
    }
}