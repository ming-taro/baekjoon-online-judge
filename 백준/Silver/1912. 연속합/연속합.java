import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int max = numbers[0];
        int sum = 0;
        for (int i=0; i<n; i++) {
            sum += numbers[i];
            max = Math.max(max, sum);
            if (sum < 0) sum = 0;
        }

        System.out.print(max);
    }
}