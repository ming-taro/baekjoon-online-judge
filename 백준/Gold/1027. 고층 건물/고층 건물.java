import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        long[] height = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        int[] answer = new int[N];
        for (int x1 = 0; x1 < N - 1; x1++) {
            double maxValue = height[x1 + 1] - height[x1] - 1;  // 탐색하려는 빌딩과의 기울기 최댓값
            for (int x2 = x1 + 1; x2 < N; x2++) {
                double current = (double) (height[x2] - height[x1]) / (x2 - x1);
                if (maxValue < current) { // 현재까지 탐색한 빌딩과의 기울기중 가장 큰 기울기보다 큰 경우, 즉 갱신되는 경우 볼 수 있음
                    answer[x1]++;
                    answer[x2]++;
                    maxValue = current;
                }
            }
        }

        int maxAnswer = 0;
        for (int a: answer) {
            maxAnswer = Math.max(maxAnswer, a);
        }
        System.out.println(maxAnswer);
    }
}