import java.util.Arrays;

// 377
public class CombinationSumIV {
    int val;
    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        find(nums, target);
        return val;
    }

    void find(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) break;
            if (nums[i] == target) val++;
            find(nums, target - nums[i]);
        }
    }

    // https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
    private int[] dp;

    public int combinationSum4Better(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }

    public static void main(String[] args) {
        CombinationSumIV c = new CombinationSumIV();
        System.out.println(c.combinationSum4(new int[] {
                2, 1, 3
        }, 35));
    }
}
