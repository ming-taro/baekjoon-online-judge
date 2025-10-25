import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        boolean[] valid = new boolean[N + 1];

        for (int num = 2; num <= Math.sqrt(N); num++) {
            if (valid[num]) continue;
            for (int i = num * 2; i <= N; i+=num) {
                valid[i] = true;
            }
        }

        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (!valid[i]) prime.add(i);
        }
        prime.sort((o1, o2) -> o1- o2);

        if (prime.isEmpty()) {     // 1은 소수 X
            System.out.println(0);
            return;
        }

        int start = 0;
        int end = 0;
        int total = prime.get(0);
        int answer = 0;

//        Deque<Integer> queue = new ArrayDeque<>(); // 연속된 소수 조합
//        queue.add(prime.get(0));

        while (start <= end) {
            if (total == N) {
//                System.out.println(queue);
                answer++;     // 경우의 수 카운트
            }

            if (total >= N) { // N초과 -> start값 1만큼 증가
                total -= prime.get(start++);
//                queue.pollFirst();
                continue;
            }

            if (end == prime.size() - 1) break; // 더 이상의 소수 조합 없음

            total += prime.get(++end); // N보다 작음 -> end증가시켜서 합 키우기
//            queue.addLast(prime.get(end));
        }

        System.out.println(answer);
    }
}
/*
============ 에라토스테네스의 체 ============
n = 16, 약수 = [1, 2, 4, 8, 16]
n(16) = 1 * 16   --> min(1, 16) = 1
      = 2 * 8    --> min(2, 8) = 1
      = 4 * 4    --> min(4, 4) = 4
=> min(a, b)의 최댓값은 루트 n = 4, 루트 n이하인 자연수에 대한 배수를 살펴보면 됨
 */