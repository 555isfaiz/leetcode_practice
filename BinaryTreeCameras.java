import java.util.*;

// 968
public class BinaryTreeCameras {
    public int minCameraCover(TreeNode root) {
        List<TreeNode> s = new ArrayList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        int val = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                var node = q.poll();
                if (node == null) continue;
                s.add(node);
                if (node.left != null) {
                    q.add(node.left);
                    map.put(node.left, node);
                }
                if (node.right != null) {
                    q.add(node.right);
                    map.put(node.right, node);
                }
            }
        }

        while (s.size() != 0) {
            val++;
            var n = s.get(s.size() - 1);
            var parent = map.get(n);
            var pparent = map.get(parent);
            s.remove(s.size() - 1);
            s.remove(parent);
            s.remove(pparent);
            if (parent != null) {
                if (parent.left != null) s.remove(parent.left);
                if (parent.right != null) s.remove(parent.right);
            }
        }
        return val;
    }

    // https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
    int res = 0;
    public int minCameraCoverBetter(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
//        TreeNode nnnnnnnn = new TreeNode();
//        TreeNode nnnnnnn = new TreeNode(0, nnnnnnnn, null);
//        TreeNode nnnnnn = new TreeNode(0, nnnnnnn, null);
//        TreeNode nnnnn = new TreeNode(0, null, null);
//        TreeNode nnnn = new TreeNode(0, nnnnn, nnnnnn);
//        TreeNode nnn = new TreeNode(0, null, nnnn);
//        TreeNode nn = new TreeNode(0, null, nnn);
//        TreeNode n = new TreeNode(0, null, nn);
        TreeNode n = TreeNode.getTree(new Object[]{0,null,0,null,0,null,0,null,0,null,0,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,0,null,null,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null,0,null});
        BinaryTreeCameras b = new BinaryTreeCameras();
        System.out.println(b.minCameraCover(n));
    }
}
