import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 637
public class AverageofLevelsinBinaryTree {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Double> result = new ArrayList<>();
        q.add(root);
        int count = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                var node = q.poll();
                sum += node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            result.add(sum / size);
        }
        return result;
    }
}
