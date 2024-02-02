import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Home implements Comparable<Home>{
        long number;
        long people;

        public Home(long number, long people) {
            this.number = number;
            this.people = people;
        }

        @Override
        public int compareTo(Home o) {
            return (int) (number - o.number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int N = Integer.parseInt(reader.readLine());

        ArrayList<Home> distance = new ArrayList<>();
        long totalPeople = 0;

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(reader.readLine());
            long X = Integer.parseInt(stringTokenizer.nextToken());
            long A = Integer.parseInt(stringTokenizer.nextToken());

            distance.add(new Home(X, A));
            totalPeople += A;
        }

        Collections.sort(distance);
        long sum = 0;

        for (Home home: distance) {
            sum += home.people;
            if (sum >= (double) totalPeople / 2.0) {
                System.out.println(home.number);
                return;
            }
        }
    }
}