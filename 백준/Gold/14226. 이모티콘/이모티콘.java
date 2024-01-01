import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(reader.readLine());

        bfs(S);
    }

    public static void bfs(int S) {
        Queue<Sticker> q = new LinkedList<>();
        boolean[][] visited = new boolean[1001][1001];  //[screen][board]

        q.offer(new Sticker(2, 1, 2));  //화면 -> 처음 1개의 스티커

        visited[1][0] = true;
        visited[1][1] = true;
        visited[2][1] = true;

        while (!q.isEmpty()) {
            Sticker sticker = q.poll();

            if (sticker.screen == S) {   //너비우선탐색이므로 처음 S개가 되는 순간이 최소 시간
                System.out.println(sticker.time);
                return;
            }

            //복사
            int row = sticker.screen;
            int col = sticker.screen;
            if (isWithinRange(row, col) && !visited[row][col]
                    && sticker.screen != sticker.board){  //화면과 보드의 스티커 개수가 같다면 복사할 필요X
                q.add(new Sticker(row, col, sticker.time + 1));
                visited[row][col] = true;
            }

            //붙여넣기
            row = sticker.screen + sticker.board;
            col = sticker.board;
            if (isWithinRange(row, col) && !visited[row][col]) {
                q.add(new Sticker(row, col, sticker.time + 1));
                visited[row][col] = true;
            }

            //삭제
            row = sticker.screen - 1;
            col = sticker.board;
            if (isWithinRange(row, col) && !visited[row][col]
                && sticker.screen > 0) {
                q.add(new Sticker(row, col, sticker.time + 1));
                visited[row][col] = true;
            }
        }
    }

    public static boolean isWithinRange(int row, int col) {
        return row >= 0 && row <= 1000 && col >= 0 && col <= 1000;
    }

    static class Sticker {
        int screen;
        int board;
        int time;

        public Sticker(int screen, int board, int time) {
            this.screen = screen;
            this.board = board;
            this.time = time;
        }
    }
}