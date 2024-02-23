import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[] number;
    private static int[] operator;
    private static int minResult = Integer.MAX_VALUE;
    private static int maxResult = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(reader.readLine());

        number = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        operator = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        calcNumber(1, number[0]);

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    private static void calcNumber(int numberIndex, int result) {
        if (numberIndex == N) { //계산 완료
            minResult = Math.min(minResult, result);
            maxResult = Math.max(maxResult, result);
            return;
        }

        for (int i = 0; i < 4; i++) { //+->+, +->-, +->*, +->// 처럼 operator개수 내에서 한 연산자 다음에 4가지가 모두 오는 경우를 생각해야 하기 때문에 재귀를 돌릴 때 for()문으로 4가지 연산자가 오는 모든 경우를 고려해야 함
            if (operator[i] == 0) {  //연산자가 없는 경우 다음 연산자로
                continue;
            }

            operator[i]--;

            if (i == 0) {
                calcNumber(numberIndex + 1, result + number[numberIndex]);
            } else if (i == 1) {
                calcNumber(numberIndex + 1, result - number[numberIndex]);
            } else if (i == 2) {
                calcNumber(numberIndex + 1, result * number[numberIndex]);
            } else {
                calcNumber(numberIndex + 1, result / number[numberIndex]);
            }

            operator[i]++;
        }
    }
}