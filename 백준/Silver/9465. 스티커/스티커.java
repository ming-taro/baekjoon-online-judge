import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(reader.readLine());
            int[][] sticker = new int[2][n];
            for (int i = 0; i < 2; i++) {
                sticker[i] = Arrays.stream(reader.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            int answer = Math.max(sticker[0][0], sticker[1][0]);
            
            for (int c = 1; c < n; c++) {
                for (int r = 0; r < 2; r++) {
                    int target = 0;
                    if (c > 1) target = Math.max(sticker[0][c - 2], sticker[1][c - 2]);
                    if (r == 0) target = Math.max(target, sticker[1][c - 1]);
                    if (r == 1) target = Math.max(target, sticker[0][c - 1]);
                    sticker[r][c] += target;
                    answer = Math.max(answer, sticker[r][c]);
                }
            }
            System.out.println(answer);
        }
    }
}