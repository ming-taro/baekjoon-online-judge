import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        long[] card = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        Arrays.sort(card);

        int M = Integer.parseInt(reader.readLine());
        long[] target = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        StringBuilder answer = new StringBuilder();

        for (long number: target) {
            int first = searchFirstIndex(card, number);
            if (first < 0 || first >= card.length || card[first] != number) {
                answer.append(0 + " ");
                continue;
            }
            int last = searchLastIndex(card, number);
            answer.append((last - first + 1) + " ");
        }

        System.out.println(answer);
    }

    private static int searchFirstIndex(long[] card, long target) {
        int left = -1;
        int right = card.length;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (card[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }

    private static int searchLastIndex(long[] card, long target) {
        int left = -1;
        int right = card.length;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (card[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right - 1;
    }
}