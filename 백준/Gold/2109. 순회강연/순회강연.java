import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<Integer, List<Integer>> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[][] fee = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fee[i][0] = p;
            fee[i][1] = d;
        }

        for (int[] f: fee) {
            if (!map.containsKey(f[1])) {
                map.put(f[1], new ArrayList<>());
            }
            map.get(f[1]).add(f[0]);
        }

        Queue<Integer> queue = new PriorityQueue<>();
        for (int day: map.keySet()) {
            for (int f : map.get(day)) {
                queue.offer(f);
            }

            while (queue.size() > day) {
                queue.poll();
            }
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            answer += queue.poll();
        }
        System.out.println(answer);
    }
}
