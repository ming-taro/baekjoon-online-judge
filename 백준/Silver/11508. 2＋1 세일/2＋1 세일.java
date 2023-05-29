import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] price = new Integer[N];

        for (int index = 0; index < N; index++) {
            price[index] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(price, Collections.reverseOrder());

        int totalPrice = 0;

        for (int index = 0; index < N; index += 3) {
            totalPrice += price[index];
            if (N - index >= 2) {
                totalPrice += price[index + 1];
            }
        }

        System.out.println(totalPrice);
        br.close();
    }
}

//제품: 2 + 1
//3개 제품 구매시 가장 비싼 두 제품 가격만 지불