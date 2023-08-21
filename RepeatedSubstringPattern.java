/**
 * RepeatedSubstringPattern
 */
public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int ptr = s.indexOf(s.charAt(s.length() - 1), 0);
        while (ptr != -1 && ptr < s.length() - 1) {
            if (s.length() % (ptr + 1) == 0) {
                boolean match = true;
                String sub = s.substring(0, ptr + 1);
                for (int i = ptr + 1; i < s.length(); i += ptr + 1) {
                    if (!s.substring(i, i + ptr + 1).equals(sub)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    return true;
            }
            ptr = s.indexOf(s.charAt(s.length() - 1), ptr + 1);
        }
        return false;
    }

    // https://leetcode.com/problems/repeated-substring-pattern/solutions/3938580/99-42-2-approaches-o-n/
    public class Solution1 {
        public boolean repeatedSubstringPattern(String s) {
            int n = s.length();
            for (int i = 1; i <= n / 2; i++) {
                if (n % i == 0) {
                    String substring = s.substring(0, i);
                    StringBuilder repeated = new StringBuilder();
                    for (int j = 0; j < n / i; j++) {
                        repeated.append(substring);
                    }
                    if (repeated.toString().equals(s)) return true;
                }
            }
            return false;
        }
    }

    public class Solution2 {
        public boolean repeatedSubstringPattern(String s) {
            String doubled = s + s;
            String sub = doubled.substring(1, doubled.length() - 1);
            return sub.contains(s);
        }
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern r = new RepeatedSubstringPattern();
        System.out.println(r.repeatedSubstringPattern("abab"));
    }
}
