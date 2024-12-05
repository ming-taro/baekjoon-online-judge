import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 100000;
        
        int start = 1;
        int end = 100000;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            long result = run(diffs, times, mid);
            if (result > limit) {
                start = mid + 1;
            } else {
                end = mid - 1;
                answer = Math.min(answer, mid);
            }
        }
        
        return answer;
    }
    
    private static long run(int[] diffs, int[] times, int level) {
        int n = diffs.length;
        long total = 0;
        
        for (int i = 0; i < n; i++) {
            if (diffs[i] <= level) {
                total += times[i];
            } else {
                int time = i == 0 ? 0 : times[i - 1] + times[i];
                total += (diffs[i] - level) * time + times[i];
            }
        }
        
        return total;
    }
}
/*
n개의 퍼즐 - 난이도, 소요시간
숙련도 -> 퍼즐 풀 때 틀리는 횟수가 바뀜
diffs = 난이도 / times = 소요 시간 / limit = 제한 시간

return 최소 숙련도
*/