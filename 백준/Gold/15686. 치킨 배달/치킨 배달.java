import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static int minDistance = Integer.MAX_VALUE;
    private static int[][] distance;
    private static List<int[]> home = new ArrayList<>();
    private static List<int[]> chicken = new ArrayList<>();
    private static List<Integer> selection = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String[] input = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(input[j]);
                if (number == 1) {
                    home.add(new int[]{i, j});
                } else if (number == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        distance = new int[home.size()][chicken.size()];

        for (int i = 0; i < home.size(); i++) {
            for (int j = 0; j < chicken.size(); j++) {
                int d = calcDistance(home.get(i), chicken.get(j));
                if (distance[i][j] == 0) {
                    distance[i][j] = d;
                } else {
                    distance[i][j] = Math.min(distance[i][j], d);
                }
            }
        }

        selectChicken(0);
        System.out.println(minDistance);
    }

    private static int calcDistance(int[] start, int[] end) {
        return Math.abs(start[0] - end[0]) + Math.abs(start[1] - end[1]);
    }

    private static void selectChicken(int chickenIndex) {
        if (selection.size() == M) {
            int totalDistance = 0;
            for (int homeIndex = 0; homeIndex < home.size(); homeIndex++) {
                totalDistance += findMinDistance(homeIndex);
            }
            minDistance = Math.min(minDistance, totalDistance);
            return;
        }

        for (int i = chickenIndex; i < chicken.size(); i++) {
            selection.add(i);
            selectChicken(i + 1);
            selection.remove(selection.size() - 1);
        }
    }

    private static int findMinDistance(int homeIndex) {
        int minDistance = Integer.MAX_VALUE;

        for (int chickenIndex: selection) {
            minDistance = Math.min(minDistance, distance[homeIndex][chickenIndex]);
        }

        return minDistance;
    }
}
/*
->N * N 크기의 도시
->빈칸(0), 집(1), 치킨집(2)
->r, c는 1부터 시작
->도시의 치킨 거리 = 모즌 집의 치킨 거리 합
->M개의 치킨집만 두고, 나머지는 폐업
return 최대 M개의 치킨집만 있을 때 도시의 치킨 거리 최솟값
 */