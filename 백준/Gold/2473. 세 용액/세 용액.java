import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        long[] array = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        Arrays.sort(array);

        long value = Math.abs(array[0] + array[1] + array[2]);
        long[] answer = { array[0], array[1], array[2] };

        for (int i = 0; i <= N - 3; i++) {
            int left = i + 1;
            int right = array.length - 1;

            while (true) {
                long currentValue = Math.abs(array[i] + array[left] + array[right]);
                if (currentValue < value) {
                    value = currentValue;
                    answer[0] = array[i];
                    answer[1] = array[left];
                    answer[2] = array[right];
                }

                if (left + 1 >= right || right - 1 <= left) break;

                if (Math.abs(array[i] + array[left + 1] + array[right]) < Math.abs(array[i] + array[left] + array[right - 1])) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}