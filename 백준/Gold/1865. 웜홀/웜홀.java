import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private static int INF = 5_000_000;
    private static int N;
    private static int[] distance;
    private static ArrayList<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int TC = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < TC; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(stringTokenizer.nextToken());     //지점
            int M = Integer.parseInt(stringTokenizer.nextToken()); //도로
            int W = Integer.parseInt(stringTokenizer.nextToken()); //웜홀

            distance = new int[N + 1];
            nodes = new ArrayList[N + 1];
            for (int j = 1; j <= N; j++) {
                nodes[j] = new ArrayList<>();
            }

            for (int j = 0; j < M; j++) { //도로 정보(양방향)
                stringTokenizer = new StringTokenizer(reader.readLine());
                int S = Integer.parseInt(stringTokenizer.nextToken());
                int E = Integer.parseInt(stringTokenizer.nextToken());
                int T = Integer.parseInt(stringTokenizer.nextToken()); //소요 시간

                nodes[S].add(new Node(E, T));
                nodes[E].add(new Node(S, T));
            }

            for (int j = 0; j < W; j++) { //웜홀 정보(단방향)
                stringTokenizer = new StringTokenizer(reader.readLine());
                int S = Integer.parseInt(stringTokenizer.nextToken());
                int E = Integer.parseInt(stringTokenizer.nextToken());
                int T = Integer.parseInt(stringTokenizer.nextToken()); //감소 시간

                nodes[S].add(new Node(E, -T));
            }

            boolean isNegativeCycle = false;

            for (int j = 1; j <= N; j++) {
                if (bellmanFord(j)) { //음의 사이클 발생
                    isNegativeCycle = true;
                    break;
                }
            }

            if (isNegativeCycle) {
                result.append("YES").append("\n");
            } else {
                result.append("NO").append("\n");
            }
        }

        System.out.println(result);
    }

    private static boolean bellmanFord(int start) {
        boolean isUpdated = false;

        Arrays.fill(distance, INF);
        distance[start] = 0;

        for (int i = 0; i < N - 1; i++) {
            isUpdated = false;

            for (int j = 1; j <= N; j++) {
                if (distance[j] == INF) {
                    continue;
                }

                for (Node node: nodes[j]) {
                    int newPath = distance[j] + node.weight;  //시작 -> 현재 + 현재 -> 정점

                    if (newPath < distance[node.end]) {
                        distance[node.end] = newPath;
                        isUpdated = true;
                    }
                }
            }

            if (!isUpdated) {
                break;
            }
        }

        if (isUpdated) {
            for (int j = 1; j <= N; j++) {
                if (distance[j] == INF) {
                    continue;
                }

                for (Node node: nodes[j]) {
                    int newPath = distance[j] + node.weight;

                    if (newPath < distance[node.end]) {
                        distance[node.end] = newPath;
                        return true;
                    }
                }
            }
        }

        return false;
    }
}