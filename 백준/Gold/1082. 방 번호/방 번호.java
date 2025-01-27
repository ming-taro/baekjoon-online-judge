import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[][] number = new int[N][2]; // 숫자 - 가격
        int[] price = new int[N];
        for (int i = 0; i < N; i++) {
            number[i][0] = i;
            number[i][1] = Integer.parseInt(st.nextToken());
            price[i] = number[i][1];
        }
        Arrays.sort(number, (o1, o2) -> o1[1] - o2[1]);

        int M = Integer.parseInt(reader.readLine());

        int digit = M / number[0][1];
        int totalPrice = number[0][1] * digit;
        int[] result = new int[digit];
        Arrays.fill(result, number[0][0]);

        if (number[0][0] == 0 && N > 1 && number[1][1] <= M) { // 최소숫자가 ?000 형태가 가능한 경우
            digit = 1 + (M - number[1][1]) / number[0][1];
            totalPrice = number[1][1] + number[0][1] * (digit - 1);
            result = new int[digit];
            Arrays.fill(result, number[0][0]);
            result[0] = number[1][0];
        }

        for (int i = 0; i < digit; i++) {
            for (int[] num: number) {
                int newPrice = totalPrice - price[result[i]] + num[1];
                if (num[0] > result[i] && newPrice <= M) {
                    totalPrice = newPrice;
                    result[i] = num[0];
                }
            }
        }

        if (digit > 1 && result[0] == 0) {
            System.out.println(0);
        } else {
            for (int r: result) {
                System.out.print(r);
            }
        }
    }
}