import java.util.*;

class Solution {
    private static Set<String> keys = new HashSet<>();
    private static int N;
    private static int M;
    private static String[][] relation;
    
    public int solution(String[][] relation) {
        int answer = 0;
        N = relation.length;
        M = relation[0].length;
        this.relation = relation;
        
        for (int i = 1; i <= M; i++) {
            dfs(0, 0, i, new int[M]);
        }
        
        return keys.size();
    }
    
    private static void dfs(int current, int count, int standard, int[] comb) {
        if (count == standard) {
            if (isValid(comb) && isValidKey(arrayToString(comb))) {
                keys.add(arrayToString(comb));
            }
            return;
        }
        
        for (int i = current; i < M; i++) {
            if (comb[i] == 0) {
                comb[i] = 1;
                dfs(current + 1, count + 1, standard, comb);
                comb[i] = 0;
            }
        }
    }
    
    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(String.valueOf(array[i]));
        }
        return sb.toString();
    }
    
    private static boolean isValidKey(String compare) {
        for (String key: keys) {
            if ((Integer.parseInt(key, 2) & Integer.parseInt(compare, 2)) == Integer.parseInt(key, 2)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValid(int[] col) {
        Set<String> set = new HashSet<>();
        
        for (int r = 0; r < N; r++) {
            StringBuilder key = new StringBuilder();
            for (int c = 0; c < M; c++) {
                if (col[c] == 1) {
                    key.append(relation[r][c]);
                }
            }
            set.add(key.toString());
        }
        return set.size() == N ? true : false;
    }
}