// 114
public class FlattenBinaryTreetoLinkedList {
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.right != null) {
            if (root.left != null) {
                var ptr = root.left;
                while (ptr.right != null) ptr = ptr.right;
                ptr.right = root.right;
            }
        }
        if (root.left != null) root.right = root.left;
        root.left = null;
        flatten(root.right);
    }
}
