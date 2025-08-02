import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(reader.readLine());

        List<Node>[] nodes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[u].add(new Node(v, w));
        }

        int[] distance = new int[V + 1];
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> distance[o1] - distance[o2]);
        queue.offer(K);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Node node: nodes[current]) {
                int newValue = distance[current] + node.value;
                if (distance[node.next] == 0 || distance[node.next] > newValue) {
                    distance[node.next] = newValue;
                    queue.offer(node.next);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (i == K) {
                System.out.println(0);
                continue;
            }
            System.out.println(distance[i] == 0 ? "INF" : distance[i]);
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