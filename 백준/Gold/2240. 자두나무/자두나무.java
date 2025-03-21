import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] fruit = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            fruit[i] = Integer.parseInt(reader.readLine());
        }

        int[][][] dp = new int[3][T + 1][W + 2];    // dp[나무][초][이동]
        for (int t = 1; t <= T; t++) {
            for (int w = 1; w <= W + 1; w++) {
                if (fruit[t] == 1) {
                    dp[1][t][w] = Math.max(dp[1][t - 1][w], dp[2][t - 1][w - 1]) + 1; // 1->1번 나무 vs 2->1번 나무 + 1
                    dp[2][t][w] = Math.max(dp[2][t - 1][w], dp[1][t - 1][w - 1]);     // 2->2번 나무 vs 1->2번 나무
                } else {
                    /*
                    1초에 2번 자두가 열리는 경우, 1->2로 움직여야만 자두를 먹을 수 있다
                    즉, w >= 2부터 가능하다
                    따라서 1초일 때 움직이지 않은 상태(w = 1)에서 2번 자두를 먹는 것을 불가하기 때문에
                    t = 1초일 때 w = 1인 경우를 제외하고 계산해야 한다
                     */
                    if (t == 1 && w == 1) continue;
                    dp[1][t][w] = Math.max(dp[1][t - 1][w], dp[2][t - 1][w - 1]);     // 1->1 vs 2->1
                    dp[2][t][w] = Math.max(dp[2][t - 1][w], dp[1][t - 1][w - 1]) + 1; // 2->2 vs 1->2
                }
            }
        }

        int answer = 0;
        for (int w = 1; w <= W + 1; w++) {
            answer = Math.max(answer, Math.max(dp[1][T][w], dp[2][T][w]));
        }
        System.out.println(answer);
    }
}