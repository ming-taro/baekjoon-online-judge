import java.util.*;

class Solution {
    private static Set<Character>[] prev = new HashSet[26];
    private static Set<Character>[] next = new HashSet[26];
    
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < 26; i++) {
            prev[i] = new HashSet<>();
            next[i] = new HashSet<>();
        }
        
        for (int i = 1; i < skill.length(); i++) {
            int current = skill.charAt(i) - 'A';
            prev[current].addAll(prev[skill.charAt(i - 1) - 'A']);
            prev[current].add(skill.charAt(i - 1));
        }
        
        for (int i = skill.length() - 2; i >= 0; i--) {
            int current = skill.charAt(i) - 'A';
            next[current].addAll(next[skill.charAt(i + 1) - 'A']);
            next[current].add(skill.charAt(i + 1));
        }
        
        for (String tree: skill_trees) {
            if (isValid(tree)) answer++;
        }
        
        return answer;
    }
    
    private static boolean isValid(String skill) {
        for (int i = 0; i < skill.length(); i++) {
            int current = skill.charAt(i) - 'A';
            int count = 0;
            for (int j = 0; j < i; j++) { // 선행 스킬
                // if (next[current].contains(skill.charAt(j))) return false; // 선행스킬이 배울 스킬 목록에 있는 경우
                if (prev[current].contains(skill.charAt(j))) count++; // 현재 트리에서 학습한 선행 스킬 수 == i의 선행 스킬 수 -> 가능한 스킬트리
            }
            if (count != prev[current].size()) return false;
            
            for (int j = i + 1; j < skill.length(); j++) { // 학습할 스킬
                if (prev[current].contains(skill.charAt(j))) return false; // 학습할 스킬 목록에 선행 스킬이 있는 경우
            }
        }
        
        return true;
    }
}