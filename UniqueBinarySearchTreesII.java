import java.util.ArrayList;
import java.util.List;

/**
 * UniqueBinarySearchTreesII
 */
public class UniqueBinarySearchTreesII {

  public List<TreeNode> helper(int start, int end) {
    List<TreeNode> res = new ArrayList<>();
    if (start > end) {
      res.add(null);
      return res;
    }

    for (int i = start; i <= end; i++) {
      var left = helper(start, i - 1);
      var right = helper(i + 1, end);
      for (var l : left) {
        for (var r : right) {
          TreeNode head = new TreeNode(i);
          head.left = l;
          head.right = r;
          res.add(head);
        }
      }
    }
    return res;
  }

  public List<TreeNode> generateTrees(int n) { return helper(1, n); }

  // https://leetcode.com/problems/unique-binary-search-trees-ii/solutions/3864900/recursion-dp-video-catalan-number-unique-bst/
  class Solution {
    public List<TreeNode> generateTrees(int n) {
      if (n == 0)
        return new ArrayList<>();

      List<TreeNode>[] dp = new ArrayList[n + 1];
      dp[0] = new ArrayList<>();
      dp[0].add(null);
      for (int nodes = 1; nodes <= n; nodes++) {
        dp[nodes] = new ArrayList<>();
        for (int root = 1; root <= nodes; root++) {
          for (TreeNode left_tree : dp[root - 1]) {
            for (TreeNode right_tree : dp[nodes - root]) {
              TreeNode root_node = new TreeNode(root);
              root_node.left = left_tree;
              root_node.right = clone(right_tree, root);
              dp[nodes].add(root_node);
            }
          }
        }
      }
      return dp[n];
    }

    private TreeNode clone(TreeNode n, int offset) {
      if (n == null)
        return null;
      TreeNode node = new TreeNode(n.val + offset);
      node.left = clone(n.left, offset);
      node.right = clone(n.right, offset);
      return node;
    }
  }
}
