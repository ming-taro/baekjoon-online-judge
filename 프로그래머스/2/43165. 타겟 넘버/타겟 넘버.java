import java.util.*;

class Solution {
    private static Set<String> set = new HashSet<>();
    private static int N;
    private static int target;
    private static int[] numbers;
    
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        this.target = target;
        this.numbers = numbers;
        
        dfs(0, 0, "", 0);
        return set.size();
    }
    
    private static void dfs(int depth, int current, String text, int result) {
        if (depth == N) {
            if (result == target) {
                set.add(text);            
            }
            return;
        }
        
        for (int i = current; i < N; i++) {
            dfs(depth + 1, i + 1, text + "+" + numbers[i], result + numbers[i]);
            dfs(depth + 1, i + 1, text + "-" + numbers[i], result - numbers[i]);
        }
    }
}