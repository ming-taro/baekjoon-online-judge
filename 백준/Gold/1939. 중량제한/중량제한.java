import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            nodes[A].add(new Node(B, C));
            nodes[B].add(new Node(A, C));
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        long[] distance = new long[N + 1];
        distance[start] = Long.MAX_VALUE;

        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> (int)(o2.value - o1.value));
        queue.offer(new Node(start, Long.MAX_VALUE));

        while(!queue.isEmpty()) {
            Node current = queue.poll(); // start ~ current.value까지 최소 중량 = current.value
            if (current.value < distance[current.next]) continue; // 탐색해온 경로(current)의 중량이 이전 경로보다 작으면 더 탐색할 필요X
            for (Node node: nodes[current.next]) {
                if (Math.min(distance[current.next], node.value) > distance[node.next]) { // 경로에 대해 최소중량이 더 큰 경로 선택
                    distance[node.next] = Math.min(distance[current.next], node.value);
                    queue.offer(new Node(node.next, distance[node.next]));
                }
            }
        }

        System.out.println(distance[end]);
    }
}

class Node {
    int next;
    long value;
    public Node(int next, long value) {
        this.next = next;
        this.value = value;
    }
}