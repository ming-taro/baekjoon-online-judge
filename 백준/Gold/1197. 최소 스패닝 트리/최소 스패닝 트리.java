import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int start;
    int end;
    int weight;

    public Node(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        Queue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            queue.add(new Node(A, B, C));
        }

        int totalWeight = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (findParent(current.start) != findParent(current.end)) {
                union(current.start, current.end);
                totalWeight += current.weight;
            }
        }

        System.out.println(totalWeight);
    }

    private static int findParent(int node) {
        if (parent[node] == node) {  //정점의 부모 == 정점 -> 루트도달
            return node;
        }
        return parent[node] = findParent(parent[node]);
    }

    private static void union(int A, int B) {
        int parentOfA = findParent(A);
        int parentOfB = findParent(B);

        parent[parentOfA] = Math.min(parentOfA, parentOfB);
        parent[parentOfB] = Math.min(parentOfA, parentOfB);
    }
}