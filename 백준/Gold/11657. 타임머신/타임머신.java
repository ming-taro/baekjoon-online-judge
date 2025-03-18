import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Node {
    int val;
    int weight;
    public Node(int val, int weight) {
        this.val = val;
        this.weight = weight;
    }
}

class Main {
    private static int N;
    private static ArrayList<Node>[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            nodes[A].add(new Node(B, C));
        }

        long[] distance = new long[N + 1];

        if (isNegativeCycle(distance, 1)) {
            System.out.println(-1);
            return;
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(distance[i] == Integer.MAX_VALUE ? -1 : distance[i]);
        }
    }

    private static boolean isNegativeCycle(long[] distance, int start) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for (int i = 0; i < N - 1; i++) {   // (n - 1)회 시행
            for (int j = 1; j <= N; j++) {  // 거쳐 가는 노드(start - > j -> j.next == distance[j.next]) vs distance[end](== start -> end)
                if (distance[j] == Integer.MAX_VALUE) continue; // 길 없음
                for (Node next: nodes[j]) {
                    distance[next.val] = Math.min(distance[next.val], distance[j] + next.weight);
                }
            }
        }

        for (int j = 1; j <= N; j++) {
            if (distance[j] == Integer.MAX_VALUE) continue;
            for (Node next: nodes[j]) {
                if (distance[j] + next.weight < distance[next.val]) {
                    return true;
                }
            }
        }

        return false;
    }
}
/*
벨만-포드 : 노드 - 노드간 최단 거리 (가중치 음수 가능)
->매번 모든 간선 확인
 */