import java.io.*;
import java.util.*;

public class Main {
    private static int[][] board;
    private static int N;
    private static int r1, c1;
    private static int r2, c2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer input = new StringTokenizer(reader.readLine());

        r1 = Integer.parseInt(input.nextToken());
        c1 = Integer.parseInt(input.nextToken());
        r2 = Integer.parseInt(input.nextToken());
        c2 = Integer.parseInt(input.nextToken());

        N = Math.max(
                Math.max(Math.abs(r1), Math.abs(c1)),
                Math.max(Math.abs(r2), Math.abs(c2))
        ) * 2 + 1;

        r1 += N / 2;  //(0, 0) == (N/2, N/2)
        c1 += N / 2;
        r2 += N / 2;
        c2 += N / 2;

        board = new int[r2 - r1 + 1][c2 - c1 + 1];

        int row = N / 2;  //(0, 0)
        int col = N / 2;

        for (int i = 0; i <= N / 2; i++) {
            setNumbers(row, col, i * 2 + 1);  //1부터 채워줌
            row += 1;
            col += 1;
        }

        int width = board[0].length;
        int height = board.length;

        int numberSize = Integer.toString(
                Math.max(  //범위 사각형의 꼭짓점 값 중 가장 큰 값의 자릿수가 전체 자릿수가 됨
                        Math.max(board[0][0], board[0][width - 1]),
                        Math.max(board[height - 1][0], board[height - 1][width - 1])
                )).length();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                printNumber(numberSize, board[i][j]);
            }
            System.out.println();
        }
    }

    private static void printNumber(int size, int number) {
        size -= Integer.toString(number).length();

        for (int i = 0; i < size; i++) {
            System.out.print(" ");
        }
        System.out.print(number + " ");
    }

    private static void setNumbers(int row, int col, int line) {
        int[] dx = {0, -1, 0, 1};  //왼, 위, 오, 아
        int[] dy = {-1, 0, 1, 0};

        int number = line * line;  //해당 정사각형의 마지막 숫자 == 한 변의 길이^2
        int[] count = {line, line - 1, line - 1, line - 2};  //각 변에 들어갈 숫자의 개수(ex) 마지막 숫자가 9인 정사각형의 경우 아랫변 3개, 왼쪽변과 윗변 각각 2개, 오른쪽변 1개의 숫자가 들어감)

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < count[i]; j++) {
                if (row >= r1 && row <= r2 && col >= c1 && col <= c2) {
                    board[row - r1][col - c1] = number;  //N * N 보드판 중 (r1, c1) ~ (r2, c2)의 메모리만 사용하므로 실제 board에서의 인덱스는 각 인덱스 값에서 (r1, c1)를 뺀 값
                }
                number--;
                row += dx[i];
                col += dy[i];
            }
            if (i < 3) {     //아랫변, 왼쪽변, 윗변 진행 후에는 다음 시작 위치를 조정해 줌
                row = row - dx[i] + dx[i + 1];
                col = col - dy[i] + dy[i + 1];
            }
        }
    }
}