import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());
        long[] dp = new long[100];
        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = dp[4] = 2;

        for (int i = 5; i < 100; i++) {
            dp[i] += dp[i - 5] + dp[i - 1];
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine());
            result.append(dp[N - 1]).append("\n");
        }
        System.out.println(result.toString());
    }
}
/*
1) N = 1인 삼각형
2) 1의 왼쪽 변에 마주 하는 N = 1인 삼각형
3) 2의 윗변에 마주 하는 N = 1인 삼각형
4) 1, 3의 오른쪽 변에 마주 하는 N = 2인 삼각형
5) 4의 오른쪽 변에 마주 하는 N = 2인 삼각형
6) 1, 5의 아랫 변에 마주 하는 N = 3인 삼각형
-------------------------------------
7) 2, 6의 왼쪽 변에 마주 하는 N = 4인 삼각형
8) 3, 7의 왼쪽 변에 마주 하는 N = 5인 삼각형
9) 4, 8의 윗변에 마주 하는 N = 7인 삼각형
10) 5, 9의 오른쪽 변에 마주 하는 N = 9인 삼각형
....

삼각형 1개(1, 7, 13..)를 둘러 싸는 5개의 삼각형
=>(N - 5), (N - 1) 번째 삼각형 변의 합

 */