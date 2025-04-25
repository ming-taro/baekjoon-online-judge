import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int d;
    public Node(int row, int col, int d) {
        this.row = row;
        this.col = col;
        this.d = d;
    }

    @Override
    public String toString() {
        return "위치 = (" + row + ", " + col + "), 방향 = " + d;
    }
}

class Main {
    private static int N;
    private static int M;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        M = Integer.parseInt(st.nextToken()); // 세로
        N = Integer.parseInt(st.nextToken()); // 가로

        board = new int[M][N];
        for (int i = 0; i < M; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        st = new StringTokenizer(reader.readLine());
        Node start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(reader.readLine());
        Node end = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        System.out.println(bfs(start, end));
    }

    private static int bfs(Node start, Node end) {
        int[] dx = { 0, 0, 1, -1 }; // 오, 왼, 아, 위
        int[] dy = { 1, -1, 0, 0 };

        int[][][] dp = new int[4][M][N];
        Queue<Node> queue = new PriorityQueue<>(((o1, o2) -> dp[o1.d][o1.row][o1.col] - dp[o2.d][o2.row][o2.col]));
        queue.offer(start);

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.row == end.row && current.col == end.col) {
                answer = Math.min(answer, dp[current.d][current.row][current.col] + calcRotateCount(current.d, end.d));
            }

            for (int i = 0; i < 4; i++) {
                int count = dp[current.d][current.row][current.col] + calcRotateCount(i, current.d) + 1; // 현재 좌표까지 누적된 명령 + 회전수 + 앞으로의 이동 명령

                for (int move = 1; move <= 3; move++) {
                    int nextRow = current.row + dx[i] * move;
                    int nextCol = current.col + dy[i] * move;
                    if (!isValid(nextRow, nextCol) || board[nextRow][nextCol] == 1) break; // 막혀있는 길을 더 이상 탐색X

                    if (dp[i][nextRow][nextCol] == 0 || count < dp[i][nextRow][nextCol]) {
                        dp[i][nextRow][nextCol] = count;
                        queue.offer(new Node(nextRow, nextCol, i));
                    }
                }
            }
        }

        return answer;
    }

    private static int calcRotateCount(int d1, int d2) {
        if (d1 == d2) return 0; // 같은 방향
        if (d1 == 0 && d2 == 1 || d1 == 1 && d2 == 0 || d1 == 2 && d2 == 3 || d1 == 3 && d2 == 2) return 2; // 반전  = 명령 + 2
        return 1;               // 왼 or 오른쪽 회전 = 명령 + 1
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < M && col >= 0 && col < N;
    }
}