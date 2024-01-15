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
        Stack<Integer> numberStack = new Stack<>();

        for (char n : numberInput.toCharArray()) {
            int number = Character.getNumericValue(n);

            while (K > 0 && !numberStack.empty() && number > numberStack.peek()) {
                numberStack.pop();
                K--;
            }

            if (numberStack.empty() || K == 0 || number <= numberStack.peek()) {
                numberStack.add(number);
            }
        }

        while (K > 0) {
            numberStack.pop();
            K--;
        }

        StringBuilder result = new StringBuilder();

        while (!numberStack.isEmpty()) {
            result.append(numberStack.pop());
        }

        System.out.println(result.reverse().toString());
    }
}