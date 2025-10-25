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
/*
4
1 10
2 6
3 5
7 9

사용자 배정 순서 : PC 시작 시간이 빠른 사람 순
사용할 큐 2개 : 사용 가능한 PC 우선순위큐(availablePC), 후보PC 우선순위큐(candidate)
             ->availablePC는 번호가 낮은 순서대로 정렬한다
             ->후보PC는 종료시각이 빠른 순서대로 정렬한다

1) [1, 10]
- availablePC가 없으므로 기존 PC재사용 불가 -> 새로 배정받은 PC = [번호 0번, 종료 10, 이용자 1명]
- 새로 배정받은 PC는 2번 사용자를 위해 후보PC 목록에 넣는다
  (2번 사용자가 현재 PC를 사용할 수 있을 지는 2번 사용자 차례에 검사해야 하므로 availablePC가 아닌 후보PC에 담아야 한다)

>>현재 우선순위 큐의 상황<<
availablePC = []
candidate = [[0, 10, 1]]

2) [2, 6]
- 후보PC 중에 2번 사용자가 사용 가능한 PC가 없다
  **뒤에 오는 사용자들은 모두 2번보다 PC시작 시간이 큰 사람들이다
    때문에 2번 사용자가 사용가능한 PC들은 당연히 뒤의 사용자들도 사용 가능하기 때문에 availablePC에 계속 누적해서 사용할 수 있는 것이다**
- 새로 배정받은 PC = [번호 1번, 종료 6, 이용자 1명]
  (새로 배정받은 PC는 3번 사용자를 위해 후보PC 목록에 넣는다)

>>현재 우선순위 큐의 상황<<
availablePC = []
candidate = [[0, 10, 1], [1, 6, 1]]

3) [3, 5]
- 후보PC 중에 3번 사용자가 사용 가능한 PC가 없다
- 새로 배정받은 PC = [번호 2번, 종료 5, 이용자 1명]
  (새로 배정받은 PC는 4번 사용자를 위해 후보PC 목록에 넣는다)
  
>>현재 우선순위 큐의 상황<<
availablePC = []
candidate = [[0, 10, 1], [1, 6, 1], [2, 5, 1]]

4) [7, 9]
- 후보PC 중에 4번 사용자가 사용 가능한 PC는 [1, 6, 1], [2, 5, 1] 2대이므로 availablePC 목록으로 옮긴다

>>현재 우선순위 큐의 상황<<
availablePC = [[1, 6, 1], [2, 5, 1]]
candidate = [[0, 10, 1]]

- availablePC 중 번호가 가장 낮은 1번 PC를 이어서 사용한다
  [1, 6, 1]의 정보는 [1, 9, 2]로 갱신된다
- 이때 PC정보가 갱신되었기 때문에 이전의 [1, 6, 1]정보였다면 5번 사용자도 사용 가능한 PC였지만,
  4번 사용자가 사용함으로써 PC종료시간이 9로 바뀌었기 때문에 5번 사용자의 시작 시간에 따라 가능할지 검사해야 한다
  때문에 갱신된 PC는 availablePC 목록에서 제거하고 다시 candidate로 옮겨야 한다
  
>>현재 우선순위 큐의 상황<<
availablePC = [[2, 5, 1]]
candidate = [[0, 10, 1], [1, 9, 2]]
 */