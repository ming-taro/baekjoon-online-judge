import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] number = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int count = Integer.parseInt(reader.readLine());

        int[] total = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            total[i] = total[i - 1] + number[i - 1];
        }

        int[] people = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i - count < 0) people[i] = total[i];
            else people[i] = total[i] - total[i - count];
        }

        int[][] dp = new int[3][N + 1]; // 소형 기관차 3대, 기차칸 N개
        for (int i = 1; i <= N; i++) {
            /*
             기관차 1대 -> 이전까지 구한 최대 손님수 vs 현재 기관차의 손님수
             기관차 2대 -> 이전까지 구한 최대 손님수 vs count칸 이전까지 구한 기관차 1대의 최대 손님수 + 현재 기관차의 손님수
            */
            dp[0][i] = Math.max(dp[0][i - 1], people[i]);
            if (i >= count) {
                dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - count] + people[i]);
                dp[2][i] = Math.max(dp[2][i - 1], dp[1][i - count] + people[i]);
            } else {
                dp[1][i] = people[i];
                dp[2][i] = people[i];
            }
        }

        System.out.println(Math.max(dp[0][N], Math.max(dp[1][N], dp[2][N])));
    }
}