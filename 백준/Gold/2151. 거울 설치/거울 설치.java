import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int d;
    int mirror;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Node(int row, int col, int d, int mirror) {
        this.row = row;
        this.col = col;
        this.d = d;
        this.mirror = mirror;
    }
}

class Main {
    private static char[][] board;
    private static List<Node> door = new ArrayList<>();
    private static List<Node> mirror = new ArrayList<>();
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, 1, 0, -1 };
    private static int N;
    private static int answer = Integer.MAX_VALUE;
    private static int[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        visited = new int[4][N][N];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == '#') door.add(new Node(i, j));
                if (board[i][j] == '!') mirror.add(new Node(i, j));
            }
        }

        for (int d = 0; d < 4; d++) {
            dfs(new Node(door.get(0).row, door.get(0).col, d, 0));
        }
        System.out.println(answer);

    }

    private static void dfs(Node current) {
        int row = current.row;
        int col = current.col;

        if (visited[current.d][row][col] > -1 && current.mirror >= visited[current.d][row][col]) return;
        visited[current.d][row][col] = current.mirror;

        while (isValid(row + dx[current.d], col + dy[current.d])) { // 직진 도중 만나는 요소에 따라 경로 처리하기
            row += dx[current.d];
            col += dy[current.d];
            if (visited[current.d][row][col] > -1 && current.mirror >= visited[current.d][row][col]) continue;
            visited[current.d][row][col] = current.mirror;

            if (row == door.get(1).row && col == door.get(1).col) {  // 1. 출구 -> 탐색 종료 + 거울 개수 갱신
                answer = Math.min(answer, current.mirror);
                return;
            }

            if (board[row][col] == '!') {  // 2. 거울 -> 반사되는 2 방향으로 새로운 탐색 진행
                int left = current.d == 0 ? 3 : current.d - 1;
                int right = current.d == 3 ? 0 : current.d + 1;
                dfs(new Node(row, col, left, current.mirror + 1));
                dfs(new Node(row, col, right, current.mirror + 1));
            }
        }
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N && board[row][col] != '*';
    }
}