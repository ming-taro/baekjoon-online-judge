import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String[] input = reader.readLine().split(" ");
        int mainCount = Integer.parseInt(input[0]);
        int subCount = Integer.parseInt(input[1]);

        long total = numbers.length;
        for (int number: numbers) {
            int current = number - mainCount;

            if (current <= 0) {
                continue;
            }

            if (current % subCount == 0) {
                total += current / subCount;
            } else {
                total += current / subCount + 1;
            }
        }

        System.out.println(total);
    }
}