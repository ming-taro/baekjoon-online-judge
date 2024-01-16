import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;   //결과가 음수 or 0 -> 우선순위가 높음(따라서 target보다 가중치가 적을 경우 자리를 유지함)
    }
}

public class Main {
    private static ArrayList<Node>[] nodes;
    private static boolean[] visited;
    private static int[] distance;
    private static final int INF = 1_000 * 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        nodes = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        distance = new int[N + 1];

        for (int i = 0; i<= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());

            nodes[start].add(new Node(end, weight));  //A도시에서 B도시로 가는 버스, 즉 방향이 있는 간선임을 주의할것
        }

        stringTokenizer = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(stringTokenizer.nextToken());
        int end = Integer.parseInt(stringTokenizer.nextToken());

        System.out.println(dijkstra(start, end));
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();

        queue.offer(new Node(start, 0));
        Arrays.fill(distance, INF);
        Arrays.fill(visited, false);

        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int current = currentNode.end;

            if (visited[current]) {
                continue;
            }

            visited[current] = true;

            for (Node node: nodes[current]) {
                int newPath = distance[current] + node.weight;  //start->현재정점 + 현재정점->연결노드

                if (!visited[node.end] && newPath < distance[node.end]) {
                    distance[node.end] = newPath;
                    queue.offer(new Node(node.end, distance[node.end]));
                }
            }
        }

        return distance[end];
    }
}