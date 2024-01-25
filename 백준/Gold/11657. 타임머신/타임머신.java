import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int INF = 5_000_000;
    private static int N;
    private static ArrayList<Node>[] nodes;
    private static long[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        stringTokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());      //버스
        int M = Integer.parseInt(stringTokenizer.nextToken());  //노선 수

        nodes = new ArrayList[N + 1];  //간선
        distance = new long[N + 1];     //start->정점 최단 거리

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(stringTokenizer.nextToken());
            int B = Integer.parseInt(stringTokenizer.nextToken());
            int C = Integer.parseInt(stringTokenizer.nextToken());

            nodes[A].add(new Node(B, C));
        }

        if(bellmanFord(1, N)) {     //음수 사이클이 있는 경우
            System.out.println(-1);
            return;
        }

        StringJoiner result = new StringJoiner("\n");

        for (int i = 2; i <= N; i++) {
            if (distance[i] == INF) {
                result.add("-1");  //경로X
                continue;
            }
            result.add(distance[i] + "");
        }

        System.out.println(result);
    }

    private static boolean bellmanFord(int start, int end) {
        boolean isUpdated = false;

        Arrays.fill(distance, INF);
        distance[start] = 0;  //자기자신은 0으로 초기화

        //(N - 1)회 동안 최단거리 초기화
        for (int i = 1; i < N; i++) {
            isUpdated = false;
            for (int j = 1; j <= N; j++) {
                if (distance[j] == INF) {  //연결된 길이 없음 -> 첫 시작은 distance[start]=0인 start와 연결된 노드부터 거리 계산을 시작함
                    continue;
                }

                for (Node node: nodes[j]) { //현재 정점과 연결된, 즉 start에서 end정점들 까지의 거리를 갱신하려함
                    long newNode = distance[j] + node.weight;  //(정점 -> 현재) + (현재 -> 다음 노드)
                    if (newNode < distance[node.end]) {
                        distance[node.end] = newNode;
                        isUpdated = true;
                    }
                }
            }

            //더 이상 갱신할 최단 거리가 없는 경우 탐색을 종료함
            if (!isUpdated) {
                break;
            }
        }

        //N번째에서 한번 더 업데이트가 발생 = 음수 사이클 존재
        for (int i = 1; i <= N; i++) {
            if (distance[i] == INF) {
                continue;
            }

            for (Node node: nodes[i]) {
                if (distance[i] + node.weight < distance[node.end]) {
                    return true;
                }
            }
        }

        return false;
    }

    static class Node {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
/*
A->B 버스 노선 = C 시간
->단방향
->C가 음수인 경우도 있음 = 밸만 포드 알고리즘
*/