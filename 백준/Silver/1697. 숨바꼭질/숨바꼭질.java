import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static final int maxPoint = 100_000;
    private static int[] counts;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());

        counts = new int[maxPoint + 1];

        bfs();
        System.out.println(counts[K]);
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty()) {
            int currentPoint = queue.poll();

            if (currentPoint == K) {
                return;
            }

            int[] moves = {currentPoint + 1, currentPoint - 1, currentPoint * 2};

            for (int nextPoint: moves) {
                if (nextPoint >= 0 && nextPoint <= maxPoint && counts[nextPoint] == 0) {
                    counts[nextPoint] = counts[currentPoint] + 1;  //이동에 1초 소요
                    queue.add(nextPoint);
                }
            }
        }
    }
}