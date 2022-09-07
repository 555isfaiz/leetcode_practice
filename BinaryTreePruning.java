// 814
public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        if (del(root)) root = null;
        return root;
    }

    boolean del(TreeNode node) {
        Boolean l = null, r = null;
        if (node.left != null) l = del(node.left);
        if (node.right != null) r = del(node.right);
        if (l != null && l) node.left = null;
        if (r != null && r) node.right = null;
        return node.val == 0 && (r == null || r) && (l == null || l);
    }
}
