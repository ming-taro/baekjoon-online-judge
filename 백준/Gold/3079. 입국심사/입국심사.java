import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());  // 입국 심사대
        int M = Integer.parseInt(st.nextToken());  // 총원

        long[] time = new long[N];
        for (int i = 0; i < N; i++) {
            time[i] = Long.parseLong(reader.readLine()); // k번 심사대 심사 시간
        }
        Arrays.sort(time);

        System.out.println(search(time, M));
    }

    private static long search(long[] time, int target) {
        long maxTime = 0L;
        for (long t: time) maxTime = Math.max(maxTime, t);

        long left = 0;
        long right = target * maxTime + 1;  // 최악 = 1개 심사대 * M명

        while (left + 1 < right) {
            long mid = (left + right) / 2;  // 시간
            long total = 0;
            for (long t: time) {
                total += mid / t;
                if (total >= target) break;
            }
            if (total < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left + 1;
    }
}
/*
7개의 심사대, 10명의 친구들
[2 3 3 4 6 8 9]
1번 심사대 가능 시간 : 0, 2, 4, 6, 8, 10..
2번 심사대 가능 시간 : 0, 3, 6, 9, 12, 15..
3번 심사대 가능 시간 : 0, 3, 6, 9, 12, 15..
4번 심사대 가능 시간 : 0, 4, 8, 12, 16, 20..
5번 심사대 가능 시간 : 0, 6, 12, 18, 24, 30..
6번 심사대 가능 시간 : 0, 8, 16, 24, 32, 40..
7번 심사대 가능 시간 : 0, 9, 18, 27, 36, 45..

[심사대]
1번 심사대 -> ~8 (4명)
2번 심사대 -> ~6 (2명)
3번 심사대 -> ~6 (2명)
4번 심사대 -> ~4 (1명)
5번 심사대 -> ~6 (1명)
Math.max() => 8초

[초 기준]
1초 = 0명
2초 = 1명(1번)
3초 = 1(1번) + 1(2번) + 1(3번) = 3명
4초 = 2(1번) + 1(2번) + 1(3번) + 1(4번) = 5명
5초 = 2(1번) + 1(2번) + 1(3번) + 1(4번) = 5명
6초 = 3(1번) + 2(2번) + 2(3번) + 1(4번) + 1(5번) = 9명
7초 = 3(1번) + 2(2번) + 2(3번) + 1(4번) + 1(5번) = 9명
8초 = 4(1번) + 2(2번) + 2(3번) + 2(4번) + 1(5번) = 11명
->소요 시간 = mid
->mid 시간 동안 심사대에서 수용 가능한 인원을 기준으로 left, right 설정
->(total >= target)인 경우 중 가장 작은 mid를 구해야 하므로 (left < 최소시간)이 될 때까지 탐색 하면 (left + 1)이 가장 작은 시간이 됨
 */