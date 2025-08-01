import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        StringBuilder answer = new StringBuilder();
        dfs(0, new int[M], answer);
        System.out.println(answer);
    }

    private static void dfs(int current, int[] numbers, StringBuilder answer) {
        if (current == M) {
            for (int num: numbers) {
                answer.append(num).append(" ");
            }
            answer.append("\n");
            return;
        }

        for (int num = 1; num <= N; num++) {
            numbers[current] = num;
            dfs(current + 1, numbers, answer);
        }
    }
}