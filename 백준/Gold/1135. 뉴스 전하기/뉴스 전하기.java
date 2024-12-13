import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static List<Integer>[] nodes;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");

        parents = new int[N];
        nodes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int node = Integer.parseInt(input[i]);
            if (node == -1) continue;
            nodes[node].add(i);
            parents[i] = node;
        }

        System.out.println(dfs(0));
    }

    private static int dfs (int current) {
        if (nodes[current].isEmpty()) {
            return 0;
        }

        int size = nodes[current].size();
        int[] times = new int[size];
        int index = 0;

        for (int next: nodes[current]) {
            times[index++] = dfs(next);
        }
        Arrays.sort(times);

        int time = 1;
        int maxTime = 0;
        for (int i = 0; i < size; i++) {
            times[size - i - 1] += time++;
            maxTime = Math.max(maxTime, times[size - i - 1]);
        }

        return maxTime;
    }
}