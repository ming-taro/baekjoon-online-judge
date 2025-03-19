import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] trees = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        System.out.println(calcTree(M, trees));
    }

    private static long calcTree(int target, long[] trees) {
        long left = -1;
        long right = 2_000_000_001;
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (long tree: trees) {
                if (tree > mid) total += tree - mid;
            }
            if (total >= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}