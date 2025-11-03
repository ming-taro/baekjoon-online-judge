import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int M;
    private static int L;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken()); // 자르는 횟수
        M = Integer.parseInt(st.nextToken()); // 자를 수 있는 지점 개수
        L = Integer.parseInt(st.nextToken()); // 롤 케이크 길이

        int[] part = new int[M];
        for (int m = 0; m < M; m++) {
            part[m] = Integer.parseInt(reader.readLine());
        }

        StringBuilder answer = new StringBuilder();
        for (int n = 0; n < N; n++) {
            int count = Integer.parseInt(reader.readLine());
            answer.append(cutCake(part, count)).append("\n");
        }

        System.out.println(answer.toString());
    }

    private static int cutCake(int[] part, int target) {
        int left = 0;
        int right = L;   // 자르려는 케이크의 길이 범위
        int answer = 0;
        while (left + 1< right) {
            int mid = (left + right) / 2;      // 가장 작은 케이크의 크기
            int prev = 0;                      // 길이 0부터 시작
            int count = 0;
            for (int i = 0; i < M; i++) {
                if (part[i] - prev >= mid) {
                    count++;
                    prev = part[i];
                }
            }

            boolean isValid = true;
            if (count == target && L - prev < mid) isValid = false; // 카운트를 다 썼는데 마지막 조각이 더 작은 경우 -> 최소길이 충족X

            if (isValid && count >= target) {   // 카운트 충족 -> 케이크 크기 더 늘리기
                left = mid;
                answer = Math.max(answer, mid);
            } else {         // 카운트 부족 -> 케이크 크기 더 줄이기
                right = mid;
            }
        }

        return answer;
    }
}