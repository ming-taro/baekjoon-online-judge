import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int val;
    int weight;
    public Node (int val, int weight) {
        this.val = val;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        ArrayList<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes[a].add(new Node(b, c));
            nodes[b].add(new Node(a, c));
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0));
        int answer = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited[current.val]) continue;
            visited[current.val] = true;
            answer += current.weight;

            for (Node next: nodes[current.val]) {
                if (!visited[next.val]) {
                    queue.offer(next);
                }
            }
        }

        System.out.println(answer);
    }
}