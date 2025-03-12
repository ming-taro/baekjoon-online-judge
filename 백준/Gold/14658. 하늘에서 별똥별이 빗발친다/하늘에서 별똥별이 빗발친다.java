import java.io.*;
import java.util.*;

class Main {
    private static int[][] A;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] stars = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars[i][0] = x;
            stars[i][1] = y;
        }

        int answer = 0;


        for (int[] current: stars) {
            for (int[] target: stars) {
                int[] top = { Math.min(current[0], target[0]), Math.max(current[1], target[1])};
                int[] down = { top[0] + L, top[1] - L };
                answer = Math.max(answer, calcStar(stars, top, down));
            }
        }

        System.out.println(K - answer);
    }

    private static int calcStar(int[][] stars, int[] top, int[] down) {
        int count = 0;

        for (int[] star: stars) {
            if (star[0] >= top[0] && star[1] <= top[1]
                    && star[0] <= down[0] && star[1] >= down[1]) {
                count++;
            }
        }
        return count;
    }
}
/*
  0 1 2 3 4 5 6 7 8 9 0 1 2
0 . . . . . . . . . . . . *
9 . . . . . . . . . . . . .
8 . . . . . . . . . . . . .
7 . . . . * . . . . . . . .
6 . . . . . * . . * . . . .
5 . . . . . . . . . . . . .
4 . . * . . . . . . . . . .
3 . . . . . . . * . . . . .
2 . . . . . . . . . . . . .
1 . . . * . . . . . . . . .
0 . . . . . . . . . . . . .

(4, 7) - (8, 7)
   |       |
(4, 3) - (8, 3)
=> 4개의 별, 3개를 튕겨냄

좌상단 좌표 후보 -> 두 별의 교점
for 별1 in stars:
    for 별2 in stars:
        좌상 = (별1과 2중 작은 x, 큰 y)
        우하 = x + L, y - L
        def: 위 범위 들어 오는 별들 count
 */