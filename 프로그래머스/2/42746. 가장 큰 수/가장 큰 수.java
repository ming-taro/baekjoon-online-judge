import java.util.*;

class Node implements Comparable<Node>{
    int index;
    int size;
    int number;
    int beforeNumber;
    
    public Node(int index, int size, int number, int beforeNumber) {
        this.index = index;
        this.size = size;
        this.number = number;
        this.beforeNumber = beforeNumber;
    }
    
    @Override
    public int compareTo(Node node) {
        int first = Integer.toString(this.number).charAt(0) - '0';
        int second = Integer.toString(node.number).charAt(0) - '0';
        
        if (this.number == node.number) {
            int f = this.beforeNumber*(int)Math.pow(10, node.size) + node.beforeNumber;
            int s = node.beforeNumber*(int)Math.pow(10, this.size) + this.beforeNumber;
            
            return s - f;
        }
        
        if (first == second) {
            return node.number - this.number;
        }
        
        return second - first;
    }
}

class Solution {
    private static boolean[] visited;
    private static String result;
    private static List<Node> nodes;
    
    public String solution(int[] numbers) {
        String answer = "";
        int maxLength = 0;
        
        for (int number: numbers) {
            maxLength = Math.max(maxLength, Integer.toString(number).length());
        }
        
        nodes = new ArrayList<>();
        
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            // int repeat = number % 10;
            int repeat = Integer.toString(number).charAt(0) - '0';
            int loop = maxLength - Integer.toString(number).length();
            
            for (int j = 0; j < loop; j++) {
                number = number * 10 + repeat;
            }
            nodes.add(new Node(i, Integer.toString(numbers[i]).length(), number, numbers[i]));
        }
        
        Collections.sort(nodes);

        // for (int i = 0; i < numbers.length; i++) {
        //     System.out.println(nodes.get(i).number + "\t" + numbers[nodes.get(i).index]);
        // }
        
        for (Node node: nodes) {
            answer += Integer.toString(numbers[node.index]);
        }
        
        if (answer.charAt(0) == '0') {
            return "0";
        }
        
        return answer;
    }
}
/*
89/898/818/81/1/10/100/1000/0/0

818 > 81
818 == 818
818 > 811

89 > 898
898 == 898
121312 > 12
1 > 10 > 100 > 1000
110
101
1100 
*/