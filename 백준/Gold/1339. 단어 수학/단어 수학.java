import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String[] word = new String[N];
        Map<Character, Integer> result = new HashMap<>();

        for (int i = 0; i < N; i++) {
            word[i] = reader.readLine();
            for (int j = 0; j < word[i].length(); j++) {
                int value = (int) Math.pow(10, (word[i].length() - j - 1));
                if (result.containsKey(word[i].charAt(j))) {
                    value += result.get(word[i].charAt(j));
                }
                result.put(word[i].charAt(j), value);
            }
        }

        List<Character> list = new ArrayList<>(result.keySet());
        Collections.sort(list, (o1, o2) -> result.get(o2) - result.get(o1));

        int answer = 0;
        int value = 9;
        for (char ch: list) {
            answer += result.get(ch) * value--;
        }

        System.out.println(answer);
    }
}