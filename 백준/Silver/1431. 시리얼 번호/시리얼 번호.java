import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        String[] text = new String[N];
        for (int i = 0; i < N; i++) {
            text[i] = reader.readLine();
        }

        Arrays.sort(text, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                int sum1 = 0;
                int sum2 = 0;

                for (int i = 0; i < o1.length(); i++) {
                    if (o1.charAt(i) >= '0' && o1.charAt(i) <= '9') sum1 += o1.charAt(i) - '0';
                }
                for (int i = 0; i < o2.length(); i++) {
                    if (o2.charAt(i) >= '0' && o2.charAt(i) <= '9') sum2 += o2.charAt(i) - '0';
                }

                if (sum1 != sum2) return sum1 - sum2;
                return o1.compareTo(o2);
            }

            return o1.length() - o2.length();
        });

        for (String str: text) {
            System.out.println(str);
        }
    }
}