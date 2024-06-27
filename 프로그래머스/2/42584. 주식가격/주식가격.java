import java.util.*;

class Node {
    private int index;
    private int price;
    
    public Node(int index, int price) {
        this.index = index;
        this.price = price;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 1));
        
        for (int index = 0; index < prices.length; index++) {
            // queue.offer(new Node(index, prices[index]));
            // queue.offer(new Node(1, 1));
        }
        
        return answer;
    }
}
/*
return 가격이 떨어지지 않은 total sec

[1, 2, 3, 2, 3]
[0  1  2  3  4]
[   0  1  2  3]
[      0  1   ]
[            0]

[1, 2, 3, 2, 3]

[[i: 0, v: 1], [i: 1, v: 2], [i: 2, v: 3], [i: 3, v: 2]]

*/