import java.util.ArrayList;
import java.util.List;

public class GNode {
    public int val;
    public List<GNode> neighbors;
    public GNode() {
        val = 0;
        neighbors = new ArrayList<GNode>();
    }
    public GNode(int _val) {
        val = _val;
        neighbors = new ArrayList<GNode>();
    }
    public GNode(int _val, ArrayList<GNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
