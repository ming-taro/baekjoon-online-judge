import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] count = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            count[i] = value - count[i];
        }

        boolean go = count[0] < 0 ? false : true;
        int answer = Math.abs(count[0]);

        for (int i = 1; i < N; i++) {
            if (count[i] > 0 && !go || count[i] < 0 && go) { // 방향 전환
                answer += Math.abs(count[i]);
                go = !go;
            } else {
                if (Math.abs(count[i - 1]) < Math.abs(count[i])) {
                    answer += Math.abs(count[i]) - Math.abs(count[i - 1]);
                }
            }
        }

        System.out.println(answer);
    }
}
/*
1. 방향이 전환되는 경우
ex) +2, -3
이전 움직임인 2는 더이상 앞의 인덴트에 영향을 줄 수 X -> 3칸 뒤로감
현재 가장 큰 움직임은 뒤로3칸

2. 방향이 그대로 가는 경우
2-1. 값이 전보다 큰 경우
ex) +2, +3
앞보다 더 움직여야 함 -> 현재 3에서 이전 움직임인 2를 뺀 1칸 앞으로 감
현재 가장 큰 움직임은 앞으로 3칸

2-2. 값이 전보다 작은 경우
+3, +2
앞에 움직일때 같이 움직이면 됨
현재 가장 큰 움직임은 앞으로 2칸

 */