import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        String numberInput = reader.readLine();
        Deque<Integer> numberDeque = new ArrayDeque<>();

        for (char n : numberInput.toCharArray()) {
            int number = Character.getNumericValue(n);

            while (K > 0 && !numberDeque.isEmpty() && number > numberDeque.peekLast()) {
                numberDeque.pollLast();
                K--;
            }

            if (numberDeque.isEmpty() || K == 0 || number <= numberDeque.peekLast()) {
                numberDeque.add(number);
            }
        }

        while (K > 0) {
            numberDeque.pollLast();
            K--;
        }

        StringBuilder result = new StringBuilder();

        while (!numberDeque.isEmpty()) {
            result.append(numberDeque.poll());
        }

        System.out.println(result.toString());
    }
}