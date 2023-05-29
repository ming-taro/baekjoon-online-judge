import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int coin = 0;

        if (n < 5) {
            if (n % 2 == 0){
                System.out.println(n / 2);
            }
            else {
                System.out.println(-1);
            }
            return;
        }

        coin += n / 5;
        n %= 5;

        if (n % 2 == 0){
            coin += n / 2;
        }
        else if (coin >= 1 && n % 2 == 1) {
            coin = (coin - 1) + n / 2 + 3;   //5원 동전 + 1원 = 6원 즉 2원동전 3개
        }
        else {
            System.out.println(-1);
            return;
        }

        System.out.println(coin);
    }
}