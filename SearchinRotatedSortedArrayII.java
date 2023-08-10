/**
 * SearchinRotatedSortedArrayII
 */
public class SearchinRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        int offset = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == target)
                return true;
            if (nums[i] > nums[i + 1]) {
                offset = i + 1;
                break;
            }
        }

        int start = offset, end = offset + nums.length - 1;
        while (end >= start) {
            int ptr = (end + start) / 2;
            if (nums[ptr % nums.length] == target)
                return true;
            else if (nums[ptr % nums.length] > target)
                end = ptr - 1;
            else
                start = ptr + 1;
        }
        return false;
    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array-ii/solutions/3888242/100-binary-search-video-with-rotation-handling-optimal/
    public class Solution {
        public boolean search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums[mid] == target) return true;

                if (nums[low] == nums[mid]) {
                    low++;
                    continue;
                }

                if (nums[low] <= nums[mid]) {
                    if (nums[low] <= target && target <= nums[mid])
                        high = mid - 1;
                    else
                        low = mid + 1;
                } else {
                    if (nums[mid] <= target && target <= nums[high])
                        low = mid + 1;
                    else
                        high = mid - 1;
                }
            }
            return false;
        }
    }
}
