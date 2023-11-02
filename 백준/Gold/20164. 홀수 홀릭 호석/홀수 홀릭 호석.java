import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int maxCount = 0;
    private static int minCount = 999999999;

    private static int calcOdd(String number) {
        int oddCount = 0;

        for (int index = 0; index < number.length(); index++) {
            if ((number.charAt(index) - '0') % 2 == 1) {
                oddCount += 1;
            }
        }

        return oddCount;
    }

    private static String addNumber(String first, String second, String third) {
        int sum = Integer.parseInt(first) + Integer.parseInt(second) + Integer.parseInt(third);
        return Integer.toString(sum);
    }

    private static void divideNumber(String number, int oddCount) {
        if (number.length() == 1) {                    //한 자리 수일 때까지 재귀 반복
            oddCount += calcOdd(number);

            maxCount = Math.max(maxCount, oddCount);
            minCount = Math.min(minCount, oddCount);
        } else if (number.length() == 2) {
            oddCount += calcOdd(number);
            number = Integer.toString((number.charAt(0) - '0') + (number.charAt(1) - '0'));

            divideNumber(number, oddCount);
        } else {
            for (int i = 0; i < number.length() - 2; i++) {
                for (int j = i + 1; j < number.length() - 1; j++) {
                    int count = calcOdd(number);

                    String first = number.substring(0, i + 1);
                    String second = number.substring(i + 1, j + 1);
                    String third = number.substring(j + 1);

                    divideNumber(addNumber(first, second, third), oddCount + count);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String N = reader.readLine();

        divideNumber(N, 0);

        System.out.println(minCount + " " + maxCount);
    }
}