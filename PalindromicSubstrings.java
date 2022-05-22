import java.util.LinkedList;
import java.util.Queue;

// 647
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        Queue<int[]> known = new LinkedList<>();
        int val = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    known.add(new int[]{i, i + 1});
                    val++;
                }
            }

            if (i < s.length() - 1 && i > 0) {
                if (s.charAt(i - 1) == s.charAt(i + 1)) {
                    known.add(new int[]{i - 1, i + 1});
                    val++;
                }
            }
        }

        while (!known.isEmpty()) {
            var ind = known.poll();
            if (ind[0] - 1 < 0 || ind[1] + 1 >= s.length()) continue;
            if (s.charAt(ind[0] - 1) == s.charAt(ind[1] + 1)) {
                val++;
                known.add(new int[]{ind[0] - 1, ind[1] + 1});
            }
        }

        return val;
    }

    // same idea, but better performence
    // https://leetcode.com/problems/palindromic-substrings/discuss/105689/Java-solution-8-lines-extendPalindrome
    int count = 0;
    public int countSubstringsBetter(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }

        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }

    public static void main(String[] args) {
        PalindromicSubstrings p = new PalindromicSubstrings();
        System.out.println(p.countSubstrings("aaaa"));
    }
}
