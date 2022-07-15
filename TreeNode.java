import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public static List<Integer> printTree(TreeNode n) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                var nn = q.poll();
                if (nn == null) { res.add(null); continue; }
                res.add(nn.val);
                if (nn.left != null || nn.right != null) {
                    q.add(nn.left);
                    q.add(nn.right);
                }
            }
        }
        return res;
    }
}
