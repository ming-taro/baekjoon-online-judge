import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main {
    private static int N;
    private static int[] hp;
    private static int[] damage = { 9, 3, 1 };
    private static List<int[]> attackComb = new ArrayList<>();
    private static int[][][] dp;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine()); // 강호가 가지고 있는 SCV 개수
        hp = Arrays.stream(reader.readLine().split(" ")) // 남아 있는 체력
                .mapToInt(Integer::parseInt)
                .toArray();
        dp = new int[61][61][61];

        boolean[] visited = new boolean[N];
        createAttack(0, new int[N], visited);

        dfs(0);
        System.out.println(answer);
    }

    private static void createAttack(int current, int[] comb, boolean[] visited) {
        if (current == N) {
            int[] newComb = new int[N];
            for (int i = 0; i < N; i++) newComb[i] = comb[i];
            attackComb.add(newComb);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                comb[current] = damage[i];
                createAttack(current + 1, comb, visited);
                visited[i] = false;
            }
        }
    }

    private static void dfs(int totalCount) {
        boolean flag = false;
        for (int number: hp) {
            if (number > 0) flag = true;
        }
        if (!flag) {
            answer = Math.min(answer, totalCount);
            return;
        }

        for (int[] attack: attackComb) {    // 공격 패턴
            int[] idx = new int[3];
            for (int i = 0; i < N; i++) {
                hp[i] -= attack[i];
                if (hp[i] > 0) idx[i] = hp[i];
            }
            if (dp[idx[0]][idx[1]][idx[2]] == 0 || totalCount < dp[idx[0]][idx[1]][idx[2]]) {
                dp[idx[0]][idx[1]][idx[2]] = totalCount;
                dfs(totalCount + 1);
            }
            for (int i = 0; i < N; i++) {
                hp[i] += attack[i];
            }
        }
    }
}