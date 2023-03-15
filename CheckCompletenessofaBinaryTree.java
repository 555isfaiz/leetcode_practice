import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessofaBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        int val_target = 1;
        boolean next_compact = true;
        q.add(root);
        while (!q.isEmpty())
        {
            int size = q.size();
            boolean compact = true, null_seen = false;
            for (int i = 0; i < size; i++)
            {
                var n = q.poll();
                if (n.left != null) {
                    q.add(n.left);
                    if (null_seen) compact = false;
                }
                else null_seen = true;
                
                if (n.right != null) {
                    q.add(n.right);
                    if (null_seen) compact = false;
                }
                else null_seen = true;
            }

            if ((!q.isEmpty() && size < val_target) || (q.isEmpty() && !next_compact)) return false;
            val_target <<= 1;
            next_compact = compact;
        }
        return true;
    }

    // https://leetcode.com/problems/check-completeness-of-a-binary-tree/solutions/3298346/clean-codes-full-explanation-b-f-s-c-java-python3/
    class Solution {
        public boolean isCompleteTree(TreeNode root) {
            if (root == null)
            return true;

            Queue<TreeNode> q = new LinkedList<>(Arrays.asList(root));

            while (q.peek() != null) {
                TreeNode node = q.poll();
                q.offer(node.left);
                q.offer(node.right);
            }

            while (!q.isEmpty() && q.peek() == null)
            q.poll();

            return q.isEmpty();
        }
    }
}
