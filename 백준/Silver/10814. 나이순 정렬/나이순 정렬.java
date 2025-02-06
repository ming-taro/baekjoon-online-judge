import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Node {
    int index;
    int age;
    String name;
    public Node(int index, int age, String name) {
        this.index = index;
        this.age = age;
        this.name = name;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        Node[] member = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            member[i] = new Node(i, Integer.parseInt(st.nextToken()), st.nextToken());
        }

        Arrays.sort(member, ((o1, o2) -> {
            if (o1.age == o2.age) {
                return o1.index - o2.index;
            }
            return o1.age - o2.age;
        }));

        for (Node node: member) {
            System.out.println(node.age + " " + node.name);
        }
    }
}