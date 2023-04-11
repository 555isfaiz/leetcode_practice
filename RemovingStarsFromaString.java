import java.util.Stack;

public class RemovingStarsFromaString {
    public String removeStars(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (c == '*' && !sb.isEmpty())
                sb.deleteCharAt(sb.length() - 1);
            else if (c != '*')
                sb.append(c);
        }

        return sb.toString();
    }
}
