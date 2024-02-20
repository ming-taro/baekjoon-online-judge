import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<Integer>[] indexList = new ArrayList[26];
    private static String W;
    private static int K;
    private static StringBuilder result = new StringBuilder();
    private static int maxLength, minLength;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 26; i++) {
            indexList[i] = new ArrayList<>();
        }

        int T = Integer.parseInt(reader.readLine());

        for (int i = 0; i < T; i++) {
            W = reader.readLine();
            K = Integer.parseInt(reader.readLine());

            boolean isUpdated = false;
            for (int j = 0; j < 26; j++) {   //인덱스 리스트 비움
                indexList[j].clear();
            }
            maxLength = Integer.MIN_VALUE;
            minLength = Integer.MAX_VALUE;

            for (int j = 0; j < W.length(); j++) {    //indexList에 각 알파벳의 인덱스값 차례로 저장
                int index = W.charAt(j) - 'a';
                indexList[index].add(j);
            }

            for (int j = 0; j < 26; j++) {
                if (indexList[j].size() >= K) {       //K개 이상의 문자가 존재하는 알파벳의 인덱스 위치만 확인

                    isUpdated = true;
                    start(j);
                }
            }

            if (!isUpdated) {
                result.append(-1).append("\n");
            } else {
                result.append(minLength).append(" ").append(maxLength).append("\n");
            }
        }

        System.out.println(result.toString());
    }

    private static void start(int alphabetIndex) {
        List<Integer> indexs = indexList[alphabetIndex];
        int start = 0;
        int end = K - 1;  //K개의 알파벳이 존재해야 하므로 가장 작은 end = K - 1

        while (end < indexs.size()) {
            maxLength = Math.max(maxLength, calcLength(indexs.get(start), indexs.get(end))); //길이가 작은 문자열과 같은 문자를 포함 해야 한다는 조건이 없기 때문에 항상 갱신 해주면 된다..
            minLength = Math.min(minLength, calcLength(indexs.get(start), indexs.get(end))); //K개의 알파벳을 포함할 때 가장 작은 문자열도 시작과 끝이 같은 문자열일 수밖에 없음 -> 긴 문자열을 구하는 방법과 같다
            start++; //알파벳이 K개만 존재하는 윈도우를 유지해야 하므로 start, end를 같이 한 칸씩 움직임
            end++;
        }

    }

    private static int calcLength(int start, int end) {
        return end - start + 1;
    }
}