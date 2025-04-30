import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int K = Integer.parseInt(st.nextToken());  // 테스트 케이스 번호
            int M = Integer.parseInt(st.nextToken());  // 노드 수
            int P = Integer.parseInt(st.nextToken());  // 간선 수

            int[] in = new int[M + 1];
            boolean[] isLake = new boolean[M + 1];
            List<Integer>[] nodes = new ArrayList[M + 1];
            for (int i = 1; i <= M; i++) {
                nodes[i] = new ArrayList<>();
            }
            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(reader.readLine());
                int A = Integer.parseInt(st.nextToken()); // 강 : A -> B
                int B = Integer.parseInt(st.nextToken());
                nodes[A].add(B);
                in[B]++;
                isLake[A] = true;
            }

            int sea = 0;
            for (int i = 1; i <= M; i++) {
                if (!isLake[i]) {
                    sea = i;
                    break;
                }
            }

            System.out.println(K + " " + run(M, in, nodes, sea));
        }
    }

    private static int run(int M, int[] in, List<Integer>[] nodes, int sea) {
        int[][] result = new int[M + 1][2];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= M; i++) {
            if (in[i] == 0) {     // 강의 근원
                queue.offer(i);
                result[i][0] = 1; // 들어 오는 강의 순서 중 가장 큰 값
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next: nodes[current]) {
                if (result[current][0] > result[next][0]) {
                    result[next][0] = result[current][0];
                    result[next][1] = 1;
                } else if (result[current][0] == result[next][0]) {
                    result[next][1]++;
                }
                in[next]--;  // 들어 오는 간선 -1

                if (in[next] == 0) {
                    if (result[next][1] >= 2) result[next][0]++; // 순서가 가장 큰 들어 오는 강이 2개 이상 -> 순서 + 1
                    queue.offer(next);
                }
            }
        }

        return result[sea][0];
    }
}
/*
네모 = 순서 / 동그라미 = 노드 번호
in X = 순서 1번 / out X = 바다
return 바다 노드의 순서
 */