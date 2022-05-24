import java.util.Stack;

// 32
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int val = 0;
        int[] marker = new int[s.length()];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    int l = stack.pop();
                    marker[l] = 1;
                    marker[i] = 1;
                }
            }
        }

        int t = 0;
        for (int j : marker) {
            if (j == 0) t = 0;
            else t++;
            val = Math.max(val, t);
        }
        return val;
    }

    // https://leetcode.com/problems/longest-valid-parentheses/discuss/14167/Simple-JAVA-solution-O(n)-time-one-stack
    public int longestValidParenthesesBetter(String s) {
        Stack<Integer> stack = new Stack<>();
        int max=0;
        int left = -1;
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='(') stack.push(j);
            else {
                if (stack.isEmpty()) left=j;
                else{
                    stack.pop();
                    if(stack.isEmpty()) max=Math.max(max,j-left);
                    else max=Math.max(max,j-stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
        System.out.println(l.longestValidParentheses(")(())(()()))("));
    }
}
