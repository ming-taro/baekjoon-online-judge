import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        Stack<Character> stack = new Stack<>();
        int answer = 0;

        for (int i = 0; i < input.length(); i++) {
            if (i + 1< input.length() && input.charAt(i) == '(' && input.charAt(i + 1) == ')') {
                answer += stack.size();
                i++;
                continue;
            }
            if (input.charAt(i) == ')') {
                stack.pop();
                answer++;
                continue;
            }
            stack.push(input.charAt(i));
        }

        System.out.println(answer);
    }
}