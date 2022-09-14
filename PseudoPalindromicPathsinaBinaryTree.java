import java.util.HashMap;
import java.util.Map;

// 1457
public class PseudoPalindromicPathsinaBinaryTree {
    int val;
    public int pseudoPalindromicPaths (TreeNode root) {
        if (root.left == null && root.right == null) return 1;
        walk(root, new HashMap<>());
        return val;
    }

    void walk(TreeNode node, Map<Integer, Integer> map) {
        map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        if (node.left != null)
            walk(node.left, map);
        if (node.right != null)
            walk(node.right, map);
        if (node.left == null && node.right == null) {
            boolean skip = false, valid = true;
            for (var e : map.entrySet()) {
                if (e.getValue() % 2 != 0) {
                    if (skip) {
                        valid = false;
                        break;
                    }
                    else skip = true;
                }
            }

            if (valid) val++;
        }
        map.put(node.val, map.get(node.val) - 1);
    }

    // https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/discuss/2573276/JAVA-oror-DFS-and-Bit-Manipulation-oror-100-Faster-Code-oror-Beginner-Friendly
    class Solution {
        int ans = 0;
        public int pseudoPalindromicPaths (TreeNode root) {
            ans = 0;
            dfs(root, 0);
            return ans;
        }
        private void dfs(TreeNode root, int count) {
            if (root == null) return;
            count ^= 1 << (root.val - 1);
            dfs(root.left, count);
            dfs(root.right, count);
            if (root.left == null && root.right ==  null && (count & (count - 1)) == 0) ans++;
        }
    }
}
