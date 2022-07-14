import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 105
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    Map<Integer, Integer> indices = new HashMap<>();
    List<Integer> roots = new ArrayList<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode tree = new TreeNode(preorder[0]);
        for (int i = 0; i < inorder.length; i++) {
            indices.put(inorder[i], i);
        }

        roots.add(indices.get(preorder[0]));
        build_(tree, preorder, 0, indices.get(preorder[0]), -1);
        return tree;
    }

    int build_(TreeNode root, int[] preorder, int rootIndexP, int rootIndexI, int parentI) {
        int childIndexP;
        int childIndexI = 0;
        childIndexP = rootIndexP + 1;
        boolean left_valid = true;
        if (childIndexP >= preorder.length) left_valid = false;
        else {
            childIndexI = indices.get(preorder[childIndexP]);
            if ((childIndexI - parentI) * (rootIndexI - parentI) < 0) left_valid = false;
            if (childIndexI >= rootIndexI) left_valid = false;
        }

        int left_count = 0;
        if (left_valid) {
            TreeNode left = new TreeNode(preorder[childIndexP]);
            root.left = left;
            roots.add(childIndexI);
            left_count = build_(left, preorder, childIndexP, childIndexI, rootIndexI) + 1;
            roots.remove(roots.size() - 1);
        }

        childIndexP = rootIndexP + left_count + 1;
        if (childIndexP >= preorder.length) return left_count;
        childIndexI = indices.get(preorder[childIndexP]);
        if (childIndexI <= rootIndexI) return left_count;
        for (var i : roots) {
            if ((childIndexI - i) * (rootIndexI - i) < 0) return left_count;
        }

        TreeNode right = new TreeNode(preorder[childIndexP]);
        root.right = right;
        roots.add(childIndexI);
        int right_count = build_(right, preorder, childIndexP, childIndexI, rootIndexI) + 1;
        roots.remove(roots.size() - 1);

        return left_count + right_count;
    }

    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34538/My-Accepted-Java-Solution
    public TreeNode buildTreeBetter(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }

    public static void main(String[] args) {
        ConstructBinaryTreefromPreorderandInorderTraversal c = new ConstructBinaryTreefromPreorderandInorderTraversal();
        System.out.println(TreeNode.printTree(c.buildTree(new int[]{
                3,1,2,4
//                3,9,20,15,7
        }, new int[]{
                1,2,3,4
//                9,3,15,20,7
        })));
    }
}
