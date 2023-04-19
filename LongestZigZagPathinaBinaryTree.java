import java.util.LinkedList;
import java.util.Queue;

public class LongestZigZagPathinaBinaryTree {
    public int longestZigZag(TreeNode root) {
        // helper(root, true, 0);
        // helper(root, false, 0);
        // return res;
        Queue<Object[]> q = new LinkedList<>();
        if (root.left != null) {
            q.add(new Object[] {root.left, true, 1});
        }
        if (root.right != null) {
            q.add(new Object[] {root.right, false, 1});
        }

        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                var e = q.poll();
                TreeNode n = (TreeNode) e[0];
                boolean from_left = (boolean)e[1];
                int val = (int)e[2];
                if (n.left != null) {
                    if (from_left) {
                        res = Math.max(val, res);
                        q.add(new Object[] {n.left, true, 1});
                    } else 
                    q.add(new Object[] {n.left, true, val + 1});
                }
                if (n.right != null) {
                    if (!from_left) {
                        res = Math.max(val, res);
                        q.add(new Object[] {n.right, false, 1});
                    } else 
                    q.add(new Object[] {n.right, false, val + 1});
                }
                res = Math.max(val, res);
            }
        }
        return res;
    }

    // https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/solutions/3432994/python-java-c-simple-solution-easy-to-understand/
    public class Solution {
        public int longestZigZag(TreeNode root) {
            int[] res = dfs(root);
            return res[2];
        }
        
        private int[] dfs(TreeNode node) {
            if (node == null) {
                return new int[]{-1, -1, -1};
            }
            
            int[] leftSubtree = dfs(node.left);
            int[] rightSubtree = dfs(node.right);
            
            int leftLength = leftSubtree[1] + 1;
            int rightLength = rightSubtree[0] + 1;
            int maxLen = Math.max(Math.max(leftLength, rightLength), Math.max(leftSubtree[2], rightSubtree[2]));
            
            return new int[]{leftLength, rightLength, maxLen};
        }
    }

    public static void main(String[] args) {
        LongestZigZagPathinaBinaryTree l = new LongestZigZagPathinaBinaryTree();
        System.out.println(l.longestZigZag(TreeNode.getTree(new Object[] {1,1,1,1,null,1,1,null,null,1,null,1,null,null,1,null,1})));
    }
}
