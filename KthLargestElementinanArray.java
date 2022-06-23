import java.util.Arrays;

// 215
public class KthLargestElementinanArray {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
