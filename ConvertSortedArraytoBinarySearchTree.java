// 108
public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    TreeNode helper(int[] nums, int low, int high) {
        if (low > high) return null;
        int middle = (high + low) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = helper(nums, low, middle - 1);
        root.right = helper(nums, middle + 1, high);
        return root;
    }
}
