import java.util.Arrays;
import java.util.HashMap;

/**
 * MinimizetheMaximumDifferenceofPairs
 */
public class MinimizetheMaximumDifferenceofPairs {
    int min = Integer.MAX_VALUE;

    private int helper(HashMap<Integer, Integer>[] dp, int[] nums, int ptr, int p, int p_ori) {
        if (p == 0)
            return 0;
        if ((nums.length - ptr) / 2 < p)
            return Integer.MAX_VALUE;
        if (dp[ptr] == null)
            dp[ptr] = new HashMap<>();
        if (dp[ptr].containsKey(p))
            return dp[ptr].get(p);

        int val;
        if (Math.abs(nums[ptr] - nums[ptr + 1]) > min)
            val = helper(dp, nums, ptr + 1, p, p_ori);
        else
            val = Math.min(helper(dp, nums, ptr + 1, p, p_ori),
                    Math.max(Math.abs(nums[ptr] - nums[ptr + 1]),
                            helper(dp, nums, ptr + 2, p - 1, p_ori)));

        dp[ptr].put(p, val);
        if (p == p_ori)
            min = dp[ptr].get(p);
        return val;
    }

    public int minimizeMax(int[] nums, int p) {
        if (nums.length <= 1 || p == 0)
            return 0;
        Arrays.sort(nums);

        HashMap<Integer, Integer>[] dp = new HashMap[nums.length];
        helper(dp, nums, 0, p, p);
        return min;
    }

    // https://leetcode.com/problems/minimize-the-maximum-difference-of-pairs/solutions/3883965/100-binary-search-greedy-video-in-o-n-log-m-optimal-solution/
    public class Solution {
        public int minimizeMax(int[] nums, int p) {
            Arrays.sort(nums);

            int left = 0, right = nums[nums.length - 1] - nums[0];

            while (left < right) {
                int mid = (left + right) / 2;
                if (can_form_pairs(nums, mid, p)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        private boolean can_form_pairs(int[] nums, int mid, int p) {
            int count = 0;
            for (int i = 0; i < nums.length - 1 && count < p;) {
                if (nums[i + 1] - nums[i] <= mid) {
                    count++;
                    i += 2;
                } else {
                    i++;
                }
            }
            return count >= p;
        }
    }

    public static void main(String[] args) {
        MinimizetheMaximumDifferenceofPairs m = new MinimizetheMaximumDifferenceofPairs();
        System.out.println(m.minimizeMax(new int[] {4, 0, 2, 1, 2, 5, 5, 3}, 3));
    }
}
