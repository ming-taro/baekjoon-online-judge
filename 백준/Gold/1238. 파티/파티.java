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
        return weight - o.weight;
    }
}

public class Main {
    private static int N;
    private static List<Node>[] paths;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int X = Integer.parseInt(stringTokenizer.nextToken());

        paths = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            paths[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());

            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            int time = Integer.parseInt(stringTokenizer.nextToken());

            paths[start].add(new Node(end, time)); //단방향 도로
        }

        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, dijkstra(i, X) + dijkstra(X, i));
        }

        System.out.println(maxTime);
    }

    private static int dijkstra(int start, int end) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));

        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();  //거리가 가장 짧은 노드

            if (visited[current.end]) {
                continue;
            }
            visited[current.end] = true;

            for (Node node: paths[current.end]) {
                int newPath = distance[current.end] + node.weight;
                if (!visited[node.end] && newPath < distance[node.end]) {
                    distance[node.end] = newPath;  //새로운 최단 거리 갱신
                    queue.add(new Node(node.end, newPath));
                }
            }
        }

        return distance[end];
    }
}