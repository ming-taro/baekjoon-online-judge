import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String word = reader.readLine();
        int start = 0; // a의 개수만큼 슬라이딩
        int end = -1;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a') end++;
        }

        int countOfB = 0;
        for (int i = 0; i <= end; i++) {
            if (word.charAt(i) == 'b') countOfB++;
        }

        int answer = countOfB;
        while (start + 1 < word.length()) {
            if (word.charAt(start) == 'b') countOfB--;
            start++;
            end = (end + 1) % word.length();
            if (word.charAt(end) == 'b') countOfB++;

            answer = Math.min(answer, countOfB);
        }

        System.out.println(answer);
    }
}