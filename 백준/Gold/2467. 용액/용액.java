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

        int left = 0;
        int right = array.length - 1;

        long[] answer = {array[left], array[right]};
        long dif = Math.abs(array[left] + array[right]);

        while (left < right) {
            if (Math.abs(array[left] + array[right]) < dif) {
                answer[0] = array[left];
                answer[1] = array[right];
                dif = Math.abs(array[left] + array[right]);
            }

            if (Math.abs(array[left + 1] + array[right]) < Math.abs(array[left] + array[right - 1])) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}