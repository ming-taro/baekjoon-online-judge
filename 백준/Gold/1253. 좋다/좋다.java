import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(numbers);

        int result = 0;

        for (int index = 0; index < N; index++) {
            if(isCalculated(index)) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    private static boolean isCalculated(int current) {
        int start = 0;
        int end = N - 1;

        while (start < N - 1 && end >= 0 && start < end) {
            if (start == current || numbers[current] > numbers[start] + numbers[end]) {
                start++;
                continue;
            }
            if (end == current || numbers[current] < numbers[start] + numbers[end]) {
                end--;
                continue;
            }

            return true;
        }

        return false;
    }
}