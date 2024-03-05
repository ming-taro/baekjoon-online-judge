import java.io.*;
import java.util.*;

public class Main {
    private static int[][] board;
    private static int[] parent;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());  //도시 수
        int M = Integer.parseInt(reader.readLine());  //여행 계획에 속한 도시 수

        board = new int[N + 1][N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer number = new StringTokenizer(reader.readLine());
            for (int j = 1; j <= N; j++) {
                if (number.nextToken().equals("1")) {
                    union(i, j);
                }
            }
        }

        int[] plan = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();   //여행 계획

        if (M > 1) {
            int prev = findParent(plan[0]);

            for (int i = 1; i < M; i++) {
                if (prev != findParent(plan[i])) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }

    private static void union(int start, int end) {   //두 정점은 인접하게 연결 되어 있음
        int parentA = findParent(start);              //두 정점의 루트 노드를 찾음
        int parentB = findParent(end);

        if (parentA != parentB) {     //각 정점의 루트가 다르다면 작은 번호의 루트 노드 하나로 통일 -> 합집합
            parent[parentA] = Math.min(parentA, parentB);
            parent[parentB] = Math.min(parentA, parentB);
        }
    }

    private static int findParent(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = findParent(parent[a]);
        return parent[a];  //루트 노드를 찾음(1->2->3->4 처럼 깊이가 깊어지는 트리의 경우 부모값을 저장하면서 재귀를 돌리면 1, 2, 3의 부모 노드가 4가 됨 => 경로 압축)
    }
}