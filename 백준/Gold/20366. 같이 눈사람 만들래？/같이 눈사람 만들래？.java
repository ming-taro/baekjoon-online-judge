import java.io.*;
import java.util.*;

class Node {
    long value;
    int[] index;
    public Node (long value, int[] index) {
        this.value = value;
        this.index = index;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine()); // 눈덩이 개수
        long[] snow = new long[N];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            snow[i] = Long.parseLong(st.nextToken());
        }

        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                list.add(new Node(snow[i] + snow[j], new int[]{ i, j }));
            }
        }

        list.sort(((o1, o2) -> (int)(o1.value - o2.value)));

        /*
        list = 눈덩이의 모든 합 조합 + 사용한 눈 덩이 2개의 인덱스 위치
        list를 눈덩이합이 작은 순으로 정렬, 바로 다음으로 합이 작은 눈사람 즉 바로 뒤의 눈사람과 비교하기
        (i번째 눈사람은 현재 지름 합이 가장 작은 눈사람)
        *) i번째 눈사람과 (i+1)번째 눈사람의 눈덩이가 겹친다면?
           ->눈사람 2개를 만들 수 없으므로 바로 다음 조합 찾기
           ->(i+2)번째 눈사람과 비교해야 하는데, list는 높이가 작은 순으로 정렬되어 있기 때문에 i번째 눈사람보다 (i+1)번째 눈사람과 (i+2)번째 눈사람의 차이가 더 작음
           ->결국 (i)번째 눈사람과 (i+1)번째 눈사람이 불가하면 다음으로 가능성 있는 후보는 (i+1)번째 눈사람과 (i+2)번째 눈사람
           ->따라서 i번째와 (i+1), (i+2)..를 비교할 필요 없이 (i)와 (i+1), (i+1)과 (i+2)번째 눈사람을 비교해 나가면 됨
         */
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            if (isValid(list.get(i), list.get(i + 1))) {
                answer = Math.min(answer, Math.abs(list.get(i).value - list.get(i + 1).value));
            }
        }
        System.out.println(answer);
    }

    private static boolean isValid(Node node1, Node node2) {
        if (node1.index[0] == node2.index[0] || node1.index[0] == node2.index[1]
                || node1.index[1] == node2.index[0] || node1.index[1] == node2.index[1]) return false;
        return true;
    }
}
/*
N개의 눈덩이, 지름 H
눈사람 = 2개의 눈덩이(아래 > 위)
눈사람 키 = 2개 눈동이 지름 합
->N개의 눈덩이 중 서로 다른 4개를 골라 눈사람 2개 만들기
return 두 눈사람 키 차이의 최솟값

6
1 6 7 28 28 50
 */