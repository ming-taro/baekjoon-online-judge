import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int cost;
    int number;
    public Node (int cost, int number) {
        this.cost = cost;
        this.number = number;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            nodes.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int answer = Integer.MAX_VALUE;
        int[] dp = new int[C];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < C; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;

            for (Node node: nodes) {
                int customer = i + node.number;
                int newCost = dp[i] + node.cost;
                if (customer >= C) {
                    answer = Math.min(answer, newCost);
                    continue;
                }
                dp[customer] = Math.min(dp[customer], newCost);
            }
        }

        System.out.println(answer);
    }
}