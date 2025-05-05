import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static long[] honey;
    private static long[] leftHoney;
    private static long[] rightHoney;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        honey = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        leftHoney = new long[N]; // 자신을 제외한 누적합
        rightHoney = new long[N];
        for (int l = 1; l < N; l++) {
            leftHoney[l] = leftHoney[l - 1] + honey[l - 1];
        }
        for (int r = N - 2; r >= 0; r--) {
            rightHoney[r] = rightHoney[r + 1] + honey[r + 1];
        }

        long answer = 0;

        // 1. 가운데 꿀단지, 양쪽 끝에 꿀벌 위치
        /*
        h칸에 꿀단지가 있을 때 최대치는 양끝(0, N - 1)에 꿀벌이 있는 경우
        꿀벌0번이 모은 꿀 양 = h칸까지 왼쪽 누적합 + 꿀단지 - 0번칸의 꿀
        꿀번(N - 1)번이 모은 꿀 양 = h칸까지 오른쪽 누적합 + 꿀단지 - (N - 1)번칸의 꿀
         */
        for (int h = 1; h < N - 1; h++) {
            answer = Math.max(answer, leftHoney[h] + rightHoney[h] + honey[h] * 2 - honey[0] - honey[N - 1]);
        }

        // 2. 왼쪽끝 꿀단지, 오른쪽에 꿀벌 경로 겹침 -> 꿀단지 < r1 < r2
        /*
        0번칸에 꿀단지가 있을 때 -> (1 ~ N-1)칸에 꿀벌 2마리가 있는 경우

        [1, 2, 3, 4, 5]
        위에서 1번에 꿀단지가 있는 경우,
        (2, 5)와 (2, 4)가 꿀벌인 경우에서 더 큰 값은 (2, 5)에 꿀벌이 있는 경우다
        즉, 1번에 꿀단지가 있다면 가장 많은 꿀을 얻는 꿀벌은 무조건 오른쪽 끝인 5번 꿀벌이다
        때문에 5번에 꿀벌 한 마리를 고정하고 나머지 2, 3, 4 중 꿀벌이 한 마리 있을 때 가장 큰 경우를 구하면 된다
         */
        for (int r = 1; r < N - 1; r++) {
            answer = Math.max(answer, leftHoney[r] + leftHoney[N - 1] - honey[r]);
        }

        // 3. 오른쪽끝 꿀단지, 왼쪽에 꿀벌 경로 겹침 -> l1 < l2 < 꿀단지
        /*
        (N - 1)번칸에 꿀단지가 있을 때 -> (0 ~ N-2)칸에 꿀벌 2마리가 있는 경우
         */
        for (int l = 1; l < N - 1; l++) {
            answer = Math.max(answer, rightHoney[l] + rightHoney[0] - honey[l]);
        }

        System.out.println(answer);
    }
}