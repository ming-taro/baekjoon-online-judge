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
    private static int n;
    private static List<Node> nodes[];
    private static boolean[] visited;
    private static int maxWeight = 0;
    private static int maxNode = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        nodes = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        Arrays.fill(visited, false);

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

            int parent = Integer.parseInt(stringTokenizer.nextToken());
            int child = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());

            nodes[parent].add(new Node(child, weight));
            nodes[child].add(new Node(parent, weight));  //루트에서 가장 먼 정점을 구한후, 다시 해당 노드에서 가장 먼 정점을 구하기 위해 parent->child, child->parent 경로를 모두 저장
        }

        visited[1] = true;
        searchNode(1, 0);  //maxNode -> 정점으로부터 가장 먼 거리(가중치가 가장 큰)에 있는 정점

        Arrays.fill(visited, false);
        visited[maxNode] = true;
        maxWeight = 0;
        searchNode(maxNode, 0);  //다시 maxNode부터 가장 먼 거리에 있는 정점을 찾아 가중치 출력

        System.out.println(maxWeight);
    }

    private static void searchNode(int nodeIndex, int totalWeight) {
        if (totalWeight > maxWeight) {  //루트에서 가장 먼, 즉 가중치가 높은 노드의 index찾기
            maxWeight = totalWeight;
            maxNode = nodeIndex;
        }

        for (Node chid: nodes[nodeIndex]) {  //이진트리가 아니므로 하나의 정점에 자식이 더 많을 수 있음
            if (!visited[chid.number]) {
                visited[chid.number] = true;
                searchNode(chid.number, totalWeight + chid.weight);
            }
        }
    }
}