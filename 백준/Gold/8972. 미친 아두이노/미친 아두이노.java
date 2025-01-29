import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int row;
    int col;
    public Node (int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    private static int R;
    private static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[][] count = new int[R][C];

        Node I = new Node(0, 0);
        List<Node> nodes = new ArrayList<>();
        Queue<Integer> crazy = new ArrayDeque<>();

        for (int r = 0; r < R; r++) {
            String input = reader.readLine();
            for (int c = 0; c < C; c++) {
                if (input.charAt(c) == 'I') {
                    I = new Node(r, c);
                } else if (input.charAt(c) == 'R') {
                    nodes.add(new Node(r, c));
                    crazy.add(nodes.size() - 1);
                    count[r][c]++;
                }
            }
        }
        String move = reader.readLine();

        int[] dx = { 1, 1, 1, 0, 0, 0, -1, -1, -1 };
        int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
        int X = 0; // 종수의 이동 횟수 누적
        boolean flag = false;

        for (int i = 0; i < move.length(); i++) {
            I.row += dx[move.charAt(i) - '0' - 1]; // 1. 종수 이동하기
            I.col += dy[move.charAt(i) - '0' - 1];
            X++;
            if (count[I.row][I.col] != 0) {        // 2. 종수 == 아두이노 -> 게임 종료
                flag = true;
                break;
            }

            for (int c: crazy) { // 3. 아두이노 이동하기
                Node current = nodes.get(c);

                int distance = 1000;
                int next = 0;
                for (int d = 0; d < 9; d++) { // 가장 짧은 거리가 되는 방향 구하기
                    int nextRow = current.row + dx[d];
                    int nextCol = current.col + dy[d];
                    if (!isValid(nextRow, nextCol)) continue;

                    int newDistance = Math.abs(I.row - nextRow) + Math.abs(I.col - nextCol);
                    if (newDistance < distance) {
                        distance = newDistance;
                        next = d;
                    }
                }

                count[current.row][current.col]--;
                current.row += dx[next];
                current.col += dy[next];
                count[current.row][current.col]++;
            }

            int active = crazy.size();
            Set<Node> remove = new HashSet<>();
            for (int j = 0; j < active; j++) {
                int index = crazy.poll();
                Node current = nodes.get(index);

                if (I.row == current.row && I.col == current.col) { // 4 - 1. 아두이노 == 종수
                    flag = true;
                    break;
                }

                if (count[current.row][current.col] > 1) {          // 5 - 1. 2개 이상 아두이노가 같은 칸 -> 삭제
                    remove.add(new Node(current.row, current.col));
                } else {
                    crazy.offer(index);
                }
            }
            if (flag) break;       // 4 - 2. 아두이노 == 종수 -> 게임 종료
            for (Node r: remove) { // 5 - 2. 아두이노 삭제 표시
                count[r.row][r.col] = 0;
            }
        }

        if (flag) {
            System.out.println("kraj " + X);
        } else {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (I.row == i && I.col == j) {
                        System.out.print("I");
                    } else if (count[i][j] == 1) {
                        System.out.print("R");
                    } else {
                        System.out.print(".");
                    }
                } System.out.println();
            }
        }
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < R && col >= 0 && col < C;
    }
}