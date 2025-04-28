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

        n = Integer.parseInt(reader.readLine());       // 벽장 = n개, 문 = (n - 2)개
        door = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int door1 = Integer.parseInt(st.nextToken());  // 문 2개
        int door2 = Integer.parseInt(st.nextToken());

        order = Integer.parseInt(reader.readLine());   // 사용할 벽장 순서
        target = new int[order];
        for (int i = 0; i < order; i++) {
            target[i] = Integer.parseInt(reader.readLine());
        }

        dfs(door1, door2, 0, 0);
        System.out.println(answer);
    }

    private static void dfs(int door1, int door2, int currentTarget, int moveCount) {
        if (currentTarget == order) {
            answer = Math.min(answer, moveCount);
            return;
        }

        dfs(target[currentTarget], door2, currentTarget + 1, moveCount + Math.abs(door1 - target[currentTarget])); // 첫 번째 문 사용 -> 첫 번째 문은 현재 타겟으로 변경 + 두 번째 문은 그대로, index 차만큼 이동 횟수 증가
        dfs(door1, target[currentTarget], currentTarget + 1, moveCount + Math.abs(door2 - target[currentTarget])); // 두 번째 문 사용 -> 두 번째 문은 현재 타겟으로 변경 + 첫 번째 문은 그대로, index 차만큼 이동 횟수 증가
    }
}