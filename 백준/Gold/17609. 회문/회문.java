import java.io.*;
import java.util.*;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String text = reader.readLine();
            int result = Math.min(calcPalindrome(text, true), calcPalindrome(text, false));

            stringBuilder.append(result).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    private static int calcPalindrome(String text, boolean isStart) {
        int start = 0;
        int end = text.length() - 1;
        int result = 0;
        boolean isDifference = false;

        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                if (isDifference) {
                    return 2;
                }

                isDifference = true;

                if (isStart) {
                    result = 1;
                    start++;
                } else {
                    result = 1;
                    end--;
                }

                if (text.charAt(start) != text.charAt(end)) {
                    return 2;
                }
            }
            start++;
            end--;
        }

        return result;
    }
}