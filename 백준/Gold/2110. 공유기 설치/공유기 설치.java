import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] distance = new int[N];

        for (int i = 0; i < N; i++) {
            distance[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(distance);

        System.out.println(search(distance, C));
    }

    private static int search(int[] distance, int target) {
        int N = distance.length;
        int left = 0;
        int right = distance[N - 1] - distance[0] + 1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;    // 두 공유기 사이의 최소 거리
            int prev = 0;                    // 첫 번째 집에 무조건 공유기
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (distance[i] - distance[prev] >= mid) {
                    prev = i;
                    count++;
                }
            }
            if (count >= target) { // 공유기를 더 많이 설치할 수 있다 -> 거리를 더 넓혀도 가능성 있음
                left = mid;
            } else {               // 공유기가 부족하다 -> 거리를 더 좁혀서 설치해야함
                right = mid;
            }
        }

        return left;
    }
}
/*
o    o    o
o    o      o
1-2--4----8-9

1 <= d <= 8
 */