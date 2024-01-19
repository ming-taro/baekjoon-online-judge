import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int number;  //현재 정점
    int weight;  //최단경로 가중치

    public Node(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}

public class Main {
    private static final int MAX_POINT = 100_000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int K = Integer.parseInt(stringTokenizer.nextToken());

        System.out.println(dijkstra(N, K));
    }

    private static int dijkstra(int N, int K) {
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(N, 0));  //시작 노드 -> 가중치 = 0

        boolean[] visited = new boolean[MAX_POINT + 1];

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (visited[currentNode.number]) {
                continue;
            }
            visited[currentNode.number] = true;

            if (currentNode.number == K) {  //K인지를 확인하기 위해서는 방문 처리를 한 후, 즉 최소경로에 도착한 현재 시점에서 정점이 K인지를 확인해야 함
                return currentNode.weight;
            }

            Node[] nextNode = {
                    new Node(currentNode.number * 2, 0),
                    new Node(currentNode.number + 1, 1),
                    new Node(currentNode.number - 1, 1),};

            for (Node node : nextNode) {
                if (node.number < 0 || node.number > MAX_POINT || visited[node.number]) {
                    continue;
                }
                node.weight += currentNode.weight;
                queue.offer(node);
            }
        }

        return 0;
    }
}
/*
모든 가중치가 1일 때 최단 거리를 구하는 문제 -> BFS(숨바꼭질)
모든 가중치가 1이 아닌 경우 최단 거리를 구하는 문제 -> Dijkstra(숨바꼭질3)
*/