import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();
        String bombText = reader.readLine();
        char lastText = bombText.charAt(bombText.length() - 1);

        Stack<Character> textStack = new Stack<>();
        for (int i = text.length() - 1; i >= 0; i--) {
            textStack.push(text.charAt(i));
        }

        Stack<Character> result = new Stack<>();
        while (!textStack.isEmpty()) {
            result.push(textStack.pop());
            if (result.size() >= bombText.length() && result.peek() == lastText) {
                checkBomb(result, bombText);   //스택의 top이 bomb문자의 마지막 문자와 일치하면 터트릴 수 있는지 확인
            }
        }

        if (result.isEmpty()) {
            System.out.println("FRULA");
        } else {
            System.out.println(
                    result.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(""))
            );
        }
    }

    private static void checkBomb(Stack<Character> result, String bombText) {
        Stack<Character> tmp = new Stack<>();
        int index = bombText.length() - 1;

        while (index >= 0) {
            if (result.peek() == bombText.charAt(index)) {
                tmp.push(result.pop());
                index--;
            } else {
                while (!tmp.isEmpty()) {
                    result.push(tmp.pop());
                }
                break;
            }
        }
    }
}