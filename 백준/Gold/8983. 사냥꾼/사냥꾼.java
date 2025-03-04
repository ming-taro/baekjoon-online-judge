import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static long[] target;
    private static long L;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        long M = Long.parseLong(st.nextToken()); // 사대 수
        long N = Long.parseLong(st.nextToken()); // 동물 수
        L = Long.parseLong(st.nextToken()); // 사정거리

        target = Arrays.stream(reader.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();
        Arrays.sort(target);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            if (isValid(x, y)) {
//                System.out.println("!!통!!");
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static int calcLeft(long x, long y) {
        int left = 0;
        int right = target.length - 1;
        int mid = 0;

//        System.out.println("\nx = " + x + ", y = " + y);
//        System.out.println(Arrays.toString(target));
        while (left <= right) {
            mid = (left + right) / 2;
//            if (mid >= target.length) {
//                return target.length - 1;
//            }
//            System.out.println("left = " + left + ", right = " + right + ", mid = " + mid);
            if (target[mid] < x) {
                left = mid + 1;
//                System.out.println(target[mid] + " < " + x + " -> left = " + left);
            } else if (target[mid] > x) {
                right = mid - 1;
//                System.out.println(target[mid] + " > " + x + " -> right = " + right);
            } else {
//                System.out.println(target[mid] + " -> 완료");
                return mid;
            }
        }
        return left - 1;
    }

    private static boolean isValid(long x, long y) {
        int left = calcLeft(x, y);
//        System.out.println("최종 left = " + left + ", (x, y) = (" + x + ", " + y + ")");

        // y = x - (a - L)
        // y = -x + (a + L)
        if (left >= 0) {
            long positive = x - (target[left] - L);
            long negative = (-1) * x + (target[left] + L);
//            System.out.println("1=>" + positive + ", " + negative);
            if (target[left] - L <= x && x <= target[left] && positive >= y
                    || target[left] <= x && x <= target[left] + L && negative >= y) return true;
        }

        if (left + 1 < target.length) {
            long positive = x - (target[left + 1] - L);
            long negative = (-1) * x + (target[left + 1] + L);
//            System.out.println("2=>" + positive + ", " + negative);
            if (target[left + 1] - L <= x && x <= target[left + 1] && positive >= y
                    ||  target[left + 1] <= x && x <= target[left + 1] + L && negative >= y) return true;
        }

        return false;
    }
}
/*
(7, 2)위치에 동물이 있다
-> 음의 방정식 중 x가 가장 큰 것 vs 양의 방정식 중 x가 가장 작은 것
-> 두 방정식에 x넣었을 때 y보다 작거나 같으면 동물 사냥 성공
 */