import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode getTree(Object[] nodes) {
        if (nodes == null || nodes.length == 0) return null;
        TreeNode root = new TreeNode((int)nodes[0]);
        int ptr = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (ptr < nodes.length) {
            var n = queue.poll();
            if (nodes[ptr] != null) { var l = new TreeNode((int)nodes[ptr]); n.left = l; queue.add(l); }
            ptr++;
            if (ptr >= nodes.length) break;
            if (nodes[ptr] != null) { var r = new TreeNode((int)nodes[ptr]); n.right = r; queue.add(r); }
            ptr++;
        }
        return root;
    }
}
