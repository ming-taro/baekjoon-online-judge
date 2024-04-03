import java.util.*;

class Solution {
    private static final int DART_COUNT = 11;
    private static int n;
    private static int[] lion;
    private static int[] apeach;
    private static int[] answer;
    private static int maxDifference;
    
    public int[] solution(int n, int[] info) {
        answer = new int[DART_COUNT];
        
        this.apeach = info;
        this.n = n;
        lion = new int[DART_COUNT];
        
        dfs(0, n);
        
        if (maxDifference == 0) {
            return new int[] { -1 };
        }
        return answer;
    }
    
    private static void dfs(int currentIndex, int dartCount) {
        if (currentIndex == DART_COUNT || dartCount == 0) {
            int difference = calcDifference();
            lion[DART_COUNT - 1] = dartCount;
            
            if (difference > maxDifference) {
                answer = lion.clone();
                maxDifference = difference;
            }
            
            if (difference == maxDifference) {
                for (int i = DART_COUNT - 1; i >= 0; i--) {
                    if (lion[i] > answer[i]) {
                        answer = lion.clone();
                        break;
                    }
                    if (lion[i] < answer[i]) {
                        break;
                    }
                }
            }
            
            lion[DART_COUNT - 1] = 0;
            return;
        }
        
        
        for (int i = currentIndex; i < DART_COUNT; i++) {
            if (apeach[i] < dartCount) {
                lion[i] = apeach[i] + 1;
                dfs(i + 1, dartCount - lion[i]);
                lion[i] = 0;
            }
            dfs(i + 1, dartCount - lion[i]);
        }
    }
    
    private static int calcDifference() {
        int lionScore = 0;
        int apeachScore = 0;
        
        for (int i = 0; i < DART_COUNT; i++) {
            if (lion[i] > apeach[i]) {
                lionScore += 10 - i;
            } else if (lion[i] < apeach[i]) {
                apeachScore += 10 - i;
            }
        }
        
        return lionScore - apeachScore;
    }
}