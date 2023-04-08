import java.util.HashMap;
import java.util.Map;

public class CloneGraph {
    Map<GNode, GNode> known = new HashMap<>();
    public GNode cloneGraph(GNode node) {
        if (node == null)
            return node;
        GNode res = new GNode(node.val);
        known.put(node, res);
        for (int i = 0; i < node.neighbors.size(); i++) {
            var n = node.neighbors.get(i);
            if (!known.containsKey(n))
                res.neighbors.add(cloneGraph(node.neighbors.get(i)));
            else
                res.neighbors.add(known.get(n));
        }
        return res;
    }
}
