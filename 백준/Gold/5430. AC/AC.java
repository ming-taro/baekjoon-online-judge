import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(reader.readLine());

        for (int i = 0; i < T; i++) {
            String p = reader.readLine();  //수행할 함수
            int n = Integer.parseInt(reader.readLine());

            Deque<Integer> q = createDeque(n, reader.readLine());
            System.out.println(run(p, q));
        }
    }

    private static Deque<Integer> createDeque(int n, String array) {
        Deque<Integer> q = new LinkedList<>();
        array = array.substring(1, array.length() - 1);

        if (n == 0) {
            return q;
        }

        int[] numbers = Arrays.stream(array.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int number : numbers) {
            q.add(number);
        }

        return q;
    }

    private static String run(String p, Deque<Integer> numbers) {
        boolean reverse = false;

        for (int i = 0; i < p.length(); i++) {
            //첫 번째 숫자 삭제
            if (p.charAt(i) == 'D') {
                if (numbers.isEmpty()) {  //빈 배열에서 삭제 작업을 하는 경우 -> error
                    return "error";
                }

                if (reverse) {  //뒤에서 부터 시작인 경우
                    numbers.pollLast();
                } else {
                    numbers.poll();  //앞에서 부터 시작인 경우
                }
                continue;
            }

            //시작점 변경
            reverse = !reverse;
        }

        return createNumberString(reverse, numbers);
    }

    private static String createNumberString(boolean reverse, Deque<Integer> numbers) {
        StringJoiner stringJoiner = new StringJoiner(",");

        while (!numbers.isEmpty()) {
            if (reverse) {
                stringJoiner.add(Integer.toString(numbers.pollLast()));
            } else {
                stringJoiner.add(Integer.toString(numbers.pollFirst()));
            }
        }

        return "[" + stringJoiner.toString() + "]";
    }
}