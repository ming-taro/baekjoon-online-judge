import java.io.*;
import java.util.*;

class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    private static char[][] board;
    private static boolean[][] visited;
    private static int R, C;
    private static int[] dx = {-1, 0, 1};  //파이프는 위에서부터 설치함
    private static int[] dy = {1, 1, 1};
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(reader.readLine());

        R = Integer.parseInt(input.nextToken());
        C = Integer.parseInt(input.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = reader.readLine().toCharArray();
        }

        for (int i = 0; i < R; i++) {
            searchPath(new Position(i, 0));
        }

        System.out.println(count);
    }

    private static boolean searchPath(Position current) {
        if (current.col == C - 1) {
            count++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            Position next = new Position(current.row + dx[i], current.col + dy[i]);

            if (isWithinRange(next)
                    && board[next.row][next.col] == '.'
                    && !visited[next.row][next.col]) {
                visited[next.row][next.col] = true;
                if (searchPath(next)) {  //해당 (row, 0)에서 시작한 파이프 라인 작업이 끝났으므로 더 이상 다른 길은 탐색X
                    return true;
                }  //재귀 종료 후에도 왔던 길을 false 처리 하지 않음 -> 해당 길로 갔다가 다시 돌아옴 == 어떤 row에서 시작해도 파이프를 설치할 수 없음
            }
        }

        return false;
    }

    private static boolean isWithinRange(Position position) {
        return position.row >= 0 && position.row < R && position.col >= 0 && position.col < C;
    }
}