import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());         // 발전소 개수
        int W = Integer.parseInt(st.nextToken());         // 남아 있는 전선 수
        double M = Double.parseDouble(reader.readLine()); // 제한 길이

        int[][] point = new int[N + 1][2];
        boolean[][] connected = new boolean[N + 1][N + 1];

        for (int i = 0; i < N; i++) { // 발전소 좌표
            st = new StringTokenizer(reader.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            point[i + 1][0] = X;
            point[i + 1][1] = Y;
        }

        for (int i = 0; i < W; i++) { // 연결된 발전소
            st = new StringTokenizer(reader.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            connected[X][Y] = true;
            connected[Y][X] = true;
        }

        double[] distance = new double[N + 1];
        Arrays.fill(distance, Double.MAX_VALUE);
        distance[1] = 0;

        Queue<Node> queue = new PriorityQueue<>(((o1, o2) -> Double.compare(o1.cost, o2.cost)));
        queue.offer(new Node(1, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.number == N) {
                System.out.println((int)(distance[N] * 1000));
                break;
            }
            for (int next = 1; next <= N; next++) {
                if (current.number == next) continue;
                double newCost = connected[current.number][next] ? 0 : calcDistance(point[current.number], point[next]);
                if (newCost <= M && current.cost + newCost < distance[next]) {
                    distance[next] = current.cost + newCost;
                    queue.offer(new Node(next, current.cost + newCost));
                }
            }
        }
    }

    private static double calcDistance(int[] p1, int[] p2) {
        return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }
}

class Node {
    int number;
    double cost;
    public Node(int number, double cost) {
        this.number = number;
        this.cost = cost;
    }
}