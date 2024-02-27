import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;
    int direction;

    public Node(int row, int col, int direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", direction=" + direction +
                '}';
    }
}

public class Main {
    private static int N, M;
    private static int[][] board;
    private static final int[] dx = {-1, 0, 1, 0}; //위, 오, 아, 왼
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        st = new StringTokenizer(reader.readLine());
        Node robot = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        System.out.println(clean(robot));
    }

    private static int clean(Node robot) {
        int count = 0;

        while (true) {
            if (board[robot.row][robot.col] == 0) {
                board[robot.row][robot.col] = 2;  //1. 청소 표시 0 -> 2
                count++;
            }

            boolean isCleaned = true;

            for (int i = 0; i < 4; i++) { //2. 주변 칸 중 청소해야하는 칸이 있는지 검사
                int nextRow = robot.row + dx[i];
                int nextCol = robot.col + dy[i];
                if (isWithinRange(nextRow, nextCol)
                        && board[nextRow][nextCol] == 0) {
                    isCleaned = false;
                    break;
                }
            }

            if (isCleaned) {
                moveBack(robot);                             //2-1. 주변 4칸이 모두 청소 되어 있음 -> 후진
                if (!isWithinRange(robot.row, robot.col)     //2-2. 후진 불가시 작동 중지
                        || board[robot.row][robot.col] == 1) {
                    break;
                }
            } else {   //3. 청소 해야 할 칸이 존재함
                robot.direction = robot.direction == 0 ? 3 : robot.direction - 1;  //3-1. -90도 회전
                moveNext(robot);  //3-2. 앞칸이 청소 해야 하는 칸인 경우 전진
            }
        }

        return count;
    }

    private static void moveNext(Node node) {
        int nextRow = node.row + dx[node.direction];
        int nextCol = node.col + dy[node.direction];

        if (isWithinRange(nextRow, nextCol) && board[nextRow][nextCol] == 0) {
            node.row = nextRow;
            node.col = nextCol;
        }
    }

    private static void moveBack(Node node) {
        node.row -= dx[node.direction];
        node.col -= dy[node.direction];
    }

    private static boolean isWithinRange(int row, int col) {
        return row >= 0 && row < N && col >= 0 && col < M;
    }
}
/*
->N * M 크기의 방
->현재 칸을 청소 -> 주변 4칸이 모두 청소되어 있으면 방향 유지 + 후진(<- 후진 불가시 작동 중지)
              -> 청소되지 않은 칸이 있는 경우 -90도 회전 + 다음 칸을 청소해야 하는 경우 전진
*/