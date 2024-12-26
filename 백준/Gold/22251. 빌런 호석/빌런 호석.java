import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int number;
    int count;
    public Node (int number, int count) {
        this.number = number;
        this.count = count;
    }
}

public class Main {
    private static int N;
    private static int K;
    private static int P;
    private static final int RANGE = 10;
    private static List<Node>[] nodes;
    private static Set<Integer> answer = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] initialNumber = new int[K];
        int tmp = X;
        int index = K - 1;
        while (tmp > 0) {
            initialNumber[index--] = tmp % 10;
            tmp /= 10;
        }

        Set<Integer>[] numbers = new Set[RANGE];
        numbers[0] = Set.of(0, 1, 2, 4, 5, 6);
        numbers[1] = Set.of(2, 5);
        numbers[2] = Set.of(0, 2, 3, 4, 6);
        numbers[3] = Set.of(0, 2, 3, 5, 6);
        numbers[4] = Set.of(1, 2, 3, 5);
        numbers[5] = Set.of(0, 1, 3, 5, 6);
        numbers[6] = Set.of(0, 1, 3, 4, 5, 6);
        numbers[7] = Set.of(0, 2, 5);
        numbers[8] = Set.of(0, 1, 2, 3, 4, 5, 6);
        numbers[9] = Set.of(0, 1, 2, 3, 5, 6);

        int[][] change = new int[RANGE][RANGE];
        for (int i = 0; i < RANGE; i++) {
            for (int j = 0; j < RANGE; j++) {
                if (i == j) continue;
                int count = 0;
                for (int num: numbers[i]) {
                    if (!numbers[j].contains(num)) count++;
                }
                change[i][j] += count;
                change[j][i] += count;
            }
        }

        nodes = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int digit = 0; digit < K; digit++) {
            int num = initialNumber[digit];
            for (int target = 0; target < RANGE; target++) {
                if (change[num][target] > 0) {
                    nodes[digit].add(new Node(target, change[num][target]));
                }
            }
        }

        dfs(initialNumber, 0, 0);
        System.out.println(answer.size());
    }

    private static void dfs(int[] initialNumber, int depth, int count) {
        if (depth == K) {
            int result = 0;
            for (int num: initialNumber) {
                result = result * 10 + num;
            }
            if (result > 0 && result <= N && count > 0 && count <= P) {
                answer.add(result);
            }
            return;
        }

        for (Node node: nodes[depth]) {
            int prev = initialNumber[depth];

            initialNumber[depth] = node.number;
            dfs(initialNumber, depth + 1, count + node.count);
            initialNumber[depth] = prev;

            dfs(initialNumber, depth + 1, count);
        }
    }
}