import java.io.*;
import java.util.*;

public class Main {
    private static boolean[] numbers;
    private static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] cards = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        numbers = new boolean[1000001];  // 가지고 있는 카드 표시
        score = new int[1000001];

        for (int card: cards) {
            numbers[card] = true;
        }

        for (int card: cards) {
            checkCard(card);
        }

        for (int i = 0; i < N; i++) {
            System.out.print(score[cards[i]] + " ");
        }
    }

    private static void checkCard(int card) {
        if (card == 1) {
            return;
        }

        for (int i = 1; i * i <= card; i++) {
            if (card % i == 0) {   // 나눠 떨어지는 경우 -> 작은 수가 승리, 큰 수가 패배
                if (numbers[i]) {  // 가지고 있는 수 중 약수가 있다면 승리
                    score[i]++;
                    score[card]--; // 다른 카드에 의해 나눠 떨어지는 경우 패배
                }
                if (numbers[card / i] && i * i != card ) {
                    score[card / i]++;
                    score[card]--;
                }
            }
        }
    }
}