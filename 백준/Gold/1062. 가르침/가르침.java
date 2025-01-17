import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<Character>[] nodes;
    private static List<Character> wordList;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Set<Character> wordSet = new HashSet<>(); // 기본글자 외에 가르칠 글자 모음
        Set<Character> combSet = new HashSet<>(); // 현재 가르치고있는 글자 모음(기본글자 포함)
        combSet.add('a');
        combSet.add('n');
        combSet.add('t');
        combSet.add('i');
        combSet.add('c');

        nodes = new ArrayList[N]; // 각 단어별 글자 모음

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            Set<Character> tmp = new HashSet<>();
            String input = reader.readLine();
            words[i] = input.substring(4, input.length() - 4);
            for (int j = 0; j < words[i].length(); j++) {
                if (!combSet.contains(words[i].charAt(j))) { // 기본 글자는 무조건 가르쳐야 함 -> combSet에는 그 외에 가르칠 글자만을 모아야 함
                    wordSet.add(words[i].charAt(j));
                }
                tmp.add(words[i].charAt(j));
            }
            nodes[i] = new ArrayList<>(tmp);
        }
        wordList = new ArrayList<>(wordSet);

        if (K < combSet.size()) { // 기본 글자조차도 가르치지 못하는 상황 -> 종료
            System.out.println(0);
            return;
        }

        dfs(K, 0, combSet);
        System.out.println(answer);
    }

    private static void dfs(int K, int current, Set<Character> combSet) {
        if (combSet.size() == K || combSet.size() == wordList.size() + 5) {
            int count = 0;
            for (List<Character> node : nodes) {
                boolean flag = false;
                for (char ch : node) {
                    if (!combSet.contains(ch)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) count++;
            }
            answer = Math.max(answer, count);
            return;
        }

        for (int i = current; i < wordList.size(); i++) {
            combSet.add(wordList.get(i));
            dfs(K, i + 1, combSet);
            combSet.remove(wordList.get(i));
        }
    }
}