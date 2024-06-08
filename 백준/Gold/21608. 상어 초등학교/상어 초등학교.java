import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int row;
    int col;
    int favoriteCount;
    int blank;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Node(int row, int col, int favoriteCount, int blank) {
        this.row = row;
        this.col = col;
        this.favoriteCount = favoriteCount;
        this.blank = blank;
    }

    @Override
    public int compareTo(Node node) {
        if (this.favoriteCount != node.favoriteCount) {       // 주변 인접 칸에 좋아 하는 학생이 많은 순
            return node.favoriteCount - this.favoriteCount;
        }

        if (this.blank != node.blank) {      // 주변 인접 칸에 좋아 하는 학생 수가 같다면 빈칸이 많은 자리 순
            return node.blank - this.blank;
        }

        if (this.row != node.row) { // 빈칸 개수가 같다면 행이 작은 순
            return this.row - node.row;
        }
        return this.col - node.col; // 행이 같다면 열이 작은 순
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                ", favoriteCount=" + favoriteCount +
                ", blank=" + blank +
                '}';
    }
}

public class Main {
    private static int N;
    private static int[][] board;            // 전체 자리
    private static int[][] favoriteStudent;  // 좋아 하는 학생 번호
    private static Node[] seatNumber;        // 좌석 위치
    private static boolean[] visited;
    private static int[] dx = {-1, 0, 0, 1}; // 위, 왼, 오, 아
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        favoriteStudent = new int[N * N][4];
        seatNumber = new Node[N * N];
        board = new int[N][N];
        visited = new boolean[N * N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], -1);
        }

        for (int i = 0; i < N * N; i++) {
            StringTokenizer input = new StringTokenizer(reader.readLine());
            int student = Integer.parseInt(input.nextToken()) - 1; // 현재 자리에 배치할 학생 번호

            for (int j = 0; j < 4; j++) {
                int favorite = Integer.parseInt(input.nextToken()) - 1;
                favoriteStudent[student][j] = favorite;        // 좋아 하는 학생 표시
            }

            if (i == 0) {
                seatNumber[student] = new Node(1, 1); // 첫 번째 학생의 자리는 항상 (1, 1)
                board[1][1] = student;
                visited[student] = true;
                continue;
            }

            Node find = findSeat(student);
            board[find.row][find.col] = student;
            seatNumber[student] = new Node(find.row, find.col);
            visited[student] = true;
        }


        int result = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                result += calcFavorite(board[row][col]);
            }
        }

        System.out.println(result);
    }

    private static Node findSeat(int student) {
        Queue<Node> queue = new PriorityQueue<>();
        int[][] favoriteCount = new int[N][N];

        for (int favorite : favoriteStudent[student]) { // 좋아하는 학생 목록 순회
            if (!visited[favorite]) { // 아직 자리에 없는 학생이라면 패스
                continue;
            }
            for (int j = 0; j < 4; j++) {
                Node next = new Node(seatNumber[favorite].row + dx[j], seatNumber[favorite].col + dy[j]);
                if (isWithinRange(next) && board[next.row][next.col] == -1) { // 빈 자리 중 좋아 하는 학생과 인접한 자리
                    favoriteCount[next.row][next.col]++;
                }
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == -1) {
                    queue.add(new Node(row, col, favoriteCount[row][col], calcBlankSpace(row, col)));
                }
            }
        }

        return queue.poll();
    }

    private static int calcBlankSpace(int row, int col) {
        int blank = 0;

        for (int i = 0; i < 4; i++) { // 해당 칸의 인접한 빈칸 계산
            Node next = new Node(row + dx[i], col + dy[i]);
            if (isWithinRange(next) && board[next.row][next.col] == -1) {
                blank++;
            }
        }

        return blank;
    }

    private static int calcFavorite(int number) {
        int total = 0;
        Node current = seatNumber[number];

        for (int favorite : favoriteStudent[number]) {
            Node favoriteStudent = seatNumber[favorite];
            for (int i = 0; i < 4; i++) {
                Node around = new Node(current.row + dx[i], current.col + dy[i]);
                if (isWithinRange(around)
                        && favoriteStudent.row == around.row && favoriteStudent.col == around.col) {
                    total++;
                    break;
                }
            }
        }

        switch (total) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
            default:
                return 1000;
        }
    }

    private static boolean isWithinRange(Node node) {
        return node.row >= 0 && node.row < N && node.col >= 0 && node.col < N;
    }
}
/*
->N*N 크기의 교실
->N*N명의 학생(1~N^2)
->위오아왼 4면과 인접

[순서 정하기]
->좋아하는 학생이 가장 많이 인접한 칸을 자리로 정함
->위를 만족하는 칸이 여럿 -> 인접 칸 중 빈 칸이 가장 많은 칸
->위를 만족하는 칸이 여럿 -> 행 번호가 가장 작은 칸
-> 위를 만족하는 칸이 여럿 -> 열 번호가 가장 작은 칸
 */