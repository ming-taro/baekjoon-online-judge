import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();  //원생의 입력은 이미 정렬되어 있음

        int[] difference = new int[N - 1];
        int total = 0;

        for (int i = 0; i < N - 1; i++) {
            difference[i] = numbers[i + 1] - numbers[i];
            total += difference[i];
        }
        Arrays.sort(difference);

        for (int i = 0; i < K - 1; i++) {
            total -= difference[N - 2 - i];
        }

        System.out.println(total);
    }
}
/*
1 3 5 7 8 10 14 15 20
 2 2 2 1 2  4  1  5  = 19   (인접 숫자간 차)
   6      6       5  = 18   (제외: 1, 1)
  4      7        5  = 16   (제외: 2, 1)
   9           1     = 10   (제외: 4, 5) <=가장 최소가 되는 합
=>총 차액 중 (k - 1)개의 가장 큰 차를 제외한 나머지 합이 가장 최소가 되는 합이다
*/