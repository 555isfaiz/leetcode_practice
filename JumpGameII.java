public class JumpGameII {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1)
                continue;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length)
                    break;
                dp[i + j] = dp[i + j] == 0 ? dp[i] + 1 : Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[dp.length - 1];
    }
}
