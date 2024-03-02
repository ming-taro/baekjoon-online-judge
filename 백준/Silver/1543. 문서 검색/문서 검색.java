import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();
        String searchText = reader.readLine();

        int count = 0;
        int start = 0;

        for (int i = 0; i <= text.length() - searchText.length(); ) {
            boolean hasText = true;
            for (int j = 0; j < searchText.length(); j++) {
                if (searchText.charAt(j) != text.charAt(i + j)) {
                    hasText = false;
                    break;
                }
            }

            if (hasText) {
                count++;
                i += searchText.length();
            } else {
                i++;
            }
        }

        System.out.println(count);
    }
}