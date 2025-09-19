import java.io.*;
import java.util.*;

class Main {
    private static List<int[]> spaces = new ArrayList<>();
    private static int N = 9;
    private static int[][] board;
    private static boolean isValid = false;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = input.charAt(j) - '0';
                if (board[i][j] == 0) spaces.add(new int[]{ i, j });
            }
        }

        dfs(0);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static void dfs(int index) {
        if (index == spaces.size()) {
            isValid = true;
            return;
        }

        int[] current = spaces.get(index);
        Set<Integer> numbers = createNumbers(current);
        if (numbers.isEmpty()) return;

        for (int number: numbers) {
            board[current[0]][current[1]] = number;
            dfs(index + 1);
            if (isValid) return;
            board[current[0]][current[1]] = 0;
        }
    }

    private static Set<Integer> createNumbers(int[] current) {
        Set<Integer> numbers = new HashSet<>();
        for (int number = 1; number <= N; number++) {
            numbers.add(number);
        }

        for (int i = 0; i < 9; i++) {
            if (board[current[0]][i] != 0) { // 가로
                numbers.remove(board[current[0]][i]);
            }
            if (board[i][current[1]] != 0) { // 세로
                numbers.remove(board[i][current[1]]);
            }
        }

        int row = 6;
        int col = 6;

        if (current[0] <= 2) row = 0;
        else if (current[0] <= 5) row = 3;

        if (current[1] <= 2) col = 0;
        else if (current[1] <= 5) col = 3;

        for (int i = 0; i < 3; i++) { // 사각형
            for (int j = 0; j < 3; j++) {
                if (board[row + i][col + j] != 0) {
                    numbers.remove(board[row + i][col + j]);
                }
            }
        }

        return numbers;
    }
}