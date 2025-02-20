import java.io.*;
import java.util.*;

class Main {
    private static int MAX_WEIGHT = 500;
    private static int MAX_BEADS = 40000;
    private static boolean[][] result;
    private static int[] weight;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N1 = Integer.parseInt(reader.readLine());
        weight = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        result = new boolean[N1 + 1][MAX_BEADS + 1];

        int N2 = Integer.parseInt(reader.readLine());
        int[] beads = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dfs(0, 0);

        for (int b: beads) {
            if (result[N1][b]) {
                System.out.print("Y ");
            } else {
                System.out.print("N ");
            }
        }
    }

    private static void dfs(int current, int totalWeight) {
        if (result[current][totalWeight]) return; // current 추를 포함 하여 이미 무게를 쟀다면 더 이상 탐색할 필요X
        result[current][totalWeight] = true;
        if (current >= weight.length) return;

        dfs(current + 1, totalWeight);    // 추 추가X
        dfs(current + 1, totalWeight + weight[current]); // 추 추가
        dfs(current + 1, Math.abs(totalWeight - weight[current])); // 반대 편에 추 올리기
    }
}
/*
->result[0]은 1g전 까지의 구슬, 즉 아무 구슬도 활용 하지 않는 경우의 조합
->result[1]은 4g전 까지의 구슬, 즉 1g 구슬만 활용 하는 경우의 조합
->result[2]는 1, 4g을 모두 활용한 최종 조합 모음
=> 따라서 배낭을 모두 구했 다면 구슬 무게의 측정 가능 여부는 result[2]만 검사 하면 됨

0. 1g 작업 전
current = 0, 0g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |0 0 0 0 0 0
     2 |0 0 0 0 0 0
-------------------
0-1. 1g 추가X
current = 1, 0g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 0 0 0 0 0
     2 |0 0 0 0 0 0
-------------------
0-1-1. 4g 추가X
current = 2, 0g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 0 0 0 0 0
     2 |1 0 0 0 0 0
=> 아무 추도 올리지 않는 0g인 경우는 1개
=> current = 2이므로 탐색 종료
-------------------
0-1-2. 4g 추가
current = 2, 4g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 0 0 0 0 0
     2 |1 0 0 0 1 0
=> 아무 추도 올리지 않는 0g인 경우는 1개
=> current = 2이므로 탐색 종료
-------------------
0-1-3. 반대 편에 4g 올리기
current = 2, 4g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 0 0 0 0 0
     2 |1 0 0 0 1 0
=> 0-1-2와 같은 조합으로 result[2][4] = true이므로 더 이상 탐색X
-------------------
0-2. 1g 추가
current = 1, 1g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 1 0 0 0 0
     2 |1 0 0 0 1 0
-------------------
0-2-1. 4g 추가X
current = 2, 1g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 1 0 0 0 0
     2 |1 1 0 0 1 0
=> 1g 무게 측정 가능
-------------------
0-2-2. 4g 추가
current = 2, 5g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 1 0 0 0 0
     2 |1 1 0 0 1 1
=> 5g 무게 측정 가능
-------------------
0-2-3. 반대 편에 4g 올리기
current = 2, 3g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 1 0 0 0 0
     2 |1 1 0 1 1 1
=> 3g 무게 측정 가능
-------------------
0-3. 반대 편에 1g 올리기
current = 1, 1g
        0 1 2 3 4 5
(1g) 0 |1 0 0 0 0 0
(4g) 1 |1 1 0 0 0 0
     2 |1 1 0 1 1 1
=> 이미 1g으로 할 수 있는 조합은 끝남
=> 현재 저울에 1g을 놓는 것과, 반대편 저울에 1g을 놓는 것은 방향만 다를뿐 결국 같은 조합
=> 더 진행 할 경우 시간 초과 발생
 */