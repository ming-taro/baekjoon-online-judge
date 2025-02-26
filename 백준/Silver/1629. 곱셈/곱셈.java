import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        System.out.println(run(a, b, c));
    }

    private static long run(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }

        long val = run(a, b / 2, c);
        
        if (b % 2 == 1) {
            return (val * val % c) * a % c;
        }
        return val * val % c;
    }
}