import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        List<Integer> numbers = new ArrayList<>();
        List<Integer> restNumbers = new ArrayList<>();
        boolean[] isRanked = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(reader.readLine());
            if (input > N || isRanked[input]) {
                numbers.add(input);
            } else {
                isRanked[input] = true;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!isRanked[i]) {
                restNumbers.add(i);
            }
        }

        Collections.sort(numbers);
        long sum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            sum += Math.abs(numbers.get(i) - restNumbers.get(i));
        }

        System.out.println(sum);
    }
}