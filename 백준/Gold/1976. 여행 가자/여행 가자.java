import java.io.*;
import java.util.*;

public class Main {
    private static List<Integer>[] board;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());  //도시 수
        int M = Integer.parseInt(reader.readLine());  //여행 계획에 속한 도시 수
        board = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            board[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer input = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                if (input.nextToken().equals("1")) { //연결 되어 있음
                    board[i].add(j);
                    board[j].add(i);
                }
            }
        }

        int[] plan = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();   //여행 계획

        for (int i = 0; i < M - 1; i++) {
            if (!canGo(plan[i], plan[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean canGo(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                return true;
            }

            for (int next: board[current]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        return false;
    }
}