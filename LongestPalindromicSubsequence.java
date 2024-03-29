public class LongestPalindromicSubsequence {
    int helper(char[] ca, int head, int tail) {
        while (ca[tail] != ca[head] && tail > head) {
            tail--;
        }

        if (tail - head > 1)
            return helper(ca, ++head, --tail) + 2;
        else if (tail - head == 1)
            return 2;
        else
            return 1;
    }

    public int longestPalindromeSubseq(String s) {
        var ca = s.toCharArray();
        if (ca.length == 1)
            return 1;

        int res = 0;
        for (int i = 0; i < ca.length; i++)
            res = Math.max(helper(ca, i, ca.length - 1), res);

        return res;
    }

    // https://leetcode.com/problems/longest-palindromic-subsequence/solutions/3414528/python-java-c-simple-solution-easy-to-understand/
    class Solution {
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[] dp = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                int[] newdp = new int[n];
                newdp[i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        newdp[j] = 2 + dp[j-1];
                    } else {
                        newdp[j] = Math.max(dp[j], newdp[j-1]);
                    }
                }
                dp = newdp;
            }
            return dp[n-1];
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence l = new LongestPalindromicSubsequence();
        System.out.println(l.longestPalindromeSubseq("cbbd"));
    }
}