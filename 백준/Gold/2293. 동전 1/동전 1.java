import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    static List<Integer> coins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(reader.readLine()));
        }

        Collections.sort(coins);

        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            int coin = coins.get(i);
            if (coin > k) continue;

            dp[coin] += 1;

            for (int j = coin + 1; j <= k; j++) {
                dp[j] += dp[j - coin];
            }
        }

        System.out.println(dp[k]);
    }
}

/*
coins = [1, 2, 5]

1: 1
2: 1+1 / 2
3: 1+1+1 / 1+2
4: 1+1+1+1 / 1+1+2 / 2+2
5: 1+1+1+1+1 / 1+1+1+2 / 1+2+2
6: 1+1+1+1+1+1 / 1+1+1+1+2 / 1+1+2+2 / 2+2+2
7: 1+1+1+1+1+1+1 / 1+1+1+1+1+2 / 1+1+1+2+2 / 1+2+2+2
...

1. coin=1
(1부터 만들 수 있음)
dp[1]에 (1)의 경우를 추가함 -> dp[1] = 1
dp[2]는 dp[1]에 1을 더한 케이스를 추가함 -> dp[2] = 1(1+1)
dp[3]은 dp[2]에 1을 더한 케이스를 추가함 -> dp[3] = 1(1+1+1)
...

2. coin=2
(2부터 만들 수 있음)
dp[2]에 (2)의 경우를 추가함 -> dp[2] = 2(1+1, 2)
dp[3]은 dp[1]에 2를 더한 케이스를 추가함 -> dp[3] = 2(1+1+1외에 추가된 케이스: 1+2)
dp[4]는 dp[2]에 2를 더한 케이스를 추가함 -> dp[4] = 3(1+1+1+1외에 추가된 케이스: 1+1+2/1+2+2)
dp[5]는 dp[3]에 2를 더한 케이스를 추가함 -> dp[5] = 3(1+1+1+1+1외에 추가된 케이스: 1+1+1+2/1+2+2)
...

3.coin=5
1: 1
2: 1+1 / 2
3: 1+1+1 / 1+2
4: 1+1+1+1 / 1+1+2 / 2+2
5: 1+1+1+1+1 / 1+1+1+2 / 1+2+2 / 5
6: 1+1+1+1+1+1 / 1+1+1+1+2 / 1+1+2+2 / 2+2+2 / 1+5
7: 1+1+1+1+1+1+1 / 1+1+1+1+1+2 / 1+1+1+2+2 / 1+2+2+2 / 1+1+5 / 2+5
(5부터 만들 수 있음)
dp[5]에 (5)의 경우를 추가함 -> dp[5] = 4(추가된 케이스: 5)
dp[6]에 dp[1]에 5를 더한 경우를 추가함 -> dp[6] = 5(추가된 케이스: 1+5)
dp[7]에 dp[2]에 5를 더한 경우를 추가함 -> dp[7] = 6(추가된 케이스: 1+1+5/2+5)
...
 */