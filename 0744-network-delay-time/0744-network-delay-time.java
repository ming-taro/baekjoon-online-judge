class Node {
    int num;
    int time;
    public Node(int num, int time) {
        this.num = num;
        this.time = time;
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        List<Node>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] time: times) {
            nodes[time[0]].add(new Node(time[1], time[2]));
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(k);

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Node next: nodes[current]) {
                int value = distance[current] + next.time;
                if (value < distance[next.num]) {
                    distance[next.num] = value;
                    queue.offer(next.num);
                }
            }
        }        

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            answer = Math.max(answer, distance[i]);
        }

        return answer;
    }
}