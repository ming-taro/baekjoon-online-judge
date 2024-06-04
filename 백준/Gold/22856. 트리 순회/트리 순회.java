import java.io.*;
import java.util.*;

class Node {
    int left;
    int right;

    public Node (int left, int right) {
        this.left = left;
        this.right = right;
    }
}

public class Main {
    private static Node[] nodes;
    private static int moveCount;
    private static boolean[] visited;
    private static int depth = 0;
    private static int N;
    private static int lastNode = 0;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        nodes = new Node[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(input.nextToken()); // 현재 노드
            int b = Integer.parseInt(input.nextToken()); // 왼쪽 자식 노드
            int c = Integer.parseInt(input.nextToken()); // 오른쪽 자식 노드

            nodes[a] = new Node(b, c);
        }

        findLastNode(1); // 중위 순회시 마지막으로 방문하는 노드의 정점을 구한다

        depth = 0;
        Arrays.fill(visited, false);
        dfs(1);
        System.out.println(result);
    }

    private static void findLastNode (int current) {
        if (nodes[current].left != -1) {
            findLastNode(nodes[current].left); // 왼쪽 자식 먼저 탐색
        }
        visited[current] = true; // 왼쪽 - 루트 - 오른쪽 순서로 탐색
        depth++;
        if (depth == N) {
            lastNode = current;
            return;
        }
        if (nodes[current].right != -1) {
            findLastNode(nodes[current].right); // 오쪽 자식을 가장 나중에 탐색
        }
    }

    private static void dfs (int current) {
        if (!visited[current]) {
            visited[current] = true;
            depth++;
        }
        if (current == lastNode && depth == N) { // 현재노드가 이미 방문한 노드이면서 중위순회의 마지막 노드인 경우
            result = moveCount;
            return;
        }


        if (nodes[current].left != -1) {
            moveCount++;
            dfs(nodes[current].left);
            moveCount++;

            if (current == lastNode && depth == N) { // 현재노드가 이미 방문한 노드이면서 중위순회의 마지막 노드인 경우
                result = moveCount;
                return;
            }
        }
        if (nodes[current].right != -1) {
            moveCount++;
            dfs(nodes[current].right);
            moveCount++;

            if (current == lastNode && depth == N) { // 현재노드가 이미 방문한 노드이면서 중위순회의 마지막 노드인 경우
                result = moveCount;
                return;
            }
        }
    }
}