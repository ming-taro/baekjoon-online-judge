import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int start;
    int end;
    long value;

    public Node(int start, int end, long value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }

    @Override
    public int compareTo(Node node) {
        return (int) (this.value - node.value);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 0, 0));
        boolean[] visited = new boolean[N];
        long answer = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (visited[current.end]) continue;
            visited[current.end] = true;
            answer += current.value;

            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    queue.offer(new Node(current.end, i, board[current.end][i]));
                }
            }
        }
        System.out.println(answer);
    }
}