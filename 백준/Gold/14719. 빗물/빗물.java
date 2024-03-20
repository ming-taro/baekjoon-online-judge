import java.io.*;
import java.util.*;

class Block {
    int start;
    int end;

    public Block(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        int H = Integer.parseInt(input.nextToken());
        int W = Integer.parseInt(input.nextToken());

        int[] height = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int total = 0;

        for (int current = 1; current < W - 1; current++) {
            int left = 0, right = 0;

            for (int i = 0; i < current; i++) {
                left = Math.max(left, height[i]);
            }
            for (int i = current + 1; i < W; i++) {
                right = Math.max(right, height[i]);
            }

            if (height[current] < left && height[current] < right) {
                total += Math.min(left, right) - height[current];
            }
        }

        System.out.println(total);
    }
}