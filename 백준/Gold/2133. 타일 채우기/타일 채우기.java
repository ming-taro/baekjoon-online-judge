import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        dfs(0, 1, N);
        System.out.println(answer);
    }

    private static void dfs(int currentWidth, int total, int width) {
        if (currentWidth == width) {
            answer += total;
            return;
        }

        for (int num = 2; num <= width; num += 2) {
            if (currentWidth + num > width) return;
            if (num == 2) {
                dfs(currentWidth + num, total * 3, width);
            } else {
                dfs(currentWidth + num, total * 2, width);
            }
        }
    }
}