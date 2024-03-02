import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[] numbers = new int[N + 2];

        st = new StringTokenizer(reader.readLine());  //손상된 카약 팀
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());
            numbers[number] = -1;
        }

        int count = S;
        List<Integer> teams = new ArrayList<>();

        st = new StringTokenizer(reader.readLine());  //손상된 카약 팀
        while (st.hasMoreTokens()) {      //여분을 가져온 카약 팀
            int number = Integer.parseInt(st.nextToken());
            if (numbers[number] == -1) {  //우리 팀 카약이 손상되었는데 여분이 있다면, 다른 팀에게 빌리지 X -> 우리팀 여분은 우리가 씀
                numbers[number] = 0;
                count--;
            } else {
                teams.add(number);  //옆 팀에 나눠줄 여분이 있는 팀
            }
        }

        for (int number: teams) {
            if ( numbers[number - 1] == -1) {
                numbers[number - 1] = 0;
                count--;
            } else if (numbers[number + 1] == -1) {
                numbers[number + 1] = 0;
                count--;
            }
        }

        System.out.println(count);
    }
}
/*
5 2 2
3 4 
2 3
=> 1
 */