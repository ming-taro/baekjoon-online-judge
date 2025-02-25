import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static int D;
    private static int[][] board;
    private static List<int[]> enemyList = new ArrayList<>();
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) enemyList.add(new int[]{ i, j });
            }
        }

        dfs(0, new int[3], 0);
        System.out.println(answer);
    }

    private static void dfs(int current, int[] archer, int index) {
        if (index == 3) {
            answer = Math.max(answer, run(archer));
            return;
        }

        for (int i = current; i < M; i++) {
            archer[index] = i;
            dfs(i + 1, archer, index + 1);
        }
    }

    private static int run(int[] archer) {
        int life = 0;

        Set<Integer> enemySet = new HashSet<>(); // 생존한 적 idx
        int[][] enemy = new int[enemyList.size()][2];
        for (int i = 0; i < enemyList.size(); i++) {
            enemySet.add(i);
            enemy[i][0] = enemyList.get(i)[0];
            enemy[i][1] = enemyList.get(i)[1];
        }

        int[] distance = new int[3];   // 궁수와 가장 가까운 적 거리 + idx
        int[] index = new int[3];

        for (int i = 0; i < N; i++) {
            Arrays.fill(distance, 0);
            Arrays.fill(index, -1);

            for (int e: enemySet) {
                for (int j = 0; j < archer.length; j++) {
                     int d = calcDistance(enemy[e], archer[j]);
                     if (d > D) continue;
                     if (distance[j] == 0
                             || d < distance[j]
                             || d == distance[j] && enemy[e][1] < enemy[index[j]][1]) { // 가장 가까운 -> 같다면 왼쪽에 있는 적
                         distance[j] = d;
                         index[j] = e;
                     }
                }
            }

            for (int e: index) {                       // 궁수 공격
                if (e >= 0 && enemySet.contains(e)) {
                    enemySet.remove(e);
                    life++;
                }
            }

            Set<Integer> removeTarget = new HashSet<>();
            for (int e: enemySet) {                    // 적 이동 + 격자 밖으로 이동한 적 제외
                if (enemy[e][0] == N - 1) {
                    removeTarget.add(e);
                } else {
                    enemy[e][0]++;
                }
            }
            for (int e: removeTarget) {
                enemySet.remove(e);
            }
        }

        return life;
    }

    private static int calcDistance(int[] enemy, int archer) {
        return Math.abs(enemy[0] - N) + Math.abs(enemy[1] - archer);
    }
}
/*
N + 1 == 성
궁수 3명 배치
궁수 공격 : 거리가 D이하인 적 중 가장 가까운 적 -> 가장 왼쪽
-> 공격 끝나면 살아 있는 적은 한 칸 아래로 이동
 */