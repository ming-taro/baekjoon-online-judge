import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());

        for (int i = 0; i < T; i++) {
            int F = Integer.parseInt(reader.readLine());

            Map<String, Integer> toIndex = new HashMap<>();
            int index = 0;

            int[] parent = new int[F * 2];
            int[] child = new int[F * 2];
            for (int j = 0; j < F * 2; j++) {
                parent[j] = j;
                child[j] = 1;
            }

            for (int j = 0; j < F; j++) {
                String[] friend = reader.readLine().split(" ");
                if (!toIndex.containsKey(friend[0])) {
                    toIndex.put(friend[0], index++);
                }
                if (!toIndex.containsKey(friend[1])) {
                    toIndex.put(friend[1], index++);
                }

                System.out.println(union(parent, child, toIndex.get(friend[0]), toIndex.get(friend[1])));
            }
        }
    }

    private static int union(int[] parent, int[] child, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);

        if (xParent == yParent) {
            return child[xParent];
        }

        if (xParent < yParent) {            // x <- y
            parent[yParent] = xParent;
            return child[xParent] += child[yParent];
        }
        parent[xParent] = yParent;
        return child[yParent] += child[xParent];   // y <- x
    }

    private static int findParent(int[] parent, int current) {
        if (parent[current] == current) {
            return current;
        }
        return parent[current] = findParent(parent, parent[current]);
    }
}