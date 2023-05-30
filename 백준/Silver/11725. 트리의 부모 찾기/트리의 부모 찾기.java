import java.io.*;
import java.util.*;

public class Main {

    static boolean[] isVisit;
    static ArrayList<Integer>[] node;
    static int[] parents;
    private static void dfs(int start) {
        isVisit[start] = true;

        for (Integer v: node[start]) {
            if (isVisit[v] == false) {
                dfs(v);
                parents[v] = start;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        isVisit = new boolean[N + 1];
        node = new ArrayList[N + 1];
        parents = new int[N + 1];

        for(int index = 1; index <= N ; index++) {
            node[index] = new ArrayList<Integer>();
        }

        for(int index = 1; index < N; index++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            node[v1].add(v2);
            node[v2].add(v1);
        }

        dfs(1);

        for(int index = 2; index <= N; index++){
            System.out.println(parents[index]);
        }
    }
}