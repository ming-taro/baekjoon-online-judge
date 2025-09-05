import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] A = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxNumber = 0;
        for (int num: A) {
            maxNumber = Math.max(maxNumber, num);
        }

        int budget = Integer.parseInt(reader.readLine());
        System.out.println(search(A, maxNumber, budget));
    }

    private static int search(int[] A, int right, int budget) {
        int left = 0;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 상한액
            long total = 0;               // 예산총액
            for (int num: A) {
                total += Math.min(num, mid);
            }

            if (total <= budget) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }
}