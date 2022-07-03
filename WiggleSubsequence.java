// 376
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) return 1;
        if (nums.length == 2) {
            if (nums[0] == nums[1]) return 1;
            else return 2;
        }
        int val = 1;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i != nums.length - 1) {
                var tmp_diff = nums[i + 1] - nums[i];
                if ((diff == Integer.MAX_VALUE && tmp_diff != 0) || diff * tmp_diff < 0) {
                    diff = tmp_diff;
                    val++;
                }
            }
        }
        return val;
    }

    public static void main(String[] args) {
        WiggleSubsequence w = new WiggleSubsequence();
        System.out.println(w.wiggleMaxLength(new int[] {1, 7, 4, 9, 2, 5}));
    }
}
