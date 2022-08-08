import java.util.Arrays;

// 300
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] arr = new int[nums.length];
        Arrays.fill(arr, 1);
        int val = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    arr[i] = Math.max(arr[i], arr[j] + 1);
                    val = Math.max(arr[i], val);
                }
            }
        }
        return val;
    }

    // https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
    public int lengthOfLISBetter(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }
}
