import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        Item[] items = new Item[N + 1];
        items[0] = new Item(0, 0);

        for (int i = 1; i <= N; i++) {
            input = reader.readLine().split(" ");
            int weight = Integer.parseInt(input[0]);
            int value = Integer.parseInt(input[1]);
            items[i] = new Item(weight, value);
        }

        int[][] bags = new int[N + 1][K + 1];  //무게를 1씩 증가해 나가면서 각 아이템을 넣었을 때의 최고 가치를 저장

        for (int bagWeight = 1; bagWeight <= K; bagWeight++) {
            for (int itemIndex = 1; itemIndex <= N; itemIndex++) {
                bags[itemIndex][bagWeight] = bags[itemIndex - 1][bagWeight];

                if (items[itemIndex].weight <= bagWeight) {
                    int firstBagValue = bags[itemIndex - 1][bagWeight - items[itemIndex].weight];   //bags[itemIndex - 1]의 가치를 비교하는 이유는 bags[itemIndex]를 포함하기 전의 가치를 구하기 위함(bag[itemIndex]는 이제 연산할 것이기 때문)
                    int secondBagValue = items[itemIndex].value;

                    bags[itemIndex][bagWeight] = Math.max(firstBagValue + secondBagValue, bags[itemIndex][bagWeight]);
                }
            }
        }

        System.out.println(bags[N][K]);
    }

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}

/*
         0 1 2 3 4 5 6 7
(6, 13)  0 0 0 0 0 0 13 13
(4, 8)   0 0 0 0 8 8 13 13
(3, 6)   0 0 0 6 8 8 13 14
(5, 12)  0 0 0 6 8 12 13 14
*/