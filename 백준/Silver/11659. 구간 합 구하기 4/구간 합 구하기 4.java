import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sum = new int[N];
        sum[0] = numbers[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + numbers[i];
        }


        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(reader.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            System.out.println(sum[j - 1] - sum[i - 1] + numbers[i - 1]);
        }
    }
}
