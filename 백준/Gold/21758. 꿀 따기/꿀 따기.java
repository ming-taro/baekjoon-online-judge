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
            for (int j = i + 1; j < N; j++) {  // 꿀단지 i에서 오른쪽 누적합
                totalHoney[i][j] += totalHoney[i][j - 1] + honey[j - 1];
            }
            for (int j = i - 1; j >= 0; j--) { // 꿀단지 i에서 왼쪽 누적합
                totalHoney[i][j] += totalHoney[i][j + 1] + honey[j + 1];
            }
        }

        long answer = 0;

        // 1. 왼쪽, 오른쪽 끝을 제외한 가운데 꿀단지 -> 왼쪽, 오른쪽 영역에서 각각 최대인 경우의 합
        for (int h = 1; h < N - 1; h++) {
            long maxLeft = 0;
            long maxRight = 0;
            for (int l = 0; l < h; l++) {
                maxLeft = Math.max(maxLeft, totalHoney[h][l]);
            }
            for (int r = h + 1; r < N; r++) {
                maxRight = Math.max(maxRight, totalHoney[h][r]);
            }
            answer = Math.max(answer, maxLeft + maxRight);
        }

        // 2. 왼쪽끝이 꿀단지, 오른쪽에 꿀벌 경로 겹침
        /*
        [1, 2, 3, 4, 5]에서 무조건 꿀단지 오른쪽에 꿀벌이 위치하는 경우를 살펴볼 때,
        꿀단지가 1에 있는 경우가 2, 3에 있는 경우보다 얻을 수 있는 양이 많다

        꿀단지가 1에 있다면 꿀벌은 (2, 3), (2, 4), (2, 5), (3, 4), (3, 5), (4, 5)
        꿀단지가 2에 있다면 꿀벌은 (3, 4), (3, 5), (4, 5)
        꿀단지가 3에 있다면 꿀벌은 (4, 5)

        즉 꿀단지가 왼쪽의 가장 끝인 1에 있는 경우가 나머지 모든 경우의 수를 가지고 있고, 그만큼 넓은 영역에 대해 탐색하게 된다
        따라서 2, 3으로 갈수록 경우의 수도 줄어들 뿐더러 꿀벌이 이동할 수 있는 영역도 줄어들기 때문에
        모든 경우를 탐색하면서 가장 넓은 영역에서 꿀을 얻을 수 있는 왼쪽 끝을 꿀단지로 결정한다
         */
        for (int i = 1; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                answer = Math.max(answer, totalHoney[0][i] + totalHoney[0][j] - honey[i]); // 꿀단지(= 0) < 꿀벌i < 꿀벌j
            }
        }

        // 3. 오른쪽 끝이 꿀단지, 왼쪽에 꿀벌 경로 겹침
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                answer = Math.max(answer, totalHoney[N - 1][i] + totalHoney[N - 1][j] - honey[j]); // 꿀벌i < 꿀벌j < 꿀단지(= N - 1)
            }
        }

        System.out.println(answer);
    }
}