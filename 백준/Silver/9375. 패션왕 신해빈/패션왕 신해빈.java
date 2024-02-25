import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());
        Map<String, Integer> items = new HashMap<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(reader.readLine());
            items.clear();
            for (int j = 0; j < n; j++) {
                String[] input = reader.readLine().split(" ");

                if (items.containsKey(input[1])) {
                    items.put(input[1], items.get(input[1]) + 1);
                } else {
                    items.put(input[1], 1);
                }
            }

            int total = 1;

            for (String item: items.keySet()) {
                total *= items.get(item) + 1;
            }
            result.append(total - 1).append("\n");
        }

        System.out.println(result.toString());
    }
}
/*
->한번 입은 옷의 조합을 다시는 X(추가로 다른 제품을 입음 or 대체 제품(ex 안경 대신 렌즈))
ex1) [2, 1] -> 3*2 - 1
ex2) [3] -> 4 - 1
 */