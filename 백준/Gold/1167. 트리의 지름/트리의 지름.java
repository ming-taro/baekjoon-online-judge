import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int number;
    int weight;

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
}

public class Main {
    private static int v;
    private static List<Node>[] nodes;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        v = Integer.parseInt(reader.readLine());
        nodes = new ArrayList[v + 1];
        distance = new int[v + 1];

        for (int i = 0; i <= v; i++){
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < v; i++) {
            StringTokenizer input = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(input.nextToken());
            while (true) {
                int end = Integer.parseInt(input.nextToken());
                if (end == -1) break;
                int weight = Integer.parseInt(input.nextToken());

                nodes[start].add(new Node(end, weight));
            }
        }

        int index = calcLongDistance(new Node(1, 0));
        index = calcLongDistance(new Node(index, 0));

        System.out.println(distance[index]);
    }

    private static int calcLongDistance(Node startNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        Arrays.fill(distance, 0);

        boolean[] visited = new boolean[v + 1];
        Arrays.fill(visited, false);
        visited[startNode.number] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node node : nodes[current.number]) {
                if (!visited[node.number]) {
                    visited[node.number] = true;
                    queue.add(node);
                    distance[node.number] = node.weight + distance[current.number]; //시작->현재노드까지의 거리와 현재노드에서 연결된 노드까지의 거리합
                }
            }
        }

        int maxIndex = 0;

        for (int i = 1; i <= v; i++) {
            if (distance[maxIndex] < distance[i]) {
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}