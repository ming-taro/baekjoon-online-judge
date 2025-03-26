import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Node {
    int num;
    int score;
    public Node(int num, int score) {
        this.num = num;
        this.score = score;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Node>[] nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a < b) nodes[a].add(new Node(b, c));   // 동 -> 서 경로만 고려
        }

        int[][] dp = new int[N + 1][M + 1]; // N개의 여행지를 M번에 도착한 경우
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 여행은 무조건 1번에서 시작
        int count = 1;

        while (!queue.isEmpty() && count < M) {
            int size = queue.size();
            for (int loop = 0; loop < size; loop++) {
                int current = queue.poll();          // count 회차에서의 출발지 노드
                for (Node next : nodes[current]) {
                    if (dp[current][count] + next.score > dp[next.num][count + 1]) {
                        dp[next.num][count + 1] = dp[current][count] + next.score;
                        queue.offer(next.num);
                    }
                }
            }
            count++;
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) answer = Math.max(answer, dp[N][i]);
        System.out.println(answer);
    }
}