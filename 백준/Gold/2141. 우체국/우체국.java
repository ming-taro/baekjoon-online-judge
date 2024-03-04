import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    long number;
    long people;

    public Node(long number, long people) {
        this.number = number;
        this.people = people;
    }

    @Override
    public int compareTo(Node node) {
        return (int) (this.number - node.number);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        List<Node> village = new ArrayList<>();

        long total = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            village.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            total += village.get(i).people;
        }

        Collections.sort(village);

        long left = 0;
        long right = total;

        long minDifference = total;
        long number = village.get(0).number;

        for (Node current: village) {  //왼쪽 오른쪽 사람 수의 균형이 알맞은 위치가 우체국의 위치
            right -= current.people;
            if (Math.abs(right - left) < minDifference) {
                number = current.number;
                minDifference = Math.abs(right - left);
            }
            if (left > right) {
                break;
            }
            left += current.people;
        }

        System.out.println(number);
    }
}