import java.io.*;
import java.util.*;

class Node {
    boolean isOdd;
    int count;

    public Node(boolean isOdd, int count) {
        this.isOdd = isOdd;
        this.count = count;
    }

    @Override
    public String toString() {
        return "isOdd: " + isOdd + ", count: " + count;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(input.nextToken()); // 수열의 길이
        int K = Integer.parseInt(input.nextToken()); // 삭제할 수 있는 최대 횟수

        int[] S = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean isOdd = S[0] % 2 == 1;
        int count = 0;
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (isOdd && S[i] % 2 == 1 || !isOdd && S[i] % 2 == 0) {
                count++;
                continue;
            }
            nodes.add(new Node(isOdd, count));
            count = 1;
            isOdd = !isOdd;
        }
        nodes.add(new Node(isOdd, count));

        int result = 0;
        int start = 0;
        int evenCount = 0;
        int oddCount = 0;
        isOdd = nodes.get(0).isOdd;

        for (int i = 0; i < nodes.size(); i++) {
            if (i == 0 && isOdd) {
                oddCount = 0;
                isOdd = false;
                start = 1;
                continue;
            }

            if (isOdd) {
                oddCount += nodes.get(i).count;
            } else {
                evenCount += nodes.get(i).count;
            }
            isOdd = !isOdd;

            if (oddCount <= K) continue;

            result = Math.max(result, evenCount); // 수열에서 홀수를 K번을 초과하여 삭제한 경우

            if (start + 2 >= N) {
                break;
            }

            for (int j = start; j <= i; j++) {
                if (oddCount <= K) {
                    start = j;
                    break;
                }
                if (nodes.get(j).isOdd) {
                    oddCount -= nodes.get(j).count;
                } else {
                    evenCount -= nodes.get(j).count;
                }
            }
        }

        result = Math.max(result, evenCount);

        System.out.println(result);
    }
}