import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] parent = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int remove = Integer.parseInt(reader.readLine());
        int root = 0;

        List<Integer>[] childs = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            if (parent[i] == -1) {
                root = i;
            }
            childs[i] = new ArrayList<>();
        }

        if (root == remove) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (parent[i] != -1 && i != remove) {
                childs[parent[i]].add(i);
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (childs[current].isEmpty()) {
                answer++;
                continue;
            }

            for (int child: childs[current]) {
                queue.offer(child);
            }
        }

        System.out.println(answer);
    }
}