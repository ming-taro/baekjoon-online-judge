import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int time;
    int min;
    int max;

    public Node (int time, int min, int max) {
        this.time = time;
        this.min = min;
        this.max = max;
    }

    @Override
    public int compareTo(Node node) {
        if (this.time != node.time) {  // 거리가 더 가까운 치킨집
            return this.time - node.time;
        }

        if (this.min < node.min) {
            return -1;
        }
        if (this.max < node.max) {
            return -1;
        }
        return 1;
    }
}

public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int M;
    private static List<Integer>[] nodes;
    private static int[][] time;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        time = new int[N + 1][N + 1];

        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            input = reader.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            nodes[A].add(B);
            nodes[B].add(A);
        }

        for (int i = 1; i <= N; i++) { // 각 노드간 시간 거리 계산
            calcTime(i);
        }

        Queue<Node> queue = new PriorityQueue<>();

        for (int first = 1; first < N; first++) {
            for (int second = first + 1; second <= N; second++) {
                int totalTime = 0;
                for (int i = 1; i <= N; i++) {
                    totalTime += Math.min(time[i][first], time[i][second]);
                }
                queue.offer(new Node(totalTime * 2, Math.min(first, second), Math.max(first, second)));
            }
        }

        Node result = queue.poll();
        System.out.println(result.min + " " + result.max + " " + result.time);
    }

    private static void calcTime(int start) {
        Arrays.fill(time[start], INF);
        time[start][start] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : nodes[current]) {
                int newPath = time[start][current] + 1;
                if (time[start][next] == INF || newPath < time[start][next]) {
                    time[start][next] = newPath;
                    queue.offer(next);
                }
            }
        }
    }
}