import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> m = new HashMap<>();
        m.put('(', ')');
        m.put('[', ']');
        m.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                var c = stack.peek();
                if (m.containsKey(c) && m.get(c).equals(s.charAt(i)))
                    stack.pop();
                else
                    stack.push(s.charAt(i));
            } else
                stack.push(s.charAt(i));
        }
        return stack.isEmpty();
    }

    // https://leetcode.com/problems/valid-parentheses/solutions/3399077/easy-solutions-in-java-python-and-c-look-at-once-with-exaplanation/
    class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<Character>(); // create an empty stack
            for (char c : s.toCharArray()) { // loop through each character in the string
                if (c == '(') // if the character is an opening parenthesis
                    stack.push(')'); // push the corresponding closing parenthesis onto the stack
                else if (c == '{') // if the character is an opening brace
                    stack.push('}'); // push the corresponding closing brace onto the stack
                else if (c == '[') // if the character is an opening bracket
                    stack.push(']'); // push the corresponding closing bracket onto the stack
                else if (stack.isEmpty() || stack.pop() != c) // if the character is a closing bracket
                    // if the stack is empty (i.e., there is no matching opening bracket) or the top of the stack
                    // does not match the closing bracket, the string is not valid, so return false
                    return false;
            }
            // if the stack is empty, all opening brackets have been matched with their corresponding closing brackets,
            // so the string is valid, otherwise, there are unmatched opening brackets, so return false
            return stack.isEmpty();
        }
    }
}
