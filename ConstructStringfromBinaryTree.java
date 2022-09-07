// 606
public class ConstructStringfromBinaryTree {
    public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        walk(root, sb);
        return sb.toString();
    }

    void walk(TreeNode node, StringBuilder sb) {
        sb.append(node.val);
        if (node.left == null && node.right == null) return;
        sb.append('(');
        if (node.left != null) {
            walk(node.left, sb);
        }
        sb.append(')');
        if (node.right != null) {
            sb.append('(');
            walk(node.right, sb);
            sb.append(')');
        }
    }
}
