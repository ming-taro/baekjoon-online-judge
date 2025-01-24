import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());

        int[][] point = new int[N][2];

        for(int i =  0 ; i < N ; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(point, (o1, o2) -> {
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < N; i++) {
            System.out.println(point[i][0] + " " + point[i][1]);
        }
    }
}