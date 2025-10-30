import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        int maxNum = (int)Math.pow(10, 8);
        boolean[] isValid = new boolean[maxNum + 1];

        for (int num = 2; num <= Math.sqrt(maxNum); num++) {
            if (isValid[num]) continue;
            for (int i = num * 2; i <= maxNum; i += num) {
                isValid[i] = true;
            }
        }

        long result = 1;

        for (int num = 2; num <= N; num++) {
            if (isValid[num]) continue;
            long current = 1;
            while (current * num <= N) current *= num;
            result = (long) (result * current % Math.pow(2, 32));
        }

        System.out.println(result);
    }
}
/*
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
위 집합의 최소 공배수 = 2520 = 2^3 * 3^2 * 5^1 * 7^1
즉, 모든 자연수 = 최소 공배수 = 소수의 곱(소인수분해)
 */