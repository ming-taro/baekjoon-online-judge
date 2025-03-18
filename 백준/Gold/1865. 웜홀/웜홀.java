import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Node {
    int val;
    int time;
    public Node(int val, int time) {
        this.val = val;
        this.time = time;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(reader.readLine());
        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] nodes = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                nodes[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {  // 도로
                st = new StringTokenizer(reader.readLine());
                int S = Integer.parseInt(st.nextToken()); // S<--(T)-->E
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                nodes[S].add(new Node(E, T));
                nodes[E].add(new Node(S, T));
            }
            for (int i = 0; i < W; i++) {  // 웜홀
                st = new StringTokenizer(reader.readLine());
                int S = Integer.parseInt(st.nextToken()); // S--(T)-->E
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                nodes[S].add(new Node(E, -T));
            }

            boolean flag = false;
            for (int i = 1; i <= N; i++) {
                if (hasNegativeCycle(i, N, nodes)) {
                    flag = true;
                    break;
                }
            }

            System.out.println(flag ? "YES" : "NO");
        }
    }

    private static boolean hasNegativeCycle(int start, int N, ArrayList<Node>[] nodes) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        boolean isUpdate = false;
        for (int i = 0; i < N - 1; i++) {
            isUpdate = false;
            for (int j = 1; j <= N; j++) {
                if (distance[j] == Integer.MAX_VALUE) continue;
                for (Node next: nodes[j]) {
                    if (distance[j] + next.time < distance[next.val]) {
                        distance[next.val] = distance[j] + next.time;
                        isUpdate = true;
                    }
                }
            }

            if (!isUpdate) return false;
        }

        for (int j = 1; j <= N; j++) {
            if (distance[j] == Integer.MAX_VALUE) continue;
            for (Node next: nodes[j]) {
                if (distance[j] + next.time < distance[next.val]) return true;
            }
        }
        return false;
    }
}