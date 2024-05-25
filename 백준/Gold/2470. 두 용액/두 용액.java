import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static long[] value;
    private static long[] numbers = new long[3];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        value = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        Arrays.sort(value);

        int left = 0;
        int right = N - 1;
        long minValue = Long.MAX_VALUE;
        long[] result = new long[2];

        while (left < right) {
            long total = value[left] + value[right];
            if (Math.abs(total) < Math.abs(minValue)) {
                result[0] = value[left];
                result[1] = value[right];
                minValue = total;
            }

            if (total < 0) { // 음수 -> 값이 더 커져야 하므로 왼쪽 값을 움직임
                left++;
            } else { // 양수 -> 값이 더 작아져야 하므로 오른쪽 값을 움직임
                right--;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}