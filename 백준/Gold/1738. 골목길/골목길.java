import java.io.*;
import java.util.*;

class Node {
    int end;
    int weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class Main {
    private static int n, m;
    private static int[] distance;
    private static List<Node>[] nodes;
    private static List<Integer> cycleList = new ArrayList<>();
    private static int[] prevNode;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());  //지점
        m = Integer.parseInt(st.nextToken());  //골목

        distance = new int[n + 1];
        prevNode = new int[n + 1];
        nodes = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u].add(new Node(v, w));
        }

        if (isCycle()) {
            System.out.println(-1);
            return;
        }

        Stack<Integer> result = new Stack<>();
        int end = n;

        while (end != 0) {
            result.push(end);
            end = prevNode[end];      //이전에 방문한 노드를 계속 타고감 -> 1이 나올때까지
        }

        while (!result.isEmpty()) {
            System.out.print(result.pop() + " ");
        }
    }

    private static boolean isCycle() {
        Arrays.fill(distance, Integer.MIN_VALUE);
        distance[1] = 0;

        for (int loop = 1; loop <= n; loop++) {
            for (int i = 1; i <= n; i++) {
                if (distance[i] == Integer.MIN_VALUE) {  //정점 1번과 연결된 길이 없는 경우
                    continue;
                }

                for (Node next: nodes[i]) {
                    int newPath = distance[i] + next.weight;
                    if (newPath > distance[next.end]) {  //정점까지의 거리가 최대가 되는 길을 찾아서 무한한 양의 값으로 가는 싸이클이 발생하는지 확인한다
                        distance[next.end] = newPath;
                        prevNode[next.end] = i;
                        if (loop == n && isCycleToEnd(next.end)) {  //n번째에도 길이 업데이트됨 -> 양의 싸이클 존재
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    private static boolean isCycleToEnd(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[n + 1];

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == n) {
                return true;
            }

            for (Node node: nodes[current]) {
                if (!visited[node.end]) {
                    visited[node.end] = true;
                    queue.offer(node.end);
                }
            }
        }

        return false;
    }
}