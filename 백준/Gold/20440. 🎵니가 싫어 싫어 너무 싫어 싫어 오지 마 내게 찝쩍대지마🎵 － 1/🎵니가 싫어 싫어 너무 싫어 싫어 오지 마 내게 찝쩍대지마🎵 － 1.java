import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[][] range = new int[N][2];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            range[i][0] = Integer.parseInt(st.nextToken());
            range[i][1] = Integer.parseInt(st.nextToken());
            map.put(range[i][0], map.getOrDefault(range[i][0], 0) + 1);
            map.put(range[i][1], map.getOrDefault(range[i][1], 0) - 1);
        }

        ArrayList<Integer> timeList = new ArrayList<>(map.keySet());
        timeList.sort((o1, o2) -> o1 - o2);

        int[] answer = { 0, 0 };
        int maxCount = 0;
        int count = 0;
        boolean flag = false;

        for (int time: timeList) {
            count += map.get(time); // 현재 모기 수

            if (count > maxCount) {
                maxCount = count;
                answer[0] = time;
                flag = true;
            } else if (count < maxCount && flag) {
                answer[1] = time;
                flag = false;
            }
        }

        System.out.println(maxCount);
        System.out.println(answer[0] + " " + answer[1]);
    }
}
