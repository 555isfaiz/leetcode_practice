import java.util.Stack;

public class ConstructBinaryTreefromInorderandPostorderTraversal {
    int indexof(int[] arr, int val, int s, int e) {
        for (int i = s; i <=e; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }

    TreeNode buildTreeI(int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
        if (pe < 0 || ie < is || pe < ps)
            return null;

        TreeNode root = new TreeNode(postorder[pe]);
        int index = indexof(inorder, postorder[pe], is, ie);

        if (index == -1) return null;

        int lstart = -1;
        for (int i = pe - 1; i >= ps; i--) {
            if (indexof(inorder, postorder[i], is, ie) < index) {
                lstart = i;
                break;
            }
        }

        if (lstart != -1)
            root.left = buildTreeI(inorder, postorder, is, index, ps, lstart);
        if (lstart != pe - 1)
            root.right = buildTreeI(inorder, postorder, index, ie, lstart == -1 ? 0 : lstart, pe - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0)
            return null;
        
        return buildTreeI(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solutions/3302302/clean-codes-full-explanation-using-stack-c-java-python3/
    class Solution 
    {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 0 || postorder.length == 0) return null;
            
            int ip = inorder.length - 1;
            int pp = postorder.length - 1;

            Stack<TreeNode> stack = new Stack<TreeNode>();
            TreeNode prev = null;
            TreeNode root = new TreeNode(postorder[pp]);

            stack.push(root);
            pp--;

            while (pp >= 0) {
                while (!stack.isEmpty() && stack.peek().val == inorder[ip]) {
                    prev = stack.pop();
                    ip--;
                }
                TreeNode newNode = new TreeNode(postorder[pp]);
                if (prev != null) {
                    prev.left = newNode;
                } else if (!stack.isEmpty()) {
                    TreeNode currTop = stack.peek();
                    currTop.right = newNode;
                }
                stack.push(newNode);
                prev = null;
                pp--;
            }

            return root;
        }
    }

    public static void main(String[] args) {
        ConstructBinaryTreefromInorderandPostorderTraversal c = new ConstructBinaryTreefromInorderandPostorderTraversal();
        c.buildTree(new int[] {1, 2}, new int[] {2, 1});
    }
}
