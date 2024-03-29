import java.util.*;

class Solution {
    private static String[] data;
    private static boolean[] visited;
    private static Map<Character, Integer> indexs;
    private static Character[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static int total;
    
    public int solution(int n, String[] data) {
        this.data = data;
        
        visited = new boolean[8];
        indexs = new HashMap<>();
        total = 0;
        
        dfs(0);
        
        return total;
    }
    
    private static void dfs(int index) {
        if (index == 8) {
            if (isPossibleOrder()) {
                total++;
            }
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                indexs.put(friends[index], i);
                dfs(index + 1);
                visited[i] = false;
            }
        }
    }
    
    private static boolean isPossibleOrder() {
        for (String order: data) {
            int firstIndex = indexs.get(order.charAt(0));
            int secondIndex = indexs.get(order.charAt(2));

            int difference = Math.abs(firstIndex - secondIndex) - 1;
            int standard = Character.getNumericValue(order.charAt(4));

            if (order.charAt(3) == '=' && difference != standard
               || order.charAt(3) == '>' && difference <= standard
               || order.charAt(3) == '<' && difference >= standard) {
                return false;
            }
        }
        return true;
    }
}