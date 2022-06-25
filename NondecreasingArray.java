// 665
public class NondecreasingArray {
    public boolean checkPossibility(int[] nums) {
        boolean first = true;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1) {
                if (nums[i] > nums[i + 1]) {
                    if (first) {
                        if (i > 0 && nums[i + 1] < nums[i - 1]) nums[i + 1] = nums[i];
                        else nums[i] = nums[i + 1];
                        first = false;
                    } else return false;
                }
            }
        }
        return true;
    }
}
