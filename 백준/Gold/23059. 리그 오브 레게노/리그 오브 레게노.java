import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String[][] items = new String[N][2];
        Map<String, Integer> strToInt = new HashMap<>();
        Map<Integer, String> intToStr = new HashMap<>();
        int size = 0;

        for (int i = 0; i < N; i++) {
            items[i] = reader.readLine().split(" ");
            for (int j = 0; j < 2; j++) {
                if (!strToInt.containsKey(items[i][j])) {
                    intToStr.put(size, items[i][j]);
                    strToInt.put(items[i][j], size++);
                }
            }
        }

        int[] distance = new int[size];
        List<Integer>[] parent = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            parent[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            parent[strToInt.get(items[i][0])].add(strToInt.get(items[i][1]));
            distance[strToInt.get(items[i][1])]++;
        }

        Queue<String> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[size];

        for (int i = 0; i < size; i++) {
            if (distance[i] == 0) {
                queue.offer(intToStr.get(i));
            }
        }

        List<String> answer = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<String> list = new ArrayList<>();
            while (!queue.isEmpty()) {
                String current = queue.poll();
                list.add(current);
                answer.add(current);
            }

            for (String current: list) {
                for (int p : parent[strToInt.get(current)]) {
                    distance[p]--;
                    if (distance[p] == 0 && !visited[p]) {
                        queue.offer(intToStr.get(p));
                        visited[p] = true;
                    }
                }
            }
        }

        if (answer.size() != size) {
            System.out.println(-1);
        } else {
            answer.forEach(System.out::println);
        }
    }
}