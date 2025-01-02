import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();
        int N = Integer.parseInt(reader.readLine());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            input += input;
            int prevLen = input.length();
            input = input.replaceAll(text, "");
            if (prevLen > input.length()) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}