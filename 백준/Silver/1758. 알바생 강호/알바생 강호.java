import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Integer[] money = new Integer[n];
        long tip = 0;

        for (int index = 0; index < n; index++) {
            money[index] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(money, Collections.reverseOrder());

        for (int index = 0; index < n; index++){
            if (money[index] - index > 0) {
                tip += money[index] - index;
            }
        }

        bw.write(tip + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

//8시: 손님은 모두 입구에서 커피 1잔을 받음. 강호는 입구에서 커피를 하나씩 줌
//손님: 입장시 강호에게 팁 = 원래 주려고 한 돈 - 위치 인덱스 => 음수일 경우 팁을 받을 수 X