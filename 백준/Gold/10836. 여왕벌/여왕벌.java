import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int M = Integer.parseInt(st.nextToken()); // 가로/세로 크기
        int N = Integer.parseInt(st.nextToken()); // 날짜 수

        int[] left = new int[M];     // 첫번째 열의 행별 누적값 : (0, 0) ~> (M - 1, 0)
        int[] top = new int[M];      // 첫번째 행의 열별 누적값 : (0, 1) ~> (0, M - 1)

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            int row = M - 1;
            int col = 1;
            int value = 0;
            while (st.hasMoreTokens()) {
                int count = Integer.parseInt(st.nextToken());
                while (row >= 0 && count > 0) {
                    left[row--] += value;
                    count--;
                }
                while (col >= 1 && count > 0) {
                    top[col++] += value;
                    count--;
                }
                value++;
            }
        }

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < M; c++) {
                if (c == 0) System.out.print((left[r] + 1) + " ");
                else System.out.print((top[c] + 1) + " ");
            } System.out.println();
        }
    }
}
/*
[성장 규칙]
각 애벌레가 자라서 크기가 커지는 정도는 하루에 +0, +1, +2의 세 가지 중 하나다
ex) 예제1을 보면 2*2격자에 3일동안 애벌레가 성장한다
    첫번째 열과 첫번째 행은 주어진 입력대로 성장한다고 한다
    때문에 (2 + 2 - 1 = 3), 3개 칸((1,0)->(0,0)->(0,1)순서로 성장)이 입력에 대한 성장 대상이 된다

    첫째날을 보면 [1, 1, 1]만큼 성장한다고 한다
    @주의할 점은 입력만큼 자란다는게 아니다@
    (1,0)이 1만큼, (0,0)이 1만큼, (0,1)이 1만큼 자란다는 의미가 아니라
    성장 규칙대로 +0만큼 자라는 애벌레가 1마리, +1만큼 자라는 애벌레가 1마리, +2만큼 자라는 애벌레가 1마리라는 의미다

    따라서 입력에 따른 성장세를 배열로 보면
    r/c 0  1
    0  +1 +2
    1  +0

    이때 (1,1)의 성장세는 왼쪽, 왼쪽위, 위쪽 애벌레의 성장세 중 가장 큰 +2만큼이 되므로 최종 성장세는
    r/c 0  1
    0  +1 +2
    1  +0 +2

    따라서 첫째날 저녁의 애벌레 크기는
    r/c 0  1
    0   2  3
    1   1  3


[예제2번 분석]
1) 첫째날
첫번째 열 & 첫번째 행의 성장세 -> 나머지 애벌레의 성장세
            +1 +1 +2 +2     +1 +1 +2 +2
            +1  .  .  .     +1 +1 +2 +2
            +0  .  .  .  -> +0 +1 +2 +2
            +0  .  .  .     +0 +1 +2 +2

2) 둘째날
첫번째 열 & 첫번째 행의 성장세 -> 나머지 애벌레의 성장세
            +1 +1 +1 +2     +1 +1 +1 +2
            +1  .  .  .     +1 +1 +1 +2
            +1  .  .  .  -> +1 +1 +1 +2
            +1  .  .  .     +1 +1 +1 +2

3) 총 성장세 누적합 -> 결과
   +2 +2 +3 +4     3 3 4 5
   +2 +2 +3 +4     3 3 4 5
   +1 +2 +3 +4  -> 2 3 4 5
   +1 +2 +3 +4     2 3 4 5

[풀이]
(M-1, 0) ~> (0, M-1)까지의 성장세는 감소하지 않는다는 문제 조건에 따라 항상 커지는 값임을 알 수 있다
예를들어 다음의 배열을 보면
a b
c d
여기서 a,b,c는 d보다 먼저 성장하며 조건에 따라 c < a < b 이다
따라서 d의 성장세는 항상 b가 되고,
첫째 열과 첫째 행을 제외한 나머지 애벌레들은 항상 자신의 윗칸 애벌레의 성장세와 같은 크기만큼 자란다
 */