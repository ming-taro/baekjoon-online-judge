import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        List<Integer>[] list = new ArrayList[words.length];
        for (int i = 0; i < words.length; i++) {
            list[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (isReplaceable(words[i], words[j])) {
                    list[i].add(j);
                    list[j].add(i);
                }
            }
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        
        for (int i = 0; i < words.length; i++) {
            if (isReplaceable(words[i], begin)) {
                queue.offer(new int[]{i, 1});
                visited[i] = true;
            }
        }
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            if (words[current[0]].equals(target)) {
                return current[1];
            }
            
            for(int next: list[current[0]]) {
                if (!visited[next]) {
                    queue.offer(new int[]{next, current[1] + 1});
                    visited[next] = true;
                }
            }
        }
         
        return answer;
    }
    
    private static boolean isReplaceable(String word, String target) {
        int count = 0;
        
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == target.charAt(i)) {
                count++;
            }
        }
        
        return count == word.length() - 1 ? true : false;
    }
}