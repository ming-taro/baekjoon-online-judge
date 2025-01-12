import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[] people;
    private static List<Integer>[] nodes;
    private static boolean[] crew;
    private static int total;
    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        crew = new boolean[N + 1];
        people = new int[N + 1];

        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        String[] input = reader.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(input[i - 1]);
            total += people[i];
        }

        for (int i = 1; i <= N; i++) {
            input = reader.readLine().split(" ");
            for (int j = 1; j < input.length; j++) {
                nodes[i].add(Integer.parseInt(input[j]));
            }
        }

        crew[1] = true;
        dfs(2, people[1], 1);

        System.out.println(answer);
    }

    private static void dfs(int current, int peopleCount, int areaCount) {
        if (check(1, areaCount, true) && check(findNotCrew(), N - areaCount, false)) {
            int difference = Math.abs(peopleCount - (total - peopleCount));
            if (answer == -1) {
                answer = difference;
            } else {
                answer = Math.min(answer, difference);
            }
        }
        if (areaCount == N - 1) return;

        for (int i = current; i <= N; i++) {
            crew[i] = true;
            dfs(i + 1, peopleCount + people[i], areaCount + 1);
            crew[i] = false;
        }
    }

    private static int findNotCrew() {
        for (int i = 1; i <= N; i++) {
            if (!crew[i]) return i;
        }
        return -1;
    }

    private static boolean check(int start, int areaCount, boolean isCrew) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        int area = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next: nodes[current]) {
                if (!visited[next] && crew[next] == isCrew) {
                    visited[next] = true;
                    queue.offer(next);
                    area++;
                }
            }
        }
        return areaCount == area;
    }
}