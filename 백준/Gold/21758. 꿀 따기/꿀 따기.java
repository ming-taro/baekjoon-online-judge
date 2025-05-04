import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        long[] honey = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        long[][] totalHoney = new long[N][N];
        for (int i = 0; i < N; i++) {
            totalHoney[i][i] = honey[i];
            for (int j = i + 1; j < N; j++) {  // 꿀단지 i에서 오른쪽 누적합
                totalHoney[i][j] += totalHoney[i][j - 1] + honey[j];
            }
            for (int j = i - 1; j >= 0; j--) { // 꿀단지 i에서 왼쪽 누적합
                totalHoney[i][j] += totalHoney[i][j + 1] + honey[j];
            }
        }

        long answer = 0;

        for (int h = 0; h < N; h++) { // 꿀단지
            for (int i = 0; i < N - 1; i++) {
                if (i == h) continue;
                for (int j = i + 1; j < N; j++) {
                    if (j == h) continue;
                    long current = totalHoney[h][i] + totalHoney[h][j] - honey[i] - honey[j]; // 꿀양 = 누적 꿀단지합 - i의꿀 - j의꿀
                    if (i < j && j < h) current -= honey[j]; // i < j < 꿀단지 : j꿀이 i의 누적꿀에 포함되므로 한번 뺌
                    if (h < i && i < j) current -= honey[i]; // 꿀단지 < i < j : i꿀이 j의 누적꿀에 포함되므로 한번 뺌

                    answer = Math.max(answer, current);
                }
            }
        }

        System.out.println(answer);
    }
}