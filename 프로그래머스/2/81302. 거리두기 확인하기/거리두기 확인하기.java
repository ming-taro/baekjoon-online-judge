import java.util.*;

class Node{
    int row;
    int col;
    
    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
    private int N = 5;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            answer[i] = checkDistance(places[i]);
        }
        
        return answer;
    }
    
    private int checkDistance (String[] place) {
        List<Node> people = new ArrayList<>();
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (place[row].charAt(col) == 'P') {
                    people.add(new Node(row, col));
                }
            }
        }
        
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                Node left = people.get(i).col < people.get(j).col ? people.get(i) : people.get(j);
                Node right = left == people.get(i) ? people.get(j) : people.get(i);
                
                int distance = Math.abs(left.row - right.row) + Math.abs(left.col - right.col);
                
                if (!(distance > 2 
                    || distance <= 2 && isCorrect(left, right, place))) {
                    return 0;
                }
            }
        }
        return 1;
    }
    
    private boolean isCorrect(Node left, Node right, String[] place) {
        int[] dx = {-1, 0, 1, 0}; // 위, 오, 아, 왼
        int[] dy = {0, 1, 0, -1};
        
        /* XP
         * PX
         */
        if (left.row + dx[0] == right.row    
            && left.col + dy[1] == right.col
            && place[left.row - 1].charAt(left.col) == 'X' 
            && place[left.row].charAt(left.col + 1) == 'X') {
            return true;
        }
        
        /* PX
         * XP
         */
        if (left.row + dx[2] == right.row
          && left.col + dy[1] == right.col
          && place[left.row].charAt(left.col + 1) == 'X'
          && place[left.row + 1].charAt(left.col) == 'X') {// 아오
            return true;
        }
        
        /* PXP */
        if (left.row == right.row
           && left.col + 2 == right.col
           && place[left.row].charAt(left.col + 1) == 'X') {
            return true;
        }
        
        /* P
         * X
         * P
         */
        Node top = left.row < right.row ? left : right;
        Node bottom = top == left ? right : left;
        if (top.col == bottom.col 
            && top.row + 2 == bottom.row
            && place[top.row + 1].charAt(top.col) == 'X') {
            return true;
        }
        
        return false;
    }
}