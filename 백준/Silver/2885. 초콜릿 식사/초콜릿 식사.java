import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long K = Integer.parseInt(reader.readLine());

        long i = 1;
        while (true) {
            if (Math.pow(2, i) >= K) {
                break;
            }
            i++;
        }

        long chocolate = (long)Math.pow(2, i);
        System.out.print(chocolate + " ");

        if (K == chocolate) {
            System.out.println(0);
            return;
        }

        int count = 0;

        while (chocolate > 0 && K > 0) {
            chocolate /= 2;
             if (chocolate <= K) {
                 K -= chocolate;
             }
            count++;
        }

        System.out.println(count);
    }
}