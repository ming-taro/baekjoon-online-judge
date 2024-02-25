import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String name = reader.readLine();

        SortedMap<Character, Integer> alphabet = new TreeMap<>();

        for (int i = 0; i < name.length(); i++) {
            char key = name.charAt(i);
            if (alphabet.containsKey(key)) {
                alphabet.put(key, alphabet.get(key) + 1);
            } else {
                alphabet.put(key, 1);
            }
        }

        int oddCount = 0;
        Character oddCountAlphabet = 'A';

        for (Character key: alphabet.keySet()) {
            if (alphabet.get(key) % 2 == 1) {
                oddCountAlphabet = key;
                alphabet.put(key, alphabet.get(key) - 1);
                oddCount++;
            }
            if (oddCount > 1) {  //개수가 홀수개인 알파벳이 2개 이상인 경우 양쪽의 균형을 맞출 수 없음
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }
        
        Queue<Character> right = new LinkedList<>();
        Deque<Character> left = new LinkedList<>();

        for (Character key: alphabet.keySet()) {
            for (int i = 0; i < alphabet.get(key) / 2; i++) {
                right.offer(key);
                left.offerFirst(key);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!right.isEmpty()) {
            result.append(right.poll());
        }
        if (oddCount == 1) {
            result.append(oddCountAlphabet);
        }
        while (!left.isEmpty()) {
            result.append(left.pollFirst());
        }

        System.out.println(result.toString());
    }
}