import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SimplifyPath {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < path.length(); i++) {
            while (i < path.length() && path.charAt(i) == '/') {
                i++;
            }
            
            if (i >= path.length()) break;

            int start = i;
            while (i < path.length() && path.charAt(i) != '/') {
                i++;
            }

            if (i - start == 2 
                && path.substring(start, i).equals(".."))
                if (stack.isEmpty())
                    continue;
                else
                    stack.pop();
            else if (i - start == 1 
                && path.substring(start, i).equals("."))
                continue;
            else
                stack.push(path.substring(start, i));
        }

        StringBuffer sb = new StringBuffer("/");
        for (var s : stack) {
            sb.append(s).append('/');
        }

        if (!stack.isEmpty()) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    class Solution {
        public String simplifyPath(String path) {
            Deque<String> dirOrFiles = new ArrayDeque<>();
            for (String dirOrFile : path.split("/")) {
                if (!dirOrFiles.isEmpty() && dirOrFile.equals("..")) {
                    dirOrFiles.removeLast();
                } else if (!dirOrFile.equals(".") && !dirOrFile.equals("") && !dirOrFile.equals("..")) {
                    dirOrFiles.addLast(dirOrFile);
                }
            }
            StringBuilder simplified_path = new StringBuilder();
            for (String dirOrFile : dirOrFiles) {
                simplified_path.append("/").append(dirOrFile);
            }
            return simplified_path.length() == 0 ? "/" : simplified_path.toString();
        }
    }
}
