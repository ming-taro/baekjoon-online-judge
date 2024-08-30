class Solution {
    public int lengthOfLIS(int[] nums) {
        int answer = 0;
        int n = nums.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        return answer + 1;
    }
}