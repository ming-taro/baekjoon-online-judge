import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(reader.readLine());

        List<int[]> nodes = new ArrayList<>(); // [] = start, end
        for (int i = 0; i < X; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            nodes.add(new int[]{ start, end });
        }
        nodes.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        Queue<Node> availablePC = new PriorityQueue<>((o1, o2) -> o1.number - o2.number);  // 현재 사람이 사용 가능한 PC 목록 -> 생성 번호가 작은 순으로 배정함
        Queue<Node> candidate = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);          // 종료 시간이 늦어 현재 사람은 사용할 수 없지만, 다음 사람이 사용할 수도 있는 후보 PC 목록 -> 종료 시간 순으로 정렬
        int pcNumber = 0;

        for (int[] node: nodes) {          // 시작 시간이 빠른 사람 순으로 배정
            while (!candidate.isEmpty()) { // 이전 사람은 사용하지 못했지만, 현재 사람은 사용할 수도 있는 PC목록 -> availablePC로 옮겨오기
                if (candidate.peek().end <= node[0]) { // 후보 PC의 종료 시간보다 현재 사람의 시작 시간이 느림 -> PC 사용 가능
                    availablePC.offer(candidate.poll());
                } else {   // 후보 PC중에서 더 이상 사용 가능한 PC X
                    break;
                }
            }

            if (availablePC.isEmpty()) { // 현재 사람이 사용 가능한 PC가 없음 -> 새로운 PC 배정
                candidate.offer(new Node(pcNumber++, node[1], 1));
            } else { // 사용 가능 PC목록의 peek PC 사용(= 사용 가능한 PC중 가장 번호가 낮은 PC)
                Node pc = availablePC.poll();
                candidate.offer(new Node(pc.number, node[1], pc.count + 1));
            }
        }

        availablePC.addAll(candidate);
        int[] answer = new int[availablePC.size()];
        while (!availablePC.isEmpty()) {
            Node current = availablePC.poll();
            answer[current.number] = current.count;
        }

        System.out.println(answer.length);
        for (int count: answer) {
            System.out.print(count + " ");
        }
    }
}

class Node {
    int number;
    int end;
    int count;
    public Node(int number, int end, int count) {
        this.number = number;
        this.end = end;
        this.count = count;
    }
}