import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] eggs;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());
        eggs = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(answer);
    }

    private static void dfs(int current) {
        if (current == N) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i][0] <= 0) count++;
            }
            answer = Math.max(answer, count);
            return;
        }

        int empty = 0;
        if (eggs[current][0] > 0) { // 현재 계란으로 계란치기 가능한 경우
            for (int i = 0; i < N; i++) {
                if (i == current) continue;
                if (eggs[i][0] <= 0) {
                    empty++;
                    continue;
                }

                eggs[i][0] -= eggs[current][1];
                eggs[current][0] -= eggs[i][1];
                dfs(current + 1);
                eggs[i][0] += eggs[current][1];
                eggs[current][0] += eggs[i][1];
            }
        }

        if (eggs[current][0] <= 0 || empty == N - 1) {  // 불가하거나 다른 계란이 다 깨져있는 경우 -> 다음으로 넘어감
            dfs(current + 1);
        } // 칠 수 있는 계란이 1개라도 남아있는데 안 치는 경우는 더 이상 탐색X
    }
}