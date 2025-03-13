import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        Set<String> emoticon = new HashSet<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            if (input.equals("ENTER")) {
                answer += emoticon.size();
                emoticon.clear();
            } else {
                emoticon.add(input);
            }
        }

        answer += emoticon.size();
        System.out.println(answer);
    }
}
/*
ENTER -  곰곰티콘 인사 -> ENTER

ENTER -> set(id1, id2...) -> answer += set.size() -> ENTER -> ....
 */