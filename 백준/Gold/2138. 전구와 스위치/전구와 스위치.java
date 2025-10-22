import java.io.*;
import java.util.*;

class Main {
    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(reader.readLine());
        String start = reader.readLine();
        char[] target = reader.readLine().toCharArray();

        char[][] bulb = new char[2][N];
        bulb[0] = start.toCharArray();        // 전구 초기 상태는 2가지 -> start그대로, 0번을 켠 전구
        bulb[1] = Arrays.copyOf(bulb[0], N);
        on(bulb[1], 0);

        int[] count = { 0, 1 };
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < start.length(); i++) {
            for (int b = 0; b < 2; b++) {
                if (convert(bulb[b][i - 1]) == target[i - 1]) { // [0][i]전구를 켰을 때 [i-1]의 상태가 target과 같은지 검사
                    on(bulb[b], i);
                    count[b]++;
                }
                if (Arrays.equals(bulb[b], target)) {
                    answer = Math.min(answer, count[b]);
                }
            }
        }

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void on(char[] bulb, int index) { // 전구 켜기
        if (index - 1 >= 0) bulb[index - 1] = convert(bulb[index - 1]);
        bulb[index] = convert(bulb[index]);
        if (index + 1 < N) bulb[index + 1] = convert(bulb[index + 1]);
    }

    public static char convert(char c) {
        return c == '0' ? '1' : '0';
    }
}