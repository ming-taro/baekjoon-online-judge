import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Tree {
    int row;
    int col;
    int age;
    public Tree (int row, int col, int age) {
        this.row = row;
        this.col = col;
        this.age = age;
    }
}

public class Main {
    private static List<Tree> trees = new ArrayList<>();
    private static int[][] A;
    private static int[][] board;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = 5; // 초기 양분 = 5
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken()); // 나이
            trees.add(new Tree(x - 1, y - 1, z));
        }

        for (int i = 0; i < K; i++) {
            spendSpringAndSummer();
            spendFall();
            spendWinter();
        }

        System.out.println(trees.size());
    }

    private static void spendSpringAndSummer() {
        trees.sort((o1, o2) -> o1.age - o2.age);

        ArrayList<Tree> newTrees = new ArrayList<>();
        ArrayList<int[]> dead = new ArrayList<>();

        for (Tree tree: trees) {
            if (board[tree.row][tree.col] >= tree.age) {    // 봄 -> 양분 섭취
                board[tree.row][tree.col] -= tree.age;
                tree.age++;
                newTrees.add(tree);
            } else {
                dead.add(new int[]{ tree.row, tree.col, tree.age / 2 }); // 여름 -> 양분 생성
            }
        }

        trees = newTrees;
        for (int[] info: dead) {
            board[info[0]][info[1]] += info[2];
        }
    }

    private static void spendFall() {
        int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
        int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

        int len = trees.size();

        for (int i = 0; i < len; i++) {
            if (trees.get(i).age % 5 != 0) continue;

            for (int j = 0; j < 8; j++) {
                int nextRow = trees.get(i).row + dx[j];
                int nextCol = trees.get(i).col + dy[j];
                if (isValid(nextRow, nextCol)) {
                    trees.add(new Tree(nextRow, nextCol, 1)); // 인접 8칸에 나이 1살인 나무 생성
                }
            }
        }
    }

    private static void spendWinter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] += A[i][j];
            }
        }
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}