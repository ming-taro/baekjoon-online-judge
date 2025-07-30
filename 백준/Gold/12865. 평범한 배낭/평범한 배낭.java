import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] objects = new int[N + 1][2]; // (W, V)

        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(reader.readLine());
            objects[n][0] = Integer.parseInt(st.nextToken());
            objects[n][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int k = 1; k <= K; k++) {
            for (int n = 1; n <= N; n++) {
                dp[n][k] = dp[n - 1][k];

                if (objects[n][0] <= k) {
                    dp[n][k] = Math.max(dp[n][k], dp[n - 1][k - objects[n][0]] + objects[n][1]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}