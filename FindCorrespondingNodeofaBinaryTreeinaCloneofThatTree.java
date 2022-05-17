// 1379
public class FindCorrespondingNodeofaBinaryTreeinaCloneofThatTree {
    TreeNode res = null;
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (res != null)
            return res;

        if (original.val == target.val) {
            res = cloned;
            return res;
        } else {
            if (original.left != null) {
                getTargetCopy(original.left, cloned.left, target);
            }
            if (original.right != null) {
                getTargetCopy(original.right, cloned.right, target);
            }
        }
        return res;
    }

    // same efficiency as mine but codes are clean ^^
    // https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/discuss/537728/Java-Simple-Solution
    public final TreeNode getTargetCopyBetter(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || original == target)
            return cloned;
        TreeNode res = getTargetCopy(original.left, cloned.left, target);
        if (res != null)
            return res;
        return getTargetCopy(original.right, cloned.right, target);
    }
}
