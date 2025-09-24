import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 공유기의 개수
        int[] house = new int[N]; // 집 좌표

        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(house);

        System.out.println(search(N, C, house));
    }

    private static int search(int N, int C, int[] house) {
        int left = 0;
        int right = house[N - 1] + 1;

        while (left + 1 < right) {
            int mid = (left + right) / 2;  // 공유기 설치 간격
            int count = 1;                 // 공유기 설치 개수 -> 첫번째 집에 설치하고 시작
            int current = 0;
            for (int i = 1; i < N; i++) {
                if (house[i] - house[current] >= mid) { // 현재 위치에 공유기 설치
                    current = i;
                    count++;
                }
            }

            if (count < C) { // 설치 개수 미달 -> 간격 좁혀서 촘촘히 설치
                right = mid;
            } else {
                left = mid;
            }
        }

        return left;
    }
}
/*
mid = 공유기 간 최대 거리
첫번째 집에 무조건 공유기 설치 -> 최대한 멀리 설치하려면 첫집, 마지막 집에 공유기가 설치되게 되어있음
                           0, 1, 2, 3, 4 집과 공유기 3개를 설치하는 경우
                           0, 2, 3집에 공유기를 설치한 경우보다 0, 2, 4집에 공유기를 설치해야 거리가 더 넓어짐
                           따라서 첫번째 집에 공유기가 있다고 가정하고 탐색 진행

while -> 이전 공유기 위치와 현재 집 위치 비교 -> mid 거리인 경우 설치
                                         mid보다 커도 설치 가능하므로 설치
                                         mid보다 작으면 설치 불가하므로 넘김
         count가 C를 초과하면 거리를 더 넓혀야 하므로 left = mid / else right = mid
 */