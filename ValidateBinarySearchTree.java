import java.util.ArrayList;
import java.util.List;

// 98
public class ValidateBinarySearchTree {
    List<TreeNode> lefts = new ArrayList<>();
    List<TreeNode> rights = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        lefts.add(root);
        if (root.left != null) {
            if (!isValidBST(root.left)) return false;
            for (var n : lefts) {
                if (root.left.val >= n.val) return false;
            }
            for (var n : rights) {
                if (root.left.val <= n.val) return false;
            }
        }
        lefts.remove(root);

        rights.add(root);
        if (root.right != null) {
            if (!isValidBST(root.right)) return false;
            for (var n : lefts) {
                if (root.right.val >= n.val) return false;
            }
            for (var n : rights) {
                if (root.right.val <= n.val) return false;
            }
        }
        rights.remove(root);

        return true;
    }

    // https://leetcode.com/problems/validate-binary-search-tree/discuss/32109/My-simple-Java-solution-in-3-lines
    public boolean isValidBSTBetter(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        System.out.println(v.isValidBST(TreeNode.getTree(new Object[]{
                5,4,6,null,null,3,7
        })));
    }
}
