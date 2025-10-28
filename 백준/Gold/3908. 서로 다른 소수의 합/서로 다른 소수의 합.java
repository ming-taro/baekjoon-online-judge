import java.io.*;
import java.util.*;

class Main {
    private static List<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        calcPrime();

        int maxNum = 1120;
        int maxK = 14;
        int[][] dp = new int[maxNum + 1][maxK + 1]; // [1]개의 소수로 [0]만들기
        dp[0][0] = 1;

        /*
        prime.get(i)로 표현할 수 있는 가장 작은 숫자는 prime.get(i), 가장 큰 숫자는 1120 -> j범위

        prime.get(i)가 prime.get(i) ~ 1120까지의 숫자 표현시 포함되는 경우를 구하려고 할 때,
        0 ~ 13개의 소수를 사용해 구한 각각의 경우의 수에 계속해서 누적해감

        소수 2의 경우 2 <= j <= 1120 범위 내의 숫자들 중 2가 포함되는 경우의 수를 구해야 한다
        dp[j][k]는 j를 k개의 소수로 표현한 경우의 수를 의미한다

        현재 j를 표현하기 위해 2가 포함되면서, 2를 포함했을 때 소수의 개수가 k가 되는 경우를 구해야 한다
        즉, 'x + 2 = j'가 되는 x라는 숫자가 (k - 1)개의 소수로 표현되는 경우의 수를 더해주면 된다
        x를 (k - 1)개의 소수로 표현한 경우에 2를 추가하면 j를 만들면서 k개의 소수 집합이 되기 때문이다

        즉, 소수 2를 포함하여 dp[j][k]가 되는 경우의 수는 dp[j - 2][k - 1]인 경우의 수다
        이런식으로 모든 소수에 대해 연산을 하여 경우의 수를 모두 더해주면 된다
         */
        for (int i = 0; i < prime.size(); i++) {
            for (int j = maxNum; j >= prime.get(i); j--) {
                for (int k = 1; k <= maxK; k++) {
                    dp[j][k] += dp[j - prime.get(i)][k - 1];
                }
            }
        }

        int T = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            answer.append(dp[n][k] + "\n");
        }

        System.out.println(answer.toString());
    }

    private static void calcPrime() {
        int maxNum = 1120;
        boolean[] valid = new boolean[maxNum + 1];

        for (int num = 2; num <= Math.sqrt(maxNum); num++) {
            if (valid[num]) continue;
            for (int i = num * 2; i <= maxNum; i += num) {
                valid[i] = true;
            }
        }

        for (int num = 2; num <= maxNum; num++) {
            if (!valid[num]) prime.add(num);
        }
    }
}