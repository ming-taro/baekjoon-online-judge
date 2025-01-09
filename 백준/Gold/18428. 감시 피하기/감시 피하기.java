import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    int d;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Node(int row, int col, int d) {
        this.row = row;
        this.col = col;
        this.d = d;
    }
}

public class Main {
    private static int N;
    private static List<Node> students = new ArrayList<>();
    private static List<Node> list = new ArrayList<>();
    private static char[][] board;
    private static final char WALL = 'W';

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = reader.readLine().replaceAll(" ", "");
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'S') students.add(new Node(i, j));
                if (board[i][j] == 'X') list.add(new Node(i, j));
            }
        }

        if (dfs(0, 0)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }

    private static boolean dfs(int depth, int current) {
        if (depth == 3) {
            for (Node student: students) {
                if (!check(student)) return false;
            }
            return true;
        }

        for (int i = current; i < list.size(); i++) {
            board[list.get(i).row][list.get(i).col] = WALL;
            if (dfs(depth + 1, i + 1)) return true;
            board[list.get(i).row][list.get(i).col] = 'X';
        }
        return false;
    }

    private static boolean check(Node node) {
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        boolean[] visited = new boolean[4];
        int count = 0;

        for (int i = 1; i < N; i++) {
            for (int d = 0; d < 4; d++) {
                if (visited[d]) continue;
                int nextRow = node.row + dx[d] * i;
                int nextCol = node.col + dy[d] * i;
                if (!isValid(nextRow, nextCol)
                        || board[nextRow][nextCol] == 'W'
                        || board[nextRow][nextCol] == 'S') {
                    visited[d] = true;
                    count++;
                    if (count == 4) {
                        return true;
                    }
                    continue;
                }
                if (board[nextRow][nextCol] == 'T') {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}