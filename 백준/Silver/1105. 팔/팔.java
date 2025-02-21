import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] left = st.nextToken().toCharArray();
        char[] right = st.nextToken().toCharArray();

        int answer = 0;
        if (left.length == right.length) {
            for (int i = 0; i < left.length; i++) {
                if (left[i] == '8' && right[i] == '8') {
                    answer++;
                } else if (left[i] != right[i]) {
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}