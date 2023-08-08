/**
 * SearchinaRotatedSortedArray
 */
public class SearchinaRotatedSortedArray {

    public int search(int[] nums, int target) {
        int res = -1;
        int offset = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                offset = i + 1;
                break;
            }
        }

        int start = offset, end = offset + nums.length - 1;
        while (end >= start) {
            int ptr = (end + start) / 2;
            if (nums[ptr % nums.length] == target)
                return ptr % nums.length;
            else if (nums[ptr % nums.length] > target)
                end = ptr - 1;
            else
                start = ptr + 1;
        }
        return res;
    }

    // https://leetcode.com/problems/search-in-rotated-sorted-array/solutions/3879263/100-binary-search-easy-video-o-log-n-optimal-solution/
    class Solution {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[low] <= nums[mid]) {
                    if (nums[low] <= target && target < nums[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    if (nums[mid] < target && target <= nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }

            return -1;
        }
    }

    public static void main(String[] args) {
        SearchinaRotatedSortedArray s = new SearchinaRotatedSortedArray();
        System.out.println(s.search(new int[] {3, 1}, 1));
    }
}
