class Node {
    int num;
    int value;
    public Node(int num, int value) {
        this.num = num;
        this.value = value;
    }
}

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int answer = 0;
        List<Node>[] nodes = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] time: times) {
            nodes[time[0]].add(new Node(time[1], time[2]));
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(k, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (Node next: nodes[current.num]) {
                int value = distance[current.num] + next.value;
                if (value < distance[next.num]) {
                    distance[next.num] = value;
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            answer = Math.max(answer, distance[i]);
        }
        return answer;
    }
}