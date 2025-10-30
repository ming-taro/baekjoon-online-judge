import java.io.*;
import java.util.*;

class Main {
    private static List<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> word = new ArrayList<>();
        Map<String, Set<Integer>> comb = new HashMap<>(); // 단어조합, Set<>(원본 단어 인덱스)

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            word.add(input);
            for (int j = 1; j <= input.length(); j++) {
                String key = input.substring(0, j);
                Set<Integer> prev = comb.getOrDefault(key, new HashSet<>());
                prev.add(i);
                comb.put(key, prev);
            }
        }

        String target = null;

        for (int i = 0; i < N; i++) {
            for (int j = word.get(i).length(); j >= 1; j--) {
                String key = word.get(i).substring(0, j);
                if (comb.containsKey(key) && comb.get(key).size() > 1) {
                    if (target == null || target.length() < key.length()) {
                        target = key;
                    }
                    break;
                }
            }
        }

        if (target == null) return;

        List<Integer> answer = new ArrayList<>(comb.get(target));
        answer.sort((o1, o2) -> o1 - o2);
        System.out.println(word.get(answer.get(0)) + "\n" + word.get(answer.get(1)));
    }
}