import java.io.*;
import java.util.*;

class Main {
    private static int[][] A;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        A = new int[100][100];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rowCount = 3;
        int colCount = 3;
        int time = 0;

        while(true) {
            if (A[r - 1][c - 1] == k) break;
            if (time == 100) {
                time = -1;
                break;
            }

            if (rowCount >= colCount) {
                colCount = runRowRange(); // 행 연산 적용 -> 행의 크기는 그대로지만 열의 크기가 달라짐
            } else {
                rowCount = runColRange(); // 열 연산 적용 -> 열의 크기는 그대로지만 행의 크기가 달라짐
            }
            time++;
        }

        System.out.println(time);
    }

    private static int runRowRange() {
        int maxRowSize = 0;

        for (int r = 0; r < 100; r++) {
            Map<Integer, Integer> map = new HashMap<>();        // 1. 정렬 (num, count)
            for (int c = 0; c < 100; c++) {
                if (A[r][c] == 0) continue;
                int count = 1;
                if (map.containsKey(A[r][c])) {
                    count += map.get(A[r][c]);
                }
                map.put(A[r][c], count);
            }
            if (map.isEmpty()) continue;

            List<Integer> key = new ArrayList<>(map.keySet()); // 2. 등장 횟수 적은 순 > 수의 크기가 작은 순
            key.sort((o1, o2) -> {
                if (map.get(o1) == map.get(o2)) {
                    return o1 - o2;
                }
                return map.get(o1) - map.get(o2);
            });

            int col = 0;
            for (int k: key) {                       // 3. A 배열에 다시 정렬
                if (col == 100) break;
                A[r][col++] = k;
                A[r][col++] = map.get(k);
            }

            for (int c = col; c < 100; c++) {        // 4. 줄어든 행 사이즈 만큼 0으로 채우기
                A[r][c] = 0;
            }

            maxRowSize = Math.max(maxRowSize, col);
        }

        return maxRowSize;
    }

    private static int runColRange() {
        int maxColSize = 0;

        for (int c = 0; c < 100; c++) {
            Map<Integer, Integer> map = new HashMap<>();        // 1. 정렬 (num, count)
            for (int r = 0; r < 100; r++) {
                if (A[r][c] == 0) continue;
                int count = 1;
                if (map.containsKey(A[r][c])) {
                    count += map.get(A[r][c]);
                }
                map.put(A[r][c], count);
            }
            if (map.isEmpty()) continue;

            List<Integer> key = new ArrayList<>(map.keySet()); // 2. 등장 횟수 적은 순 > 수의 크기가 작은 순
            key.sort((o1, o2) -> {
                if (map.get(o1) == map.get(o2)) {
                    return o1 - o2;
                }
                return map.get(o1) - map.get(o2);
            });

            int row = 0;
            for (int k: key) {                       // 3. A 배열에 다시 정렬
                if (row == 100) break;
                A[row++][c] = k;
                A[row++][c] = map.get(k);
            }

            for (int r = row; r < 100; r++) {        // 4. 줄어든 열 사이즈 만큼 0으로 채우기
                A[r][c] = 0;
            }

            maxColSize = Math.max(maxColSize, row);
        }

        return maxColSize;
    }
}
/*
3*3 배열A
->1초마다 배열에 연산 적용
->정렬: 수의 등장 횟수가 작은 순 > 수가 작은 순
->결과: (수, 수의 등장 횟수)
ex)
[3, 1, 1] -> 3이 1번, 1이 2번 -> (3, 1) > (1, 2) = [3, 1, 1, 2]
[3, 1, 1, 2] -> 3이 1번, 1이 2번, 2가 1번 -> (2, 1) > (3, 1) > (1, 2) = [2, 1, 3, 1, 1, 2]

    0 1 2
0 | 1 2 1
1 | 2 1 3
2 | 3 3 3
ex2) (1,2)가 1이 되는 최소 시간
rowCount = 3, colCount = 3
=> rowCount >= colCount, R연산 적용

0: [1, 2, 1] -> (2, 1) > (1, 2) = [2, 1, 1, 2]
1: [2, 1, 3] -> (1, 1) > (2, 1) > (3, 1) = [1, 1, 2, 1, 3 , 1]
2: [3, 3, 3] -> [3, 3]

   0 1 2 3 4 5
0| 2 1 1 2 0 0
1| 1 1 2 1 3 1
2| 3 3 0 0 0 0

rowCount = 3, colCount = 6, time = 1
 */