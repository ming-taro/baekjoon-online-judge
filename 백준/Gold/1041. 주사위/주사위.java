import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long N = Integer.parseInt(reader.readLine());
        long[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        long three = 4;
        long two = ((N - 2) * 4 + 4) + (N - 2) * 4;
        long one = ((N - 2) * 4 + (N - 2) * (N - 2)) + (N - 2) * (N - 2) * 4;

        long minNum = Integer.MAX_VALUE;
        long maxNum = 0;
        long total = 0;
        for (long num: numbers) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
            total += num;
        }

        long sumOfTwo = Integer.MAX_VALUE;
        long sumOfThree = Integer.MAX_VALUE;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (isValid(i, j)) {
                    sumOfTwo = Math.min(sumOfTwo, numbers[i] + numbers[j]);
                }
                for (int k = 0; k < 6; k++) {
                    if (isValid(i, j) && isValid(j, k) && isValid(k, i)) {
                        sumOfThree = Math.min(sumOfThree, numbers[i] + numbers[j] + numbers[k]);
                    }
                }
            }
        }

        if (N == 1) {
            System.out.println(total - maxNum);
            return;
        }
        System.out.println(minNum * one + sumOfTwo * two + sumOfThree * three);
    }

    private static boolean isValid(int x, int y) { // 두 면이 맞닿아 있는지 확인
        return x != y && (x + y) != 5;
    }
}