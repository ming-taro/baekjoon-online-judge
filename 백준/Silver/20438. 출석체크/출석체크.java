import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());  // 학생 수
        int K = Integer.parseInt(st.nextToken());  // 졸고 있는 학생
        int Q = Integer.parseInt(st.nextToken());  // 출석 코드 보낼 학생
        int M = Integer.parseInt(st.nextToken());  // 구간

        Set<Integer> sleep = new HashSet<>();
        st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            sleep.add(Integer.parseInt(st.nextToken()));
        }

        boolean[] visited = new boolean[N + 3];
        st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            int student = Integer.parseInt(st.nextToken());
            if (sleep.contains(student)) continue;
            for (int next = student; next <= N + 2; next += student) {
                if (!sleep.contains(next)) visited[next] = true;
            }
        }

        int[] total = new int[N + 3];
        for (int i = 3; i <= N + 2; i++) {
            total[i] = total[i - 1] + (!visited[i] ? 1 : 0);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // 출석 하지 않은 학생을 누적 합으로 계산한 경우
            System.out.println(total[E] - total[S - 1]);

            // 출석한 학생을 누적 합으로 계산한 경우 -> 총 학생수(E - S + 1) - 구간합(total[E] - total[S - 1])
//            System.out.println((E - S + 1) - (total[E] - total[S - 1]));
        }
    }
}