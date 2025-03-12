import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());

        int[][] nodes = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(nodes[i], Integer.MAX_VALUE);
            nodes[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes[a - 1][b - 1] = Math.min(nodes[a - 1][b - 1], c);
        }

        for (int k = 0; k < n; k++) {             // k = 경유
            for (int i = 0; i < n; i++) {         // i = 시작
                for (int j = 0; j < n; j++) {     // j = 도착
                    if (nodes[i][k] != Integer.MAX_VALUE && nodes[k][j] != Integer.MAX_VALUE) {
                        nodes[i][j] = Math.min(nodes[i][j], nodes[i][k] + nodes[k][j]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nodes[i][j] == Integer.MAX_VALUE) {
                    System.out.print("0 ");
                } else {
                    System.out.print(nodes[i][j] + " ");
                }
            } System.out.println();
        }
    }
}
/*
"출발 노드 - 도착 노드 - 거쳐가는 노드" 순서로 거쳐가는 노드가 내부에 있으면
(1 -> 2) 경로 다음으로 (1 -> 3), (1 -> 4) .. (2 -> 1), (2 -> 3), ...
즉 각각의 경로를 한번만 검사하고 넘어가게 되면서
업데이트된 경로값을 검사하지 못한 채 다음 경로 구하기로 넘어간다
=> 거쳐가는 노드는 최상단에 위치해야 함
 */