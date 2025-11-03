import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int M;
    private static int L;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(st.nextToken()); // 자르는 횟수
        M = Integer.parseInt(st.nextToken()); // 자를 수 있는 지점 개수
        L = Integer.parseInt(st.nextToken()); // 롤 케이크 길이

        int[] part = new int[M + 1];
        for (int m = 1; m <= M; m++) {
            part[m] = Integer.parseInt(reader.readLine());
        }

        int[][][] dp = new int[2][M + 1][M + 1];      // 0 = 왼쪽 결과, 1 = 오른쪽 결과 // [1 ~ N개 구간][1 ~ N횟수]
        for (int i = 1; i <= M; i++) {                 // i 구간 기준 왼쪽 검사(현재 구간은 무조건 자름)
            dp[0][i][1] = part[i];                    // 1컷 최소 = 현재 구간에서 자르고 남은 왼쪽 부분
            for (int j = 1; j < i; j++) {
                for (int k = 1; k <= j; k++) {    // j에서의 최대 컷 가능 수는 j
                    int newCount = Math.min(dp[0][j][k], part[i] - part[j]); // 앞 구간 j에서 k컷 한 경우 + 현재 구간 자름 = 구간 i에서 (k + 1)회 컷한 경우
                    if (dp[0][i][k + 1] == 0) dp[0][i][k + 1] = newCount;
                    else dp[0][i][k + 1] = Math.max(dp[0][i][k + 1], newCount);
                }
            }
        }

        for (int i = M; i >= 1; i--) {       // 가장 오른쪽 구간부터 역으로 순회
            dp[1][i][1] = L - part[i];       // 1컷 최소 = 현재 구간에서 자르고 남은 오른쪽 부분
            for (int j = M; j > i; j--) {    // i < j
                for (int k = 1; k <= M - j + 1; k++) {   // j구간에서의 최대 컷 가능 수는 M - j + 1
                    int newCount = Math.min(dp[1][j][k], part[j] - part[i]);
                    if (dp[1][i][k + 1] == 0) dp[1][i][k + 1] = newCount;
                    else dp[1][i][k + 1] = Math.max(dp[1][i][k + 1], newCount);
                }
            }
        }


        StringBuilder answer = new StringBuilder();
        for (int n = 0; n < N; n++) {
            int count = Integer.parseInt(reader.readLine()); // 자를 횟수
            int currentAnswer = 0;
            for (int i = 1; i <= M; i++) {
                for (int c = 1; c <= count; c++) { // 구간 i에서 자를 때 왼쪽 c번 + 오른쪽 (count - c + 1) 결과값
                    currentAnswer = Math.max(currentAnswer, Math.min(dp[0][i][c], dp[1][i][count - c + 1]));
                    currentAnswer = Math.max(currentAnswer, Math.min(dp[1][i][c], dp[0][i][count - c + 1]));
                }
            }
            answer.append(currentAnswer + "\n");
        }

        System.out.println(answer.toString());
    }
}
/*
롤케이크 -> 특정 위치에서만 자를 수 있음
컷 횟수 = 3
길이 = 70
지점 = [10, 20, 35, 55, 60]

<왼쪽 방향 검사>
10에서 잘랐을 때 -> 1번 자름(10 지점) : 최소 길이 조각 = 10
20에서 잘랐을 때 -> 1번 자름(20 지점) : 최소 길이 조각 = 20
                 2번 자름(10, 20 지점) : 최소 길이 조각 = 10
35에서 잘랐을 때 -> 1번 자름(35 지점) : 최소 길이 조각 = 35
                 2번 자름(20, 35 지점) : 최소 길이 조각 = 15
                 3번 자름(10, 20, 35 지점) : 최소 길이 조각 = 10
55에서 잘랐을 때 -> 1번 자름(55 지점) : 최소 길이 조각 = 55
                 2번 자름(20, 55 지점) : 최소 길이 조각 = 20
                 3번 자름(20, 35, 55 지점) : 최소 길이 조각 = 15
...
a지점에서 잘랐을 때 포함 + a지점까지의 컷 횟수가 b인 경우의 최소 길이 조각
= 앞 지점들 중 목표 컷 횟수보다 1만큼 작은 경우들 중 최대

ex) 35에서 잘랐을 때 1번 자른 횟수 : 35 그대로
                  2번 자른 횟수 : min(dp[0][20][1] = 20, 35 - 20) = 15
                  3번 자른 횟수 : min(dp[0][20][2] = 10, 35 - 20) = 15
    단, dp가 0이 아닌 값일 때만 비교한다

<dp[0]>
구간   ➡️자른 횟수
⬇️     0  1  2  3  4  5
    0  0 10  0  0  0  0
    1  0 20 10  0  0  0
    2  0 35 15 10  0  0
    3  0 55 20 15 10  0
    4  0 60 25 15 10  5


dp[d][a][b] = d방향(왼 or 오)까지 검사 중 a지점까지 중 b번 잘랐을 때 가장 작은 조각의 길이 저장
b1 + b2 - 1 = N회인 경우 찾기 (a지점에서 각각 1번씩 잘랐다고 보기 때문에 중복 1회 빼줌)

<오른쪽 방향 검사>
60에서 잘랐을 때 -> 1번 자름(60 지점) : 최소 길이 조각 = 10
55에서 잘랐을 때 -> 1번 자름(55 지점) : 최소 길이 조각 = 15
                 2번 자름(55, 60 지점) : 최소 길이 조각 = 5
35에서 잘랐을 때 -> 1번 자름(35 지점) : 최소 길이 조각 = 25
                 2번 자름(35, 55 지점) : 최소 길이 조각 = 15
                 3번 자름(35, 55, 60 지점) : 최소 길이 조각 = 5
...


*) 3번 잘랐을 때
min(dp[0][35][2] dp[1][35][2]) = min(15, 15) = 15    => [20, 35, 55] 지점 컷
min(dp[0][55][3], dp[1][55][2]) = min(15, 15) = 15
 */