import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumASCIIDeleteSumforTwoStrings {
    public int helper(Map<String, Integer> dp, StringBuilder s1, StringBuilder s2) {
        if (s1.isEmpty() || s2.isEmpty() || s1.toString().equals(s2.toString()))
            return 0;

        if (dp.containsKey(s1.toString() + s2.toString()))
            return dp.get(s1.toString() + s2.toString());

        int sum = Integer.MAX_VALUE;
        if (s1.length() > s2.length()) {
            int len = s1.length();
            for (int i = 0; i < len; i++) {
                char tmp = s1.charAt(i);
                s1 = s1.deleteCharAt(i);
                int ssum = tmp + helper(dp, s1, s2);
                sum = Math.min(sum, ssum);
                s1 = s1.insert(i, tmp);
            }
        } else {
            int len = s2.length();
            for (int i = 0; i < len; i++) {
                char tmp = s2.charAt(i);
                s2 = s2.deleteCharAt(i);
                int ssum = tmp + helper(dp, s1, s2);
                sum = Math.min(sum, ssum);
                s2 = s2.insert(i, tmp);
            }
        }

        dp.put(s1.toString() + s2.toString(), sum);
        return sum;
    }

    public int minimumDeleteSum(String s1, String s2) {
        StringBuilder ss1 = new StringBuilder(s1);
        StringBuilder ss2 = new StringBuilder(s2);
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (var c : s1.toCharArray())
            set1.add(c);
        for (var c : s2.toCharArray())
            set2.add(c);

        int sum = 0;
        for (int i = 0; i < ss1.length(); i++) {
            if (!set2.contains(ss1.charAt(i))) {
                sum += ss1.charAt(i);
                ss1.deleteCharAt(i);
                i--;
            }
        }
        for (int i = 0; i < ss2.length(); i++) {
            if (!set1.contains(ss2.charAt(i))) {
                sum += ss2.charAt(i);
                ss2.deleteCharAt(i);
                i--;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        return sum + helper(dp, ss1, ss2);
    }

    // https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/solutions/3840821/dp-very-easy-to-understand-video-explanation/
    class Solution {
        int[][] dp;

        public int minimumDeleteSum(String s1, String s2) {
            dp = new int[s1.length() + 1][s2.length() + 1];
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }
            return solve(s1, s2, 0, 0);
        }

        private int solve(String s1, String s2, int i, int j) {
            if (dp[i][j] != -1)
                return dp[i][j];
            if (i == s1.length()) {
                int sum = 0;
                for (int k = j; k < s2.length(); k++) {
                    sum += s2.charAt(k);
                }
                return sum;
            }
            if (j == s2.length()) {
                int sum = 0;
                for (int k = i; k < s1.length(); k++) {
                    sum += s1.charAt(k);
                }
                return sum;
            }
            int nt = Math.min(s1.charAt(i) + solve(s1, s2, i + 1, j), s2.charAt(j) + solve(s1, s2, i, j + 1));
            int tk = Integer.MAX_VALUE;
            if (s1.charAt(i) == s2.charAt(j)) {
                tk = solve(s1, s2, i + 1, j + 1);
            }
            return dp[i][j] = Math.min(nt, tk);
        }
    }

    public static void main(String[] args) {
        MinimumASCIIDeleteSumforTwoStrings m = new MinimumASCIIDeleteSumforTwoStrings();
        Solution s = m.new Solution();
        // System.out.println(m.minimumDeleteSum("sea", "eat"));
        // System.out.println(m.minimumDeleteSum("delete", "leet"));
        // System.out.println(m.minimumDeleteSum("djoqzmzxe", "onydroiizrlggfh"));
        System.out.println(s.minimumDeleteSum("acacabcaabac", "accabaccccabaca"));
    }
}
