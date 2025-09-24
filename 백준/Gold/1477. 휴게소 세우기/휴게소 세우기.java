import java.io.*;
import java.util.*;

class Main {
    private static int N, M, L;
    private static int[] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken()); // 휴게소 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려는 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로 길이

        distance = new int[N + 2];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            distance[i] = Integer.parseInt(st.nextToken());  // 현재 휴게소 위치
        }
        distance[N] = 0;      // distance는 휴게소가 이미 세워진, 즉 설치할 수 없는 위치를 나타내므로
        distance[N + 1] = L;  // 시작 지점인 0과 마지막 지점인 L을 추가함
        Arrays.sort(distance);

        System.out.println(search());
    }

    private static int search() {
        int left = 0;
        int right = L;

        while (left + 1 < right) {
            int mid = (left + right) / 2; // 휴게소간 최대 거리
            int count = 0;
            for (int i = 1; i < distance.length; i++) {
                count += (distance[i] - distance[i - 1] - 1) / mid;
            }

            if (count > M) { // 설치 가능 개수를 넘음 -> 휴게소를 더 넓게 설치해야 함
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
/*
예제1)
0 82 201 411 555 622 755 800
(1) 0 ~ 82 : 구간 길이 = 81 / 71 위치에 휴게소 1개 설치 -> 최대 구간 길이가 70이 됨
(2) 82 ~ 201 : 구간 길이 = 119 / 152 위치에 휴게소 1개 설치 -> 최대 구간 길이가 70이 됨
(3) 201 ~ 411 : 구간 길이 = 210 / 271, 341 위치에 휴게소 2개 설치 -> 최대 구간 길이가 70이 됨
                이때 210(구간 길이) / 70(최대 길이) = 3으로 3개를 설치 해야할 것 같지만
                341의 다음 위치인 411은 이미 기존의 휴게소가 설치된 위치이다
                즉, 202부터 410구간 내에서만 설치 가능하기 때문에 (410 - 202) + 1 = 209
                쉽게 말하면 두 구간의 차인 210보다 1만큼 작은 209 / 70 = 2로 2개의 휴게소를 설치해야
                최대 구간의 길이가 70이 됨을 구할 수 있다
(4) 411 ~ 555 : 구간 길이 = 144 / 481, 551 위치에 휴게소 2개 설치
(5) 555 ~ 622 : 구간 길이 = 67 / 70보다 이미 작으므로 휴게소 미설치
(6) 622 ~ 755 : 구간 길이 = 133 / 692 위치에 휴게소 1개 설치
(7) 755 ~ 800 : 구간 길이 45 / 70보다 이미 작으므로 휴게소 미설치

=> 총 1 + 1 + 2 + 2 + 0 + 1 + 0 = 7개 설치로 구간의 최대 길이가 70이 됨
 */