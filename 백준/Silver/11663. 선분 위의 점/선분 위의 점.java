import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());

        int[] dot = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(dot);

        for (int i = 0; i < M; i++) {
            input = new StringTokenizer(reader.readLine());

            int start = Integer.parseInt(input.nextToken());
            int end = Integer.parseInt(input.nextToken());

            System.out.println(searchHigher(N, dot, end) - searchLower(N, dot, start) + 1);
        }
    }

    private static int searchLower(int N, int[] dot, int value) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (dot[mid] >= value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static int searchHigher(int N, int[] dot, int value) {
        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (dot[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}