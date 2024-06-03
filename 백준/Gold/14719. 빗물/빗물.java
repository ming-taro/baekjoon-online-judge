import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());
        int H = Integer.parseInt(input.nextToken());
        int W = Integer.parseInt(input.nextToken());

        int[] rain = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] startMax = new int[W];
        int[] endMax = new int[W];

        startMax[0] = rain[0];        // 앞에서부터 시작 -> 내 앞에 있는 높이까지의 범위 중 나보다 크거나 같은 높이 저장
        endMax[W - 1] = rain[W - 1];  // 뒤에서부터 시작 -> 내 뒤에 있는 높이까지의 범위 중 나보다 크거나 같은 높이 저장

        for (int i = 1; i < W; i++) {
            if (startMax[i - 1] < rain[i]) {
                startMax[i] = rain[i];
            } else {
                startMax[i] = startMax[i - 1];
            }

            if (endMax[W - i] < rain[W - 1 - i]) {
                endMax[W - 1 - i] = rain[W - 1 - i];
            } else {
                endMax[W - i - 1] = endMax[W - i];
            }
        }

        int total = 0;

        for (int i = 0; i < W; i++) {
            total += Math.min(startMax[i], endMax[i]) - rain[i]; // 내 앞에 있는 높이, 뒤에 있는 높이 중 가장 작은 높이와의 높이차만큼 빗물이 고이게 된다
        }

        System.out.println(total);
    }
}