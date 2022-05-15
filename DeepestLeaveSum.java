import java.util.LinkedList;
import java.util.Queue;

// 1302
public class DeepestLeaveSum {
    public int deepestLeavesSum(TreeNode root) {
        int val = 0;
        int maxVal = 0;
        TreeNode dummy = new TreeNode();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(dummy);
        while (!q.isEmpty()) {
            var n = q.poll();
            if (n == null) break;
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
            val += n.val;
            if (q.peek() == dummy) {
                maxVal = val;
                q.add(q.poll());
                val = 0;
            }
        }
        return maxVal;
    }
}
