import javax.management.loading.MLet;
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken()); // 벨트 위 초밥 접시 개수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹을 접시 개수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] dish = new int[N];

        for (int i = 0; i < N; i++) {
            dish[i] = Integer.parseInt(reader.readLine());
        }

        Map<Integer, Integer> sushi = new HashMap<>();
        for (int i = 0; i < k; i++) {
            if (sushi.containsKey(dish[i])) {
                sushi.put(dish[i], sushi.get(dish[i]) + 1);
            } else {
                sushi.put(dish[i], 1);
            }
        }

        int answer = sushi.size() + (sushi.containsKey(c) ? 0 : 1);
        int current = k; // 추가 대상
        int prev = 0;    // 삭제 대상

        for (int i = 0; i < N; i++) { // 벨트가 한 바퀴 회전할 동안
            if (sushi.get(dish[prev]) == 1) {       // 1. 맨 앞 초밥 삭제
                sushi.remove(dish[prev]);
            } else {
                sushi.put(dish[prev], sushi.get(dish[prev]) - 1);
            }

            if (sushi.containsKey(dish[current])) { // 2. 맨 뒤에 새로운 초밥 추가
                sushi.put(dish[current], sushi.get(dish[current]) + 1);
            } else {
                sushi.put(dish[current], 1);
            }

            answer = Math.max(answer, sushi.size() + (sushi.containsKey(c) ? 0 : 1)); // 3. 쿠폰 초밥 추가

            current = (current + 1) % N;
            prev = (prev + 1) % N;
        }

        System.out.println(answer);
    }
}