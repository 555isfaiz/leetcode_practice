import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class NNode {
    public int val;
    public List<NNode> children;

    public NNode() {}

    public NNode(int _val) {
        val = _val;
    }

    public NNode(int _val, List<NNode> _children) {
        val = _val;
        children = _children;
    }
};

// 429
public class NaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(NNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<NNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                var n = q.poll();
                l.add(n.val);
                q.addAll(n.children);
            }
            res.add(l);
        }
        return res;
    }
}
