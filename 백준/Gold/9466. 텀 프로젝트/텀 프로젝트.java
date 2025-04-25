import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());     // tc개수
        StringBuilder result = new StringBuilder();
        for (int t =0; t < T; t++) {
            int n = Integer.parseInt(reader.readLine()); // 학생 수
            int[] next = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            int answer = n;

            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 1; i <= n; i++) {
                next[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if(visited[i]) continue;
                visited[i] = true;

                Deque<Integer> deque = new ArrayDeque<>();
                deque.offer(i);

                while (true) {
                    int last = deque.peekLast();// 마지막에 팀에 들어온 학생

                    if (visited[next[last]]) {                           // 1. 영입하려는 학생이 이미 팀에 속함
                        while (!deque.isEmpty() && deque.peekFirst() != next[last]) deque.poll();  // 팀의 첫번째 학생이 영입하려는 학생이 될때까지 poll
                        answer -= deque.size();
                        break;
                    }

                    visited[next[last]] = true;
                    deque.offer(next[last]);
                }
            }
            result.append(answer).append("\n");
        }

        System.out.println(result.toString());
    }
}