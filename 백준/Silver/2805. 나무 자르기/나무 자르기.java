import java.io.*;
import java.util.*;

public class Main {
    private static long N, M;
    private static long[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        System.out.println(findTreeHeight());
    }

    private static long findTreeHeight() {
        long start = 0;
        long end = Arrays.stream(trees).max().getAsLong();

        while (start <= end) {
            long middle = (start + end) / 2;

            long total = 0;
            for (long tree: trees) {
                if (tree > middle) {
                    total += tree - middle;
                }
            }

            if (total < M) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return end;
    }
}