import java.util.*;

class Node implements Comparable<Node>{
    String number;
    
    public Node(String number) {
        this.number = number;
    }
    
    @Override
    public int compareTo(Node node) {
        int first = this.number.charAt(0) - '0';
        int second = node.number.charAt(0) - '0';
        
        if (first == second) {
            long num1 = Long.parseLong(this.number + node.number);
            long num2 = Long.parseLong(node.number + this.number);
            return (int) (num2 - num1);
        }
        
        return second - first;
    }
}

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        List<Node> nodes = new ArrayList<>();
        
        for (int i = 0; i < numbers.length; i++) {
            nodes.add(new Node(Integer.toString(numbers[i])));
        }
        
        Collections.sort(nodes);

        for (Node node: nodes) {
            answer.append(node.number);
        }
        
        if (answer.charAt(0) == '0') {
            return "0";
        }
        
        return answer.toString();
    }
}