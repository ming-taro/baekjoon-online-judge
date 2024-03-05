import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        List<Integer> crane = new ArrayList<>();
        StringTokenizer input = new StringTokenizer(reader.readLine());
        while (input.hasMoreTokens()) {
            crane.add(Integer.parseInt(input.nextToken()));
        }

        int M = Integer.parseInt(reader.readLine());
        List<Integer> box = new ArrayList<>();
        input = new StringTokenizer(reader.readLine());
        while (input.hasMoreTokens()) {
            box.add(Integer.parseInt(input.nextToken()));
        }

        crane.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        if (crane.get(0) < box.get(0)) {   //가장 큰 크레인으로도 들 수 없는 상자가 있을 때
            System.out.println(-1);
            return;
        }

        int total = 0;

        while (!box.isEmpty()) {
            int boxIndex = 0;

            for (int i = 0; i < N; i++) {
                while (boxIndex < box.size()) {   //현재 크레인이 옮길 수 있는 상자중 가장 무거운 상자를 고름
                    if (box.get(boxIndex) <= crane.get(i)) {
                        break;
                    }
                    boxIndex++;
                }
                if (boxIndex == box.size()) {  //상자를 전부 옮김 -> 종료
                    break;
                }

                if (crane.get(i) >= box.get(boxIndex)) {
                    box.remove(boxIndex);    //옮긴 상자 삭제
                }
            }
            total++;
        }

        System.out.println(total);
    }
}