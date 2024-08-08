import java.util.*;

class Solution {
    private static boolean[] visited;
    private static Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        int answer = 0;
        
        visited = new boolean[numbers.length()];
        dfs(numbers, "");
        
        for (int number: set) {
            if (isPrime(number)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        
        for (int n = 2; n <= Math.sqrt(number); n++) {
            if (number % n == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    private static void dfs(String numbers, String number) {
        if (!number.isBlank()) {
            set.add(Integer.parseInt(number));
        }
                
        for (int i = 0; i < numbers.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(numbers, number + numbers.charAt(i));
                visited[i] = false;
            }
        }
    }
}
/*
숫자가 적힌 문자열 numbers
만들 수 있는 소수 개수

*/