import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean isMatch = false;

    public static void convert(String S, String T) {
        if (S.contentEquals(T)){
            isMatch = true;
        }

        StringBuilder builder = new StringBuilder(T);

        if (S.length() >= T.length()
                || !builder.toString().contains(S) && !builder.reverse().toString().contains(S)) {
            return;
        }

        convert(S + 'A', T);

        builder = new StringBuilder(S + 'B');
        convert(builder.reverse().toString(), T);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String S = reader.readLine();
        String T = reader.readLine();

        convert(S, T);

        if (isMatch) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }
}