class Node {
    int num;
    double value;
    public Node(int num, double value) {
        this.num = num;
        this.value = value;
    }
}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<Node>[] nodes = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            nodes[edges[i][0]].add(new Node(edges[i][1], succProb[i]));
            nodes[edges[i][1]].add(new Node(edges[i][0], succProb[i]));
        }

        double[] distance = new double[n];
        distance[start_node] = 1.0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start_node);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Node next: nodes[current]) {
                double value = distance[current] * next.value;
                if (value > distance[next.num]) {
                    distance[next.num] = value;
                    queue.offer(next.num);
                }
            }
        }

        return distance[end_node];
    }
}