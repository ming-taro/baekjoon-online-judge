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
                    if (nodes[j][k] != Integer.MAX_VALUE && nodes[k][i] != Integer.MAX_VALUE) {
                        nodes[j][i] = Math.min(nodes[j][i], nodes[j][k] + nodes[k][i]);
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