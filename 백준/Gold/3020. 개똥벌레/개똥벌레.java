import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken()); // 동굴 길이(짝수)
        int H = Integer.parseInt(st.nextToken()); // 높이

        int[] upSet = new int[N / 2];     // 석순
        int[] downSet = new int[N / 2];   // 종유석
        int index = 0;
        for (int i = 0; i < N; i++) {
            int l = Integer.parseInt(reader.readLine());
            if (i % 2 == 0) upSet[index] = l;
            else downSet[index++] = l;
        }

        Stack<Integer> up = orderReverse(upSet);
        Stack<Integer> down = orderReverse(downSet);

        int[][] dp = new int[2][H + 2];
        for (int i = 1, h = H; i <= H; i++, h--) { // 석순
            dp[0][i] += dp[0][i - 1];
            while (!up.isEmpty() && up.peek() == h) {
                up.pop();
                dp[0][i]++;
            }
        }
        for (int i = H, h = H; i >= 1; i--, h--) { // 종유석
            dp[1][i] += dp[1][i + 1];
            while (!down.isEmpty() && down.peek() == h) {
                down.pop();
                dp[1][i]++;
            }
        }

        int answer = N;
        int range = 0;
        for (int h = 1; h <= H; h++) {
            int result = dp[0][h] + dp[1][h];
            if (result == answer) {
                range++;
            } else if (result < answer){
                answer = result;
                range = 1;
            }
        }
        System.out.println(answer + " " + range);
    }

    private static Stack<Integer> orderReverse(int[] array) {
        Arrays.sort(array);
        Stack<Integer> stack = new Stack<>();
        for (int a: array) stack.push(a);
        return stack;
    }
}