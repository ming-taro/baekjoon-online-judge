import java.io.*;
import java.util.*;

class Main {
    private static int n;
    private static boolean[] door;
    private static int order;
    private static int[] target;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());   // 벽장 = n개, 문 = (n - 2)개
        door = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(reader.readLine());
        door[Integer.parseInt(st.nextToken())] = true; // 문 2개
        door[Integer.parseInt(st.nextToken())] = true;

        order = Integer.parseInt(reader.readLine()); // 사용할 벽장 순서
        target = new int[order];
        for (int i = 0; i < order; i++) {
            target[i] = Integer.parseInt(reader.readLine());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int current, int moveCount) {
        if (current == order) {
            answer = Math.min(answer, moveCount);
            return;
        }

        if (door[target[current]]) { // 사용 하려는 벽장 앞에 이미 문이 없는 경우
            dfs(current + 1, moveCount);
            return;
        }

        int left = target[current];
        while (left >= 0 && !door[left]) left--; // 왼쪽 방향으로 벽장 밀기
        if (left >= 0) {
            door[target[current]] = true;
            door[left] = false;
            dfs(current + 1, moveCount + (target[current] - left));
            door[target[current]] = false;
            door[left] = true;
        }

        int right = target[current];
        while (right <= n && !door[right]) right++; // 오른쪽 방향으로 벽장 밀기
        if (right <= n) {
            door[target[current]] = true;
            door[right] = false;
            dfs(current + 1, moveCount + (right - target[current]));
            door[target[current]] = false;
            door[right] = true;
        }
    }
}