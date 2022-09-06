// 1448
public class CountGoodNodesinBinaryTree {
    int val = 0;
    public int goodNodes(TreeNode root) {
        walk(root, root.val);
        return val;
    }

    void walk(TreeNode node, int maxVal) {
        if (node.val >= maxVal) val++;
        if (node.left != null) walk(node.left, Math.max(node.val, maxVal));
        if (node.right != null) walk(node.right, Math.max(node.val, maxVal));
    }
}
