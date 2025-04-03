import java.io.*;
import java.util.*;

class Node {
    int num;
    int value;
    public Node(int num, int value) {
        this.num = num;
        this.value = value;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] t = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Node>[] nodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            nodes[a].add(new Node(b, l));
            nodes[b].add(new Node(a, l));
        }

        int[][] distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            calcDistance(i, distance[i], n, nodes);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            int total = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= m) {
                    total += t[j];
                }
            }
            answer = Math.max(answer, total);
        }

        System.out.println(answer);
    }

    private static int[] calcDistance(int start, int[] distance, int n, List<Node>[] nodes) {
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        queue.offer(new Node(start, 0));
        boolean[] visited = new boolean[n];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited[current.num]) continue;
            visited[current.num] = true;

            for (Node next: nodes[current.num]) {
                if (visited[next.num]) continue;
                if (distance[current.num] + next.value < distance[next.num]) {
                    distance[next.num] = distance[current.num] + next.value;
                    queue.offer(new Node(next.num, distance[next.num]));
                }
            }
        }

        return distance;
    }
}