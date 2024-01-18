import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[] isBrokenButton = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        if (M > 0) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < M; i++) {
                isBrokenButton[Integer.parseInt(stringTokenizer.nextToken())] = true;
            }
        }

        //1. +, -버튼 만으로 채널을 이동 하는 경우
        int minCount = Math.abs(100 - N);

        //2. 근접 숫자까지 이동한 후 +, -버튼으로 나머지를 이동하는 경우
        for (int number = 0; number <= 1_000_000; number++) { //채널이 500_000이고 버튼이 1, 0만 가능한 경우 1_000_000까지 만들 수 있으므로 최대 채널 기준점을 1_000_000으로 설정
            if (isCorrectNumber(Integer.toString(number))) {     //고장나지 않은 버튼들로 이루어진 채널
                int count = Integer.toString(number).length() + Math.abs(N - number);  //버튼 누른 후 + 수동으로 이동
                minCount = Math.min(minCount, count);
            }
        }

        System.out.println(minCount);
    }

    private static boolean isCorrectNumber(String number) {
        //해당 로직을 숫자값으로 10단위씩 나누어 계산하는 경우 number가 0일 때 무조건 true를 반환함. 이때 0이 고장난 버튼인 경우 문제가 발생 -> 문자열로 확인할것
        for (int i = 0; i < number.length(); i++) {
            if (isBrokenButton[Character.getNumericValue(number.charAt(i))]) {
                return false;
            }
        }
        return true;
    }
}