import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int N = words.length;
        
        List<Integer>[] nodes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j && isValid(words[i], words[j])) {
                    nodes[i].add(j);
                    nodes[j].add(i);
                }
            }
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        int[] visited = new int[N];
        for (int i = 0; i < N; i++) {
            if (isValid(begin, words[i])) {
                queue.offer(i);
                visited[i] = 1;
            }
        }
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (words[current].equals(target)) {
                return visited[current];
            }
            
            for (int next: nodes[current]) {
                if (visited[next] == 0) {
                    visited[next] = visited[current] + 1;
                    queue.offer(next);
                }
            }
        }
        
        
        return answer;
    }
    
    private static boolean isValid(String word, String target) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                count++;
            }
        }
        
        return count == 1 ? true : false;
    }
}