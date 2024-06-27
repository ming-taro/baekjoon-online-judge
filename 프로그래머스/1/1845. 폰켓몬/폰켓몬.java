import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        
        for (int num: nums) {
            int count = 1;
            if (counts.containsKey(num)) {
                count += counts.get(num);
            }
            counts.put(num, count);
        }
        
        return Math.min(nums.length / 2, counts.size());
    }
}
/*
N마리의 포켓몬중 N/2마리를 가져감
nums -> 포켓몬 번호 모음
ex) [3, 1, 2, 3] 3번 포켓몬이 2마리다
    총 4마리 중 2마리를 고른다 -> 이때 최대한 다양한 종류의 포켓몬을 가지고 싶음
    
*/