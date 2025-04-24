import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수(1~N번)
        int M = Integer.parseInt(st.nextToken()); // 학생 각각 최대 M개 블록
        int H = Integer.parseInt(st.nextToken()); // 만들고자 하는 블록 높이

        int[][] dp = new int[N][H + 1];

        for (int i = 0; i < N; i++) {
            List<Integer> blocks = new ArrayList<>();
            st = new StringTokenizer(reader.readLine());
            while (st.hasMoreTokens()) {
                int h = Integer.parseInt(st.nextToken());
                dp[i][h]++;          // 현재 학생이 가지고 있는 블록++
                blocks.add(h);
            }

            if (i == 0) continue;    // 1번째 학생은 자신의 블록만 사용할 수 있으므로 나머지 연산 패스

            for (int j = 1; j <= H; j++) {
                dp[i][j] += dp[i - 1][j];
            }

            for (int block: blocks) {
                for (int j = 1; j <= H; j++) { // i번째 학생이 블록을 사용해 높이j를 만드는 경우의 수
                    /*
                        예를 들어 block=2, j=3인 경우 높이3을 만들기 위해서
                        먼저 만들려는 높이j가 블록보다 커야 현재 블록과 다른 블록을 조합해 사용할 수 있기 때문에 검사해야한다

                        (현재 블록 + 높이가1인 경우의 수 = 높이가 3인 경우)가 되고 결국 이 경우의 수는 이전까지 구한 높이가 1인 경우의 수와 같다
                        따라서 dp[i][3] += dp[i - 1][1]이 된다

                        때문에 점화식은
                        dp[현재 학생번호][만들려는 높이] += dp[이전까지의 모든 경우의 수가 누적된 학생번호][block을 사용하여 높이 j를 만들기 위한 block-j에서의 경우의수]
                        dp[i][j] += dp[i - 1][j - block]
                     */
                    if (j > block) {
                        dp[i][j] += dp[i - 1][j - block];
                        dp[i][j] %= 10007;
                    }
                }
            }
        }

        System.out.println(dp[N - 1][H] % 10007);
    }
}