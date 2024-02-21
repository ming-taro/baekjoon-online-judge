import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        int H = Integer.parseInt(stringTokenizer.nextToken());  //H*W 격자
        int W = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(reader.readLine());
        int R = Integer.parseInt(stringTokenizer.nextToken());  //아리스의 좌표 = (R, C)
        int C = Integer.parseInt(stringTokenizer.nextToken());
        int D = Integer.parseInt(stringTokenizer.nextToken());  //90*D 방향

        boolean[][] board = new boolean[H][W];
        int[][] ruleA = new int[H][W];
        int[][] ruleB = new int[H][W];

        for (int i = 0; i < H; i++) { //규칙표A
            ruleA[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        for (int i = 0; i < H; i++) { //규칙표B
            ruleB[i] = Arrays.stream(reader.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int count = 1;
        int lastCount = 0;

        int[] dx = {-1 , 0, 1, 0};  //위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};

        boolean isCleaned = true;
        int[] lastPoint = {R, C};
        boolean[][][] visited = new boolean[H][W][4];

        while (R >= 0 && R < H && C >= 0 && C < W) {
            if (!board[R][C]) {  //청소를 안한 구역 -> Rule A에 따라 방향을 바꿈
                board[R][C] = true;
                D = (D + ruleA[R][C]) % 4;

                lastCount = count;

                if (!isCleaned) {
                    for (int i = 0; i < H; i++) {
                        for (int j = 0; j < W; j++) {
                            Arrays.fill(visited[i][j], false);
                        }
                    }
                    isCleaned = true;
                }
            } else {  //Rule B에 따라 방향을 바꿈
                D = (D + ruleB[R][C]) % 4;
                isCleaned = false;

                if (visited[R][C][D]) {
                    break;
                } else {
                    visited[R][C][D] = true;
                }
            }

            R += dx[D];
            C += dy[D];
            count++;
        }

        System.out.println(lastCount);
    }
}
/*
현재칸에 먼지O -> 먼지 제거
-방금 먼지제거함 -> A
-안함 -> B
->해당 규칙표의 현재칸에 쓰인 숫자만큼 시계방향 회전 + 한칸 전진
->영역을 벗어남 or 위를 반복해도 더이상 먼지 제거X -> 중지

return 이동 횟수
 */