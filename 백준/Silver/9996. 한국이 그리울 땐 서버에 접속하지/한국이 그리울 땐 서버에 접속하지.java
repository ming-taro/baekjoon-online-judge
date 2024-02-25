import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        String pattern = reader.readLine();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String text = reader.readLine();
            if (isMatch(text, pattern)) {
                result.append("DA\n");
            } else {
                result.append("NE\n");
            }
        }

        System.out.println(result.toString());
    }

    public static boolean isMatch(String text, String pattern) {
        if (text.length() < pattern.length() - 1) {
            return false;
        }

        int separatorIndex = pattern.indexOf("*");

        for (int textIndex = 0, patternIndex = 0; patternIndex < separatorIndex; textIndex++, patternIndex++) {
            if (text.charAt(textIndex) != pattern.charAt(patternIndex)) {
                return false;
            }
        }

        for (int textIndex = text.length() - 1, patternIndex = pattern.length() - 1; patternIndex > separatorIndex; textIndex--, patternIndex--) {
            if (text.charAt(textIndex) != pattern.charAt(patternIndex)) {
                return false;
            }
        }

        return true;
    }
}
/*
->문자열 == 알파벳 소문자 + 별표(*) + 알파벳 소문자
return 패턴과 파일 이름이 주어졌을 때, 각각의 파일 이름이 패턴과 일치 하는가
 */