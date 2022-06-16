import java.util.LinkedList;
import java.util.Queue;

// 5
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        Queue<int[]> known = new LinkedList<>();
        int[] max = new int[]{0,0};
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    if (1 > max[1] - max[0]) { max[0] = i; max[1] = i + 1; }
                    known.add(new int[]{i, i + 1});
                }
            }

            if (i < s.length() - 1 && i > 0) {
                if (s.charAt(i - 1) == s.charAt(i + 1)) {
                    if (i + 1 - (i - 1) > max[1] - max[0]) { max[0] = i - 1; max[1] = i + 1; }
                    known.add(new int[]{i - 1, i + 1});
                }
            }
        }

        while (!known.isEmpty()) {
            var ind = known.poll();
            if (ind[0] - 1 < 0 || ind[1] + 1 >= s.length()) continue;
            if (s.charAt(ind[0] - 1) == s.charAt(ind[1] + 1)) {
                if (ind[1] + 1 - (ind[0] - 1) > max[1] - max[0]) { max[0] = ind[0] - 1; max[1] = ind[1] + 1; }
                known.add(new int[]{ind[0] - 1, ind[1] + 1});
            }
        }

        return s.substring(max[0], max[1] + 1);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("babad"));
    }
}
