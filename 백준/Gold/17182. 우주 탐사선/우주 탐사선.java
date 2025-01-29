import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] times;
    private static boolean[] visited;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        times = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            times[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int n = 0; n < N; n++) {          // 거쳐가는 노드
            for (int i = 0; i < N; i++) {      // start
                for (int j = 0; j < N; j++) {  // end
                    times[i][j] = Math.min(times[i][j], times[i][n] + times[n][j]);
                }
            }
        }

        visited[K] = true;
        dfs(1, K, 0);
        System.out.println(answer);
    }

    private static void dfs(int depth, int current, int totalTime) {
        if (depth == N) {
            answer = Math.min(answer, totalTime);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && i != current) {
                visited[i] = true;
                dfs(depth + 1, i, totalTime + times[current][i]);
                visited[i] = false;
            }
        }
    }
}
/*
1 --1--> 0
0 --1--> 2
2 --3--> 0
정점 0, 1, 2의 관계가 위와 같을 때,
1 -> 2 -> 0 순서로 가게 되면 1 -> 2의 최단거리 경로에서 이미 0을 거쳐갔음에도
또 다시 2 -> 0으로 가는 길을 추가로 더하게 되는 상황이 발생하여 이를 따로 표기해주어야 하나 고민했지만
dfs로 모든 경로를 계산해보면서 최단거리를 구할 수 있다

1 -> 2 -> 0 경로는 실제로 1 -> 0 -> 2 -> 0 거리를 구하게 되면서 1 + 1 + 3 = 5가 되지만,
1 -> 0 -> 2 경로는 실제로도 1 -> 0 -> 2 거리를 구하게 되면서 1 + 1 = 2로 최단거리를 구할 수 있다

따라서 모든 경로를 검사해보면 최단거리에서 어떤 경로를 같이 경유했는지 따로 체크해보지 않아도
최단거리를 구할 수 있다
 */