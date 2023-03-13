public class SymmetricTree {
    boolean isMirror(TreeNode left, TreeNode right) {
        if (left.val != right.val || 
            (left.left == null && right.right != null) ||
            (left.right == null && right.left != null) ||
            (left.left != null && right.right == null) ||
            (left.right != null && right.left == null))
            return false;
        
        boolean l_ = true, r_ = true;
        if (left.left != null) {
            l_ = isMirror(left.left, right.right);
        }

        if (left.right != null) {
            r_ = isMirror(left.right, right.left);
        }
        return l_ & r_;
    }

    public boolean isSymmetric(TreeNode root) {
        if ((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return false;
        if (root.left == null && root.right == null)
            return true;
        return isMirror(root.left, root.right);
    }

    // https://leetcode.com/problems/symmetric-tree/solutions/3290132/python-java-c-video-explanation/
    class Solution {
        public boolean isSymmetric(TreeNode root) {
            return isMirror(root, root);
        }

        public boolean isMirror(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) {
                return true;
            }
            if (t1 == null || t2 == null) {
                return false;
            }
            return t1.val == t2.val && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
        }
    }
}