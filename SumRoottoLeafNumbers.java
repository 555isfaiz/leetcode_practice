public class SumRoottoLeafNumbers {
    int sum = 0;

    void letsgo(TreeNode root, int val) {
        val = val * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += val;
            return;
        }

        if (root.left != null) letsgo(root.left, val);
        if (root.right != null) letsgo(root.right, val);
    }

    public int sumNumbers(TreeNode root) {
        letsgo(root, 0);
        return sum;
    }
}
