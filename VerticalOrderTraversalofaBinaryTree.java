import java.util.*;

// 987
public class VerticalOrderTraversalofaBinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> rows = new TreeSet<>();
        Set<Integer> columns = new TreeSet<>();
        Map<List<Integer>, List<Integer>> m = new HashMap<>();
        walk(root, 0, 0, rows, columns, m);
        List<Integer> loc = new ArrayList<>();
        loc.add(null); loc.add(null);
        for (var c : columns) {
            List<Integer> l = new ArrayList<>();
            for (var r : rows) {
                loc.set(0, r); loc.set(1, c);
                if (m.containsKey(loc)) {
                    var t = m.get(loc);
                    t.sort(Integer::compareTo);
                    l.addAll(t);
                }
            }
            if (!l.isEmpty()) res.add(l);
        }
        return res;
    }

    void walk(TreeNode node, int row, int column, Set<Integer> rows, Set<Integer> columns, Map<List<Integer>, List<Integer>> m) {
        rows.add(row); columns.add(column);
        List<Integer> loc = new ArrayList<>();
        loc.add(row); loc.add(column);
        if (m.containsKey(loc)) {
            m.get(loc).add(node.val);
        } else {
            List<Integer> l = new ArrayList<>();
            l.add(node.val);
            m.put(loc, l);
        }

        if (node.left != null)
            walk(node.left, row + 1, column - 1, rows, columns, m);
        if (node.right != null)
            walk(node.right, row + 1, column + 1, rows, columns, m);
    }

    // https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/discuss/231148/Java-TreeMap-Solution
    public List<List<Integer>> verticalTraversalBetter(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }
    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }
        if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
    }

    public static void main(String[] args) {
        Object[] nodes = new Object[] {1,2,3,4,6,5,7};
        VerticalOrderTraversalofaBinaryTree v = new VerticalOrderTraversalofaBinaryTree();
        System.out.println(v.verticalTraversal(TreeNode.getTree(nodes)));
    }
}
