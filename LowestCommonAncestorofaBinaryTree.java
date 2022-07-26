import java.util.*;

// 236
public class LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean fp = false, fq = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                var n = queue.poll();
                if (n.left != null) {
                    queue.add(n.left);
                    parent.put(n.left, n);
                    if (n.left == p) fp = true;
                }
                if (n.right != null) {
                    queue.add(n.right);
                    parent.put(n.right, n);
                    if (n.right == p) fq = true;
                }
            }
            if (fp && fq) break;
        }

        TreeNode ptr = p;
        List<TreeNode> ancestors = new ArrayList<>();
        while (ptr != null) {
            ancestors.add(ptr);
            ptr = parent.get(ptr);
        }

        ptr = q;
        while (ptr != null) {
            if (ancestors.contains(ptr)) break;
            ptr = parent.get(ptr);
        }
        return ptr;
    }

    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C%2B%2BJavaPythonRuby
    public TreeNode lowestCommonAncestorBetter(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}
