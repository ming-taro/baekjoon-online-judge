import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int K = Integer.parseInt(reader.readLine());

        int[] points = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .distinct()
                .toArray();
        Arrays.sort(points);

        N = points.length;

        int[] difference = new int[N - 1];
        for (int index = 0; index < N - 1; index++) {
            difference[index] = points[index + 1] - points[index];
        }
        Arrays.sort(difference);

        int total = 0;
        for (int index = 0; index < N - K; index++) {
            total += difference[index];
        }

        System.out.println(total);
    }
}
/*
N개의 센서, K개의 집중국
->센서는 최소 1개의 집중국과 통신
->집중국은 수신 가능 영역 길이 합 최소화

1 3 6 7 9
 2 3 1 2  =>2+3+1+2 = 8
  5    2  =>2+3+2 = 7
   6    0 =>2+3+1 = 6
 2    3   =>2+1+2 = 5

총 5개의 센서
집중국이 1개 -> 4개 영역 합
집중국이 2개 -> 3개 영역 합
집중국이 3개 -> 2개 영역 합
->인접 센서간 차를 구한 후 가장 영역이 작은순서대로 (N - K)개 영역을 골라 합을 구함

(cf) 행복유치원)
 */