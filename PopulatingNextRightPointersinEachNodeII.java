import java.util.LinkedList;
import java.util.Queue;

// 117
public class PopulatingNextRightPointersinEachNodeII {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> q = new LinkedList<>();
        Node dummy = new Node();
        q.add(root);
        q.add(dummy);
        while (!q.isEmpty()) {
            Node ptr = q.poll();
            if (ptr.left != null) q.add(ptr.left);
            if (ptr.right != null) q.add(ptr.right);
            if (q.peek() == dummy) {
                q.poll();
                q.add(dummy);
            } else {
                ptr.next = q.peek();
            }
        }
        return root;
    }

    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/472675/Short-Java-solution-with-explanation-100-runtime-and-100-memory
    public Node connectBetter(Node root) {
        if (root == null) return null;

        if (root.left != null) { // update left next
            if (root.right != null) root.left.next = root.right; // if right child exists - simple connect left.next to right
            else root.left.next = findNext(root); // if not - scan parent next node until we find the first left or right child
        }
        if (root.right != null) { // update right next
            root.right.next = findNext(root);
        }

        connectBetter(root.right); // update the right nodes first
        connectBetter(root.left);
        return root;
    }

    private Node findNext(Node root) { // get parent node
        while (root.next != null) { // scan all next parent nodes until we find the first left or right child
            root = root.next;
            if (root.left != null) return root.left;
            if (root.right != null) return root.right;
        }
        return null;
    }
}
