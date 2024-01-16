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
        return weight = o.weight;
    }
}

public class Main {
    private static ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
    private static int N;
    private static final int INF = 200_000_000; //200_000*1_000

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);

        for (int i = 0; i <= N; i++) { //연결노드
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int[] nodeInput = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int start = nodeInput[0];
            int end = nodeInput[1];
            int weight = nodeInput[2];

            nodes.get(start).add(new Node(end, weight));  //start->end, end->start
            nodes.get(end).add(new Node(start, weight));
        }

        input = reader.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        int firstPath = dijkstra(1, start) + dijkstra(start, end) + dijkstra(end, N);
        int secondPath = dijkstra(1, end) + dijkstra(end, start) + dijkstra(start, N);

        int result = (firstPath >= INF && secondPath >= INF) ? -1 : Math.min(firstPath, secondPath);
        System.out.println(result);
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] distance = new int[N + 1];     //정점거리
//        boolean[] visited = new boolean[N + 1];  //방문확인

        Arrays.fill(distance, INF);
        queue.offer(new Node(start, 0));
        distance[start] = 0; //start->start 거리 = 0

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int current = currentNode.end;       //현재 위치한 정점

//            if (visited[current]) {  //다익스트라 알고리즘 -> 가장 작은 정점부터 방문하기 때문에 방문 처리를 해줌
//                continue;
//            }

//            visited[current] = true; //우선순위 큐에서 꺼냈을 때 visited표시 -> 다익스트라를 통해 start에서 현재 정점까지의 가중치가 가장 작음을 보장하기 때문

            for (Node node: nodes.get(current)) {  //node.end -> 현재 정점과 연결된 정점 번호
                int newPath = distance[current] + node.weight;           //start->현재 노드까지의 거리 + 현재 노드->연결된 노드까지의 거리 == start->연결된 노드까지의 거리
                if (newPath < distance[node.end]) {//start->연결된 노드까지의 기존 길 보다 현재 정점을 거쳐서 가는 길의 가중치가 더 적은 경우
                    distance[node.end] = newPath;
                    queue.offer(new Node(node.end, distance[node.end])); //거리 가중치를 누적한 결과값을 weight에 저장
                }
            }
        }

        return distance[end];  //end까지의 최종 거리 가중치(priority queue를 사용했기 때문에 최소값이 저장됨)
    }
}
