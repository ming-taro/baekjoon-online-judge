import java.io.*;
import java.util.*;

class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Main {
    private static int N;
    private static List<Integer>[] child;
    private static int[] totalTime;   //총 건설 시간
    private static int[] constructionTime;        //건물 건설 시간
    private static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        totalTime = new int[N + 1];
        count = new int[N + 1];
        constructionTime = new int[N + 1];

        child = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            child[i] = new ArrayList<>();
        }

        List<Integer> rootNode = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            String[] input = reader.readLine().split(" ");
            int time = Integer.parseInt(input[0]);
            constructionTime[i] = time;

            if (input[1].equals("-1")) {
                rootNode.add(i);
                continue;
            }

            for (int j = 1; j < input.length - 1; j++) {
                int number = Integer.parseInt(input[j]);  //먼저 지어야 하는 건물
                count[i]++;             //진입차수 계산
                child[number].add(i);   //number -> i 연결이므로 node[number]에 현재 입력 받은 건물 번호를 연결해 줌
            }
        }

        for (int node: rootNode) {
            calcTime(node);
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(totalTime[i]);
        }
    }

    private static void calcTime(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            totalTime[current] += constructionTime[current];

            for (int next: child[current]) {
                count[next]--;
                totalTime[next] = Math.max(totalTime[next], totalTime[current]);

                if (count[next] == 0) {
                    queue.offer(next);
                }
            }
        }
    }
}