import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());  //PD

        List<Integer>[] order = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            order[i] = new ArrayList<>();
        }

        int[] value = new int[N + 1]; //진입 차수
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine()); //PD 각자가 담당한 가수의 출연 순서
            stringTokenizer.nextToken();  //PD가 맡을 가수 총원

            int current = Integer.parseInt(stringTokenizer.nextToken());

            while (stringTokenizer.hasMoreTokens()) {
                int next = Integer.parseInt(stringTokenizer.nextToken());
                order[current].add(next);
                value[next]++; //진입 차수
                current = next;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (value[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int currentNumber = queue.poll();
            result.append(currentNumber).append("\n");

            for (int number: order[currentNumber]) {
                value[number]--;
                if (value[number] == 0) {
                    queue.add(number);   //진입 차수가 0인 번호를 큐에 넣음
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (value[i] != 0) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(result.toString());
    }
}