import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());

        long X = Integer.parseInt(st.nextToken());
        long Y = Integer.parseInt(st.nextToken());
        long W = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        long first = X * W + Y * W;        //직진으로만 이동
        long second = Math.min(X, Y) * S + (Math.abs(X - Y)) * W; //대각선 이동 + 직진 이동
        long third = (X + Y) % 2 == 0 ? Math.max(X, Y) * S : (Math.max(X, Y) - 1) * S + W; //ex) (4, 2) -> 대각선만으로 4만큼 이동 / (3, 2) -> 대각선으로 2 + 직선 1

        System.out.println(Math.min(Math.min(first, second), third));
    }
}