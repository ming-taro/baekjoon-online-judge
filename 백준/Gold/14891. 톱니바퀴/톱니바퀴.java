import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int GO = 1;
    private static final int BACK = -1;
    private static final int STAY = 2;
    private static int WHEEL = 4;
    private static int[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        nodes = new int[WHEEL][8];
        for (int i = 0; i < WHEEL; i++) {
            nodes[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int node = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(node - 1);
            int[] directions = new int[WHEEL];
            directions[node - 1] = direction;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (directions[current] == STAY) continue;

                if (current - 1 >= 0 && directions[current -1] == 0) {
                    directions[current - 1] = runLeftWheel(current, directions[current]);
                    queue.offer(current - 1);
                }
                if (current + 1 < WHEEL && directions[current + 1] == 0) {
                    directions[current + 1] = runRightWheel(current, directions[current]);
                    queue.offer(current + 1);
                }
            }

            for (int j = 0; j < WHEEL; j++) {
                if (directions[j] == GO) {
                    go(j);
                } else if (directions[j] == BACK) {
                    back(j);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < WHEEL; i++) {
            if (nodes[i][0] == 1) answer += (int) Math.pow(2, i);
        }
        System.out.println(answer);
    }

    // 오른쪽 톱니바퀴가 회전할 때 왼쪽 톱니바퀴의 움직임
    private static int runLeftWheel(int target, int direction) {
        if (nodes[target - 1][2] == nodes[target][6]) {
            return STAY;
        }
        if (direction == BACK && nodes[target - 1][2] != nodes[target][6]) {
            return GO;
        }
        return BACK;
    }

    // 왼쪽 톱니바퀴가 회전할 때 오른쪽 톱니바퀴의 움직임
    private static int runRightWheel(int target, int direction) {
        if (nodes[target][2] == nodes[target + 1][6]) {
            return STAY;
        }
        if (direction == BACK && nodes[target][2] != nodes[target + 1][6]) {
            return GO;
        }
        return BACK;
    }

    private static void go(int node) {
        int len = nodes[node].length;
        int last = nodes[node][len - 1];
        for (int i = len - 1; i > 0; i--) {
            nodes[node][i] = nodes[node][i - 1];
        }
        nodes[node][0] = last;
    }

    private static void back(int node) {
        int len = nodes[node].length;
        int first = nodes[node][0];
        for (int i = 0; i < len - 1; i++) {
            nodes[node][i] = nodes[node][i + 1];
        }
        nodes[node][len - 1] = first;
    }
}