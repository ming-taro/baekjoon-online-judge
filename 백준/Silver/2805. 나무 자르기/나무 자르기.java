import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());
        long N = Integer.parseInt(stringTokenizer.nextToken());
        long M = Integer.parseInt(stringTokenizer.nextToken());

        long[] trees = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        Arrays.sort(trees);

        long start = 1;
        long end = 1_000_000_000;

        while (start <= end) {
            long target = (start + end) / 2;  //절단할 높이의 최댓값
            long sum = 0;

            for (long tree: trees){
                if (tree > target) {
                    sum += tree - target;  //가져갈 나무 길이
                }

                if (sum >= M) {
                    break;
                }
            }

            if (sum < M) {
                end = target - 1;
            } else {
                start = target + 1;
            }
        }

        System.out.println(end);
    }
}