import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 단어 개수
        Set<Character> words = new HashSet<>();      // 단축키 리스트
        StringBuilder answer = new StringBuilder();

        for (int n = 0; n < N; n++) {
            String[] word = reader.readLine().split(" ");

            // 1. 첫 글자 검사
            StringBuilder result = new StringBuilder();
            boolean flag = false; // 첫 글자가 단축키 지정 가능 한지 검사
            for (String target: word) {
                if (!flag && !words.contains(Character.toUpperCase(target.charAt(0)))) {
                    result.append("[" + target.charAt(0) + "]").append(target.substring(1)).append(" ");
                    words.add(Character.toUpperCase(target.charAt(0)));
                    flag = true;
                } else {
                    result.append(target).append(" ");
                }
            }

            if (flag) {
                answer.append(result + "\n");
                continue; // 첫 글자 검사에서 통과된 단어 -> 다음 단어 검사하기
            }

            // 2. 전체 글자 검사
            result.setLength(0);
            flag = false;
            for (String target: word) {
                if (flag) {
                    result.append(target).append(" ");
                    continue;
                }

                for (int i = 0; i < target.length(); i++) {
                    if (!flag && !words.contains(Character.toUpperCase(target.charAt(i)))) {
                        result.append("[" + target.charAt(i) + "]");
                        words.add(Character.toUpperCase(target.charAt(i)));
                        flag = true;
                    } else {
                        result.append(target.charAt(i));
                    }
                }
                result.append(" ");
            }
            answer.append(result + "\n");
        }

        System.out.println(answer);
    }
}