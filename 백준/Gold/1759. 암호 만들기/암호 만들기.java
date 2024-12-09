import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[] input = reader.readLine().split(" ");
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("e");
        set.add("i");
        set.add("o");
        set.add("u");

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (String str: input) {
            if (set.contains(str)) {
                list1.add(str);
            } else {
                list2.add(str);
            }
        }

        Map<Integer, List<String>> result1 = new HashMap<>();
        Map<Integer, List<String>> result2 = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            result1.put(i, new ArrayList<>());
            result2.put(i, new ArrayList<>());
        }

        dfs(result1, list1, "", 0);
        dfs(result2, list2, "", 0);

        Set<String> keys = new HashSet<>();

        for (int count2 = 2; count2 <= list2.size(); count2++) {
            int count1 = L - count2;
            if (count1 < 1) break;

            for (String s1: result1.get(count1)) {
                for (String s2: result2.get(count2)) {
                    String[] result = (s1 + s2).split("");
                    Arrays.sort(result);
                    keys.add(String.join("", result));
                }
            }
        }

        String[] answer = new String[keys.size()];
        int index = 0;
        for (String key: keys) {
            answer[index++] = key;
        }
        Arrays.sort(answer);

        for (String str: answer) {
            System.out.println(str);
        }
    }

    private static void dfs(Map<Integer, List<String>> result, List<String> list, String key, int current) {
        if (!key.isEmpty()) {
            result.get(key.length()).add(key);
        }

        for (int i = current; i < list.size(); i++) {
            dfs(result, list, key + list.get(i), i + 1);
        }
    }
}