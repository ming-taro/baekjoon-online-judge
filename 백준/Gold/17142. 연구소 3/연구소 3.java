import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int count;

    public Node (int row, int col, int count) {
        this.row = row;
        this.col = col;
        this.count = count;
    }
}


public class Main {
    private static int N, M;
    private static int[][] board;
    private static List<Node> virus = new ArrayList<>();
    private static List<int[]> virusComb = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(input.nextToken());
        M = Integer.parseInt(input.nextToken());
        board = new int[N][N];

        int emptySpace = 0;

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 2) {
                    virus.add(new Node(i, j, 0));
                }
                if (board[i][j] == 0) {
                    emptySpace++;
                }
            }
        }

        dfs(0, 0, new int[M]);
        int result = -1;

        for (int[] activeVirus: virusComb) {
            int time = calcTime(activeVirus, emptySpace);
            if (result == -1) {
                result = time;
            } else if (time != -1) {
                result = Math.min(result, time);
            }
        }

        System.out.println(result);
    }

    private static int calcTime(int[] activeVirus, int emptySpace) {
        Deque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        for (int v: activeVirus) {
            queue.offer(virus.get(v));
            visited[virus.get(v).row][virus.get(v).col] = true;
        }

        int[] dx = { -1, 0, 1, 0 }; // 위, 오, 아, 왼
        int[] dy = { 0, 1, 0, -1 };

        int time = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            time = Math.max(time, current.count);
            if (emptySpace == 0) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                Node next = new Node(current.row + dx[i], current.col + dy[i], current.count + 1);
                if (isValid(next)
                        && !visited[next.row][next.col]
                        && board[next.row][next.col] != 1) {
                    visited[next.row][next.col] = true;
                    queue.offer(next);
                    if (board[next.row][next.col] == 0){
                        emptySpace--;
                    }
                }
            }
        }

        if (emptySpace != 0) {
            return -1;
        }

        while (!queue.isEmpty()) {
            time = Math.max(time, queue.poll().count);
        }

        return time;
    }

    private static boolean isValid(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < N;
    }

    private static void dfs(int depth, int current, int[] nodes) {
        if (depth == M) {
            virusComb.add(Arrays.copyOfRange(nodes, 0, M));
            return;
        }

        for (int i = current; i < virus.size(); i++) {
            nodes[depth] = i;
            dfs(depth + 1, i + 1, nodes);
        }
    }
}