import java.util.ArrayList;
import java.util.List;

// 94
public class BinaryTreeInorderTraversal {
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return list;
        walk(root);
        return list;
    }

    void walk(TreeNode node) {
        if (node.left != null) walk(node.left);
        list.add(node.val);
        if (node.right != null) walk(node.right);
    }
}
