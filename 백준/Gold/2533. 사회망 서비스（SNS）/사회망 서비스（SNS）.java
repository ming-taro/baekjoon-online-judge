import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<Integer>[] nodes;
    private static boolean[] visited;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        visited = new boolean[N + 1];
        nodes = new ArrayList[N + 1];
        for (int i = 1; i <=N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[u].add(v);
            nodes[v].add(u);
        }

        visited[1] = true;
        dfs(1);
        System.out.println(answer);
    }

    private static int dfs(int current) {
        boolean flag = false;

        for (int next: nodes[current]) {
            if (visited[next]) continue;
            visited[next] = true;
            if (dfs(next) == 0) {
                flag = true;
            }
        }

        if (flag) {
            answer++;  // 자식중 1명이라도 얼리가 아니라면 => 부모는 무조건 얼리
            return 1;
        }
        return 0; // 자식들이 모두 얼리 or leaf라면 => 부모는 얼리일 필요X
    }
}