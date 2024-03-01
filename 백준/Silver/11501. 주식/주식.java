import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(reader.readLine());
            int[] numbers = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            long total = 0;
            int maxPrice = numbers[N - 1];
            for (int j = N - 1; j >= 0; j--) {
                if (numbers[j] > maxPrice) {
                    maxPrice = numbers[j];
                }
                if (numbers[j] < maxPrice) {
                    total += maxPrice - numbers[j];
                }
            }
            System.out.println(total);
        }

    }
}
/*
1 1 3 1 2
->1(3)은 2(4)일 때 팔아야 이득
->1(0), 1(2)은 3(2)일 때 팔아야 이득
*/