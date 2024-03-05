import java.io.*;
import java.util.*;

public class Main {
    private static int[] dice = new int[7];
    private static int N, M;
    private static int x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(input.nextToken());   //지도 N * M
        M = Integer.parseInt(input.nextToken());
        x = Integer.parseInt(input.nextToken());   //주사위 위치
        y = Integer.parseInt(input.nextToken());
        int K = Integer.parseInt(input.nextToken());   //명령 개수

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] commands = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();


        for (int command: commands) {
            int prevX = x;
            int prevY = y;

            if (command == 1) {  //1: 오, 2: 왼, 3: 위, 4: 아
                moveRight();
            } else if (command == 2) {
                moveLeft();
            } else if (command == 3) {
                moveUp();
            } else {
                moveDown();
            }

            if (prevX == x && prevY == y) {  //명령 수행 시 좌표가 칸을 벗어 나는 경우 -> 수행X
                continue;
            }

            if (board[x][y] == 0) {    //이동한 칸이 0인 경우 주사위 6번의 값 복사
                board[x][y] = dice[6];
            } else {
                dice[6] = board[x][y]; //아닌 경우 보드판 숫자를 6번에 복사 & 해당 칸 = 0
                board[x][y] = 0;
            }
            System.out.println(dice[1]);
        }
    }

    private static void moveUp() {
        if (x - 1 < 0) {
            return;
        }
        x--;

        //위로 굴림 : 6 -> 5, 5 -> 1, 1 -> 2, 2 -> 6
        int six = dice[6];
        swapDice(2, 6);
        swapDice(1, 2);
        swapDice(5, 1);
        dice[5] = six;
    }

    private static void moveDown() {
        if (x + 1 >= N) {
            return;
        }
        x++;

        //아래로 굴림 : 6 -> 2, 5 -> 6, 1 -> 5, 2 -> 1
        int two = dice[2];
        swapDice(6, 2);
        swapDice(5, 6);
        swapDice(1, 5);
        dice[1] = two;
    }

    private static void moveRight() {
        if (y + 1 >= M) {
            return;
        }
        y++;

        //오른쪽으로 굴림 : 1 -> 3, 4 -> 1, 6 -> 4, 3 -> 6
        int three = dice[3];
        swapDice(1, 3);
        swapDice(4, 1);
        swapDice(6, 4);
        dice[6] = three;
    }

    private static void moveLeft() {
        if (y - 1 < 0) {
            return;
        }
        y--;

        //왼쪽으로 굴림 :4 -> 6, 1 -> 4, 3 -> 1, 6 -> 3
        int six = dice[6];
        swapDice(4, 6);
        swapDice(1, 4);
        swapDice(3, 1);
        dice[3] = six;
    }

    private static void swapDice(int prev, int next) {
        dice[next] = dice[prev];
    }
}
/*
  2
4 1 3
  5
  6
왼쪽으로 구름 :4 -> 6, 1 -> 4, 3 -> 1, 6 -> 3
오른쪽으로 구름 : 1 -> 3, 4 -> 1, 6 -> 4,  3 -> 6
위로 구름 : 6 -> 5, 5 -> 1, 1 -> 2, 2 -> 6
아래로 구름 : 6 -> 2, 5 -> 6, 1 -> 5, 2 -> 1
 */