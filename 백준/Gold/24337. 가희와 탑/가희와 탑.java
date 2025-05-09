import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a + b - 1 > N) {
            System.out.println(-1);
            return;
        }

        int max = Math.max(a, b);
        Deque<Integer> queue = new ArrayDeque<>();

        int maxH = Math.max(a, b);    // 가장 높은 건물
        for (int i = 1; i < a; i++) { // 가희
            queue.offer(i);
        }
        queue.offer(maxH);
        for (int i = b - 1; i >= 1; i--) { // 단비
            queue.offer(i);
        }

        StringBuilder result = new StringBuilder();
        result.append(queue.poll() + " ");
        for (int i = 0; i < N - queue.size() - 1; i++) {
            result.append(1 + " ");
        }
        while (!queue.isEmpty()) {
            result.append(queue.poll() + " ");
        }

        System.out.println(result.toString());
    }
}
/*
1) 건물 10개, 가희 3개, 단비 2개
가장 높은 건물 = Math.max(3, 2) = 3
가희 -> 가장 높은 건물 3을 제외한 1, 2 높이 건물 2개 보임
단비 -> 가장 높은 건물 3을 제외한 1 높이 건물 1개 보임
남은 건물 6개는 가장 낮은 건물 1
결과 = 가희 [1 2 3 1] 단비 = [1 1 1 1 1 1 1 2 3 1]
*/