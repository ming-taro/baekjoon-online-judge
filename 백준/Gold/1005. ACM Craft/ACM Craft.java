import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 개수
            int K = Integer.parseInt(st.nextToken()); // 건설 순서 규칙

            String[] input = reader.readLine().split(" ");
            int[] D = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(input[i - 1]);
            }

            ArrayList<Integer>[] nodes = new ArrayList[N + 1]; // 다음으로 건설하는 건물 번호 리스트
            for (int i = 1; i <= N; i++) {
                nodes[i] = new ArrayList<>();
            }

            int[] in = new int[N + 1]; // 이전에 건설이 완료되어야 하는 건물 개수

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(reader.readLine());
                int X = Integer.parseInt(st.nextToken()); // X 건설 후 Y
                int Y = Integer.parseInt(st.nextToken());
                nodes[X].add(Y);
                in[Y]++;
            }

            int W = Integer.parseInt(reader.readLine()); // 건물 번호

            Queue<Integer> queue = new ArrayDeque<>();
            int[] time = new int[N + 1];

            for (int num = 1; num <= N; num++) {
                if (in[num] == 0) {
                    queue.offer(num);
                    time[num] = D[num];
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (current == W) {
                    answer.append(time[current]).append("\n");
                    break;
                }
                for (int next: nodes[current]) {
                    in[next]--;
                    if (time[next] == 0) {
                        time[next] = time[current] + D[next];
                    } else {
                        time[next] = Math.max(time[next], time[current] + D[next]);
                    }
                    if (in[next] == 0) {
                        queue.offer(next);
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
/*
1번 건물 -> 10 소요
2번 건물 -> prev 1, 10 + 1 = 11 소요
3번 건물 -> prev 1, 10 + 100 = 110 소요
4번 건물 -> prev 2 < prev 2, 110 + 10 = 120 소요

in == 0 인 건물들 queue에 넣기
다음 건물들 in--하면서 현재 건물 소요 시간 + 다음 건물 소요시간 해서 더 큰 값 갱신하기
 */