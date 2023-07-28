public class PredicttheWinner {
    public int helper(int[][] dp, int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }

        if (start == end) {
            return nums[start];
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int left = nums[start] - helper(dp, nums, start + 1, end);
        int right = nums[end] - helper(dp, nums, start, end - 1);
        dp[start][end] = Math.max(left, right);
        return dp[start][end];
    }
    
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];

        int max = helper(dp, nums, 0, nums.length - 1);
        return max >= 0;
    }

    public static void main(String[] args) {
        PredicttheWinner p = new PredicttheWinner();
        System.out.println(p.PredictTheWinner(new int[] {1, 5, 2}));
    }
}
